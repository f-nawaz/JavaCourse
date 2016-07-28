package fxml;

import java.util.Collection;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import session.FilmDAO;
import session.FilmDAO.Type;
import entity.Film;
import session.JpaFilmDAO;

/*
 * the fx:controller attribute associates a "controller" class with an FXML document. 
 * This contains the "code behind" the object hierarchy defined by the XML document.
 */
public class Controller {
	//annotation injects the control objects into the controller class
	@FXML
	private TableView<Film> table;
	@FXML
	private TableColumn<Film, String> titleCol;
	@FXML
	private TableColumn<Film, Integer> stockCol;
	@FXML
	private TableColumn<Film, String> yearCol;
	@FXML
	private TextField searchText;

	private ObservableList<Film> data;

	/*
	 * An instance of the FXMLLoader class calls the Controller's initialize()
	 * method, if available.
	 */
	public void initialize() {
		Collection<Film> films = new JpaFilmDAO().selectAll();
		data = FXCollections.observableArrayList(films);

		titleCol.setCellValueFactory(new PropertyValueFactory<Film, String>(
				"title"));
		stockCol.setCellValueFactory(new PropertyValueFactory<Film, Integer>(
				"stock"));

		yearCol.setCellValueFactory(c -> {
			return new SimpleStringProperty(String.format("%tY",c.getValue().getReleased()));			
		});

		table.setItems(data);
		
		ChangeListener<String> changeListener = (obsVal, oldVal, newVal) -> {
			Collection<Film> filmList = new JpaFilmDAO().selectByTitle(
			searchText.getText());
			data.setAll(filmList);
		};

		searchText.textProperty().addListener(changeListener);

//		searchText.textProperty().addListener(
//				(obsVal, oldVal, newVal) -> {
//					List<Film> filmList = Factory.getFilmDAO().selectByTitle(
//							searchText.getText());
//					data.setAll(filmList);
//				});

		Platform.runLater(() -> searchText.requestFocus());

	}
}
