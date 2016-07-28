package javafx;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import session.FilmDAO;
import session.FilmDAO.Type;
import entity.Film;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import session.JpaFilmDAO;

//JavaFX applications extend Application. 
//This class sets control layout programmatically. Alternative is a separate FXML file
public class Main extends Application {

	public static void main(String[] args) {
		Application.launch();
	}

	// JavaFX runtime calls start(), passing in the primary Stage .
	// This is the top level JavaFX container.
	@Override
	public void start(Stage primaryStage) {
		try {
			// Layout controls programmatically using a BorderPane
			BorderPane pane = new BorderPane();

			// build a Label and a TextField
			Label label1 = new Label("Search");
			TextField searchText = new TextField();
			searchText.setId("textField1");

			// Add the Label and TextField to an HBox.
			// Add the HBox to the top of the BorderPane
			HBox hbox = new HBox();
			hbox.getChildren().addAll(label1, searchText);
			pane.setTop(hbox);

			// add a TableView to the centre of the BorderPane
			// http://docs.oracle.com/javase/8/javafx/user-interface-tutorial/table-view.htm#CJAGAAEE
			TableView<Film> table = new TableView<>();
			pane.setCenter(table);

			// add three TableColumns
			TableColumn<Film, String> titleCol = new TableColumn<>("Title");
			table.getColumns().add(titleCol);
			titleCol.setPrefWidth(400);

			TableColumn<Film, Integer> stockCol = new TableColumn<>("Stock");
			stockCol.setMinWidth(100);
			table.getColumns().add(stockCol);

			TableColumn<Film, String> yearCol = new TableColumn<>("Year");
			yearCol.setMinWidth(100);
			table.getColumns().add(yearCol);

			// The Scene class is the container for all content. It resides in a
			// Stage
			Scene scene = new Scene(pane, 600, 400);
			primaryStage.setScene(scene);
			primaryStage.setTitle(pane.getClass().getSimpleName());
			primaryStage.show();

			// optionally set a stylesheet.
			//scene.getStylesheets().add("javafx/style.css");

			Platform.runLater(() -> searchText.requestFocus());
			
			/* PropertyValueFactory<S,T> is a convenience implementation of the Callback interface
			 * where S is the TableView type and T is the TableColumn type. The constructor argument 
			 * is the property name, extracted reflectively from a given TableView row item. */
			titleCol.setCellValueFactory(new PropertyValueFactory<Film, String>(
					"title"));
			
			stockCol.setCellValueFactory(new PropertyValueFactory<Film, Integer>(
					"stock"));
			
			/* The cell value factory needs to be set to specify how to populate all cells within a single TableColumn. 
			 * A cell value factory is a Callback that provides a TableColumn.CellDataFeatures instance, 
			 * and expects an ObservableValue to be returned. The returned ObservableValue instance will be observed 
			 * internally to allow for immediate updates to the value to be reflected on screen.
		     * CellDataFeatures<S,T>, where S is the TableView type and T is the TableColumn type.
		      *
		     * This will cause an exception if the Date field is transient
		     * */
			yearCol.setCellValueFactory(new Callback<CellDataFeatures<Film, String>, ObservableValue<String>>() {

				@Override
				public ObservableValue<String> call(
						CellDataFeatures<Film, String> param) {
					return new SimpleStringProperty(Integer.toString(param
							.getValue().getReleased().getYear()));
				}

			});


			/*Retrieve a a collection of films, create a new observable array list from this collection,
			 * the pass the ObservableList into the TableView's setData method. 
			 * Changes to the ObservableList will automatically be displayed in the TableView.
			*/
			
			FilmDAO dao = new JpaFilmDAO();
			
			Collection<Film> films = dao.selectAll();
			
			ObservableList<Film> data = FXCollections
					.observableArrayList(films);

			table.setItems(data);

			searchText.textProperty().addListener(
					(obsVal, oldVal, newVal) -> {
						Collection<Film> filmList = dao.selectByTitle(searchText.getText());
						data.setAll(filmList);
					});
			
		} catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}