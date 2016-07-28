package chessui;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

/**
 * An empty square on the board
 */
public class ChessPiece {
	protected Point2D position;
	protected Boolean whitePiece; // true, false, null if empty square
	//protected MoveBehaviour moveBehaviour; 

	public ChessPiece(int x, int y, boolean whitePiece) {
		if (x>7||y>7||x<0||y<0)
			throw new IndexOutOfBoundsException(String.format("%d,%d %s",x,y, "is not on the board"));
		this.position = new Point2D(x, y);
		this.whitePiece = whitePiece;
	}

	public ChessPiece(Point2D position) {
		this.position = position;
	}

	public boolean isOnWhiteSquare() {
		return (position.getX() + position.getY()) % 2 == 0;
	}

	/**
	 * Gets the image filename
	 */
	public String getImage() {
		return isOnWhiteSquare() ? "l" : "d";
	}

	/**
	 * Gets a Rectangle2D describing the pixel coordinates of the chesspiece on the
	 * screen
	 * 
	 * @param boardWidth the pixel width of the board
	 */
	public Rectangle2D getRect(int boardWidth) {
		int chessPieceWidth = (int) (boardWidth / 8.0);
		return new Rectangle2D(chessPieceWidth * position.getX(), chessPieceWidth
				* position.getY(), chessPieceWidth * (position.getX() + 1),
				chessPieceWidth * (position.getY() + 1));
	}

	public String getAlgebraicNotatation() {
		return (Character.toString((char) (position.getX() + 97)) + (8 - (int)position.getY()));
	}

	@Override
	public boolean equals(Object ob) {
		return ob instanceof ChessPiece ?  ((ChessPiece) ob).position.equals(position) : false;
	}

	// refer to http://developer.android.com/reference/java/lang/Object.html
	@Override
	public int hashCode() {
		return 3 * whitePiece.hashCode() + 5 * position.hashCode();
	}
	
	@Override
	public String toString() {
		return (whitePiece ? "White " : "Black ") + getClass().getSimpleName()
				+ " at " + getAlgebraicNotatation();
	}

}

class NullPiece extends ChessPiece {
	public NullPiece(int x, int y) {
		super(new Point2D(x,y));
	}

	@Override
	public String getImage() {
		return isOnWhiteSquare() ? "l" : "d";
	}

	@Override
	public String toString() {
		return getAlgebraicNotatation();
	}

}

class Pawn extends ChessPiece {
	public Pawn(int x, int y, boolean whitePiece) {
		super(x, y, whitePiece);
	}

	@Override
	public String getImage() {
		if (isOnWhiteSquare()) {
			return whitePiece ? "pll" : "pdl";
		}
		return whitePiece ? "pld" : "pdd";
	}
}

class Rook extends ChessPiece {
	public Rook(int x, int y, boolean whitePiece) {
		super(x, y, whitePiece);
	}

	@Override
	public String getImage() {
		if (isOnWhiteSquare()) {
			return whitePiece ? "rll" : "rdl";
		}
		return whitePiece ? "rld" : "rdd";
	}
}

class Knight extends ChessPiece {
	public Knight(int x, int y, boolean whitePiece) {
		super(x, y, whitePiece);
	}

	@Override
	public String getImage() {
		if (isOnWhiteSquare()) {
			return whitePiece ? "nll" : "ndl";
		}
		return whitePiece ? "nld" : "nld";
	}

}

class Bishop extends ChessPiece {
	public Bishop(int x, int y, boolean whitePiece) {
		super(x, y, whitePiece);
	}

	@Override
	public String getImage() {
		if (isOnWhiteSquare()) {
			return whitePiece ? "bll" : "bdl";
		}
		return whitePiece ? "bld" : "bdd";
	}

}

class Queen extends ChessPiece {
	public Queen(int x, int y, boolean whitePiece) {
		super(x, y, whitePiece);
	}

	@Override
	public String getImage() {
		if (isOnWhiteSquare()) {
			return whitePiece ? "qll" : "qdl";
		}
		return whitePiece ? "qld" : "qdd";
	}
}

class King extends ChessPiece {
	public King(int x, int y, boolean whitePiece) {
		super(x, y, whitePiece);
	}

	@Override
	public String getImage() {
		if (isOnWhiteSquare()) {
			return whitePiece ? "kll" : "kdl";
		}
		return whitePiece ? "kld" : "kdd";
	}
}
