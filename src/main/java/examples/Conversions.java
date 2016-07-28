package examples;

public class Conversions {

	public static void main(String[] args) {
		int i = 5; // assign 5 to i
		double d = i; // widening conversion
		double x = Math.pow(2, 32);
		//int y = x; // narrowing conversion won't compile
		int y = (int) x; // cast x as an int
		System.out.println(x); // 4.294967296E9
		System.out.println(y); // 2147483647

	}
}
