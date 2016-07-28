package chessui;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;


public class ChessBoard {

	private List<ChessPiece> pieces = new ArrayList<ChessPiece>();

	public ChessBoard() {	
		pieces.add(new Rook(0, 0, false));
		pieces.add(new Knight(1, 0, false));
		pieces.add(new Bishop(2, 0, false));
		pieces.add(new Queen(3, 0, false));
		pieces.add(new King(4, 0, false));
		pieces.add(new Bishop(5, 0, false));
		pieces.add(new Knight(6, 0, false));
		pieces.add(new Rook(7, 0, false));


		for (int x = 0; x <= 7; x++) {
			pieces.add(new Pawn(x, 1, false));
		}

		for (int y = 2; y <= 5; y++) {
			for (int x = 0; x <= 7; x++) {
				pieces.add(new NullPiece(x, y));
			}
		}

		for (int x = 0; x <= 7; x++) {
			pieces.add(new Pawn(x, 6, true));
		}

		pieces.add(new Rook(0, 7, true));
		pieces.add(new Knight(1, 7, true));
		pieces.add(new Bishop(2, 7, true));
		pieces.add(new Queen(3, 7, true));
		pieces.add(new King(4, 7, true));
		pieces.add(new Bishop(5, 7, true));
		pieces.add(new Knight(6, 7, true));
		pieces.add(new Rook(7, 7, true));
	}

	public List<ChessPiece> getPieces() {
		return pieces;
	}

	public ChessPiece getPiece(Point2D Point2D) {
		for (ChessPiece piece : pieces) {
			if (piece.position.equals(Point2D))
				return piece;
		}
		return null;
	}

	public void movePiece(ChessPiece from, ChessPiece to) {
		int fromIndex = pieces.indexOf(from);
		int toIndex = pieces.indexOf(to);
		pieces.set(fromIndex, new NullPiece((int)from.position.getX(), (int)from.position.getY()));

		from.position = to.position;
		pieces.set(toIndex, from);
	}

}
