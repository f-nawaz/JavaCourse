package chessui;

import static org.junit.Assert.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import chessui.ChessPiece;
import chessui.Rook;

/*
   Include the following dependency
 
		 <dependency>
			<groupId>pl.pragmatists</groupId>
			<artifactId>JUnitParams</artifactId>
			<version>1.0.2</version>
		</dependency>
 */
@Ignore
@RunWith(JUnitParamsRunner.class)
public class ParameterizedTest {
	
	@Test(expected=IndexOutOfBoundsException.class)
	@Parameters({"0,8", "8,0", "-1,0", "0,-1"})
	public void chessPieceConstructorThrowsExceptionIfNotOnBoard(int x, int y) {
		new Rook(x,y,true);
	}
	
	@Test
	@Parameters({"0,0,false,a8","7,0,false,h8","0,7,true,a1","7,7,true,h1"})
	public void shouldConvertToAlgebraicNotation(int x, int y, boolean isWhite, String expectedAlgebraic) {
		//arrange
		ChessPiece cp = new ChessPiece(x,y,isWhite);
		
		//act
		String actualAlgebraic = cp.getAlgebraicNotatation();
		
		//assert
		assertEquals(expectedAlgebraic, actualAlgebraic);
	}	
}