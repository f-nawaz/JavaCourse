package chess;

public class ChessBoard {
	public ChessPiece[] pieces = new ChessPiece[2];
	
	public ChessBoard() {
		pieces[0] = new Rook(0,0);
		pieces[1] = new Knight(1,0);
	}
}
