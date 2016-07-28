package chessui;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import chessui.Rook;
@Ignore
public class OverrideObjectTest {

	
	@Test
	public void referenceEquality() {
		//arrange
		Rook rook1 = new Rook(1, 2, true);
		Rook rook2 = rook1;
		
		//assert
		assertTrue(rook1.equals(rook2));
	}
	

	@Test
	public void valueEquality() {
		//arrange
		Rook rook1 = new Rook(1, 2, true);
		Rook rook2 = new Rook(1, 2, true);
		
		//assert
		assertTrue(rook1.equals(rook2));
	}
	
	@Test
	public void equalHashcodes() {
		//arrange
		Rook rook1 = new Rook(1, 2, true);
		Rook rook2 = new Rook(1, 2, true);
		
		//act
		int i = rook1.hashCode();
		int j = rook2.hashCode();
		
		//assert
		assertTrue(i==j);//only true if hashCode overridden
	}
}
