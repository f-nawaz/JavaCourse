package chess;

public class Main {

	public static void main(String[] args) {
		
		ChessBoard board = new ChessBoard();		
		for (ChessPiece cp : board.pieces) {
			String image = cp.getImage();
			System.out.println(image);
		}
	}

}
