package chessui;

public class Main {
	public static void main(String[] args) {
		Rook r1 = new Rook(1, 2, false);
		System.out.println(r1.hashCode());

		Rook r2 = new Rook(1, 2, false);
		System.out.println(r2.hashCode());
	}
}
