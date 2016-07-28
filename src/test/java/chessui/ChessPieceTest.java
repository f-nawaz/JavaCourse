package chessui;
//import static org.testng.Assert.*;
//import org.testng.annotations.Test;
import junitparams.Parameters;

import org.junit.Ignore;
import org.junit.Test;

import chessui.Bishop;
import chessui.ChessPiece;
import chessui.King;
import chessui.Knight;
import chessui.Queen;
import chessui.Rook;
import static org.junit.Assert.*;

@Ignore
public class ChessPieceTest {
	
	//@Ignore
	@Test(expected=IndexOutOfBoundsException.class)
	public void chessPieceConstructorThrowsExceptionIfNotOnBoard() {
		new Rook(8,8,true);
	}
	
	
	@Ignore
	@Test
	public void getImageShouldReturnCorrectString() {
	
		//arrange
		ChessPiece cp = new Rook(0, 0, false); //black rook on light square
		//act
		String image = cp.getImage();
		//assert
		assertTrue("rook dark light square",image == "rdl"); 
		
		//arrange
		cp = new Knight(1, 0, false); //black knight on dark square
		//act
		image = cp.getImage();
		//assert
		assertTrue("kight dark dark square",image == "ndd"); 
		
		//arrange
		cp = new Bishop(2, 0, false); //black bishop on light square
		//act
		image = cp.getImage();
		//assert
		assertTrue("bishop dark light square", image == "bdl");	
		
		//arrange
		cp = new Queen(3, 0, false); //black queen on dark square
		//act
		image = cp.getImage();
		//assert
		assertTrue("queen dark dark square", image == "qdd");	
		
		//arrange
		cp = new King(4, 0, false); //black king on light square
		//act
		image = cp.getImage();
		//assert
		assertTrue("king dark light square", image == "kdl");	
		
		//arrange
		cp = new Bishop(5, 0, false); //black bishop on dark square
		//act
		image = cp.getImage();
		//assert
		assertTrue("bishop dark dark square",image == "bdd"); 
		
		//arrange
		cp = new Knight(6, 0, false); //black knight on light square
		//act
		image = cp.getImage();
		//assert
		assertTrue("knight dark light square", image == "ndl");	
		
		//arrange
		cp = new Rook(7, 0, false); //black rook on dark square
		//act
		image = cp.getImage();
		//assert
		assertTrue("rook dark dark square", image == "rdd");	
		
	
	}

}
