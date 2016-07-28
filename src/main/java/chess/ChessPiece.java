package chess;

import javafx.geometry.Point2D;

public abstract class ChessPiece {
	private Point2D position;

	public ChessPiece(int x, int y) {
		this.position = new Point2D(x,y);
	}
	
	public abstract String getImage();
}
