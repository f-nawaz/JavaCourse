package chessui;

import java.util.List;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class JavaFX extends Application {
	private ChessBoard board = new ChessBoard();
	private int boardWidth = (int)Screen.getPrimary().getVisualBounds().getHeight()/2;

	public void start(Stage stage) {
		Group root = new Group();
		Scene scene = new Scene(root, boardWidth, boardWidth, Color.WHITE);
		List<ChessPiece> pieces = board.getPieces();
		for (ChessPiece chessPiece : pieces) {
			Rectangle2D rect = chessPiece.getRect(boardWidth);
			Image image = new Image(getClass().getResourceAsStream("/chess/"+chessPiece.getImage()
			+ ".png"));
			ImageView imageView = new ImageView();
			imageView.setImage(image);
			imageView.setX(rect.getMinX());
			imageView.setY(rect.getMinY());
			imageView.setFitHeight(boardWidth/8);
			imageView.setFitWidth(boardWidth/8);
			root.getChildren().add(imageView);
			imageView.setOnMousePressed(e -> 
				stage.setTitle(chessPiece.toString())
			);
		}

		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
