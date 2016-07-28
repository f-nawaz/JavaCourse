package examples;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Exceptions {
	private static void method2(String s) throws IOException {
		Path path = Paths.get(s);
		OutputStream os = Files.newOutputStream(path);
	}

	private static void method1(String s) throws IOException {
		method2(s);
	}

	public static void main(String[] args) {
		try {
			 method1("C:/Users/Public/file.txt");
			//catchAndFinally2();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	private static void tryCatchAndFinally1() throws IOException {
		OutputStream os = null;
		try {
			Path path = Paths.get("C:/file.txt");
			os = Files.newOutputStream(path);
		} catch (AccessDeniedException e) {
			System.out.println("first catch block " + e);
		} catch (IOException e) {
			System.out.println("second catch block " + e);
		} finally {
			System.out.println("finally block");
			if (os != null)
				os.close();
		}
	}

	private static void tryCatchAndFinally2() throws IOException {
		OutputStream os = null;
		try {
			Path path = Paths.get("C:/file.txt");
			os = Files.newOutputStream(path);
		} catch (IOException | IllegalArgumentException e) {
			System.out.println("catch block " + e);
		} finally {
			System.out.println("finally block");
			if (os != null)
				os.close();
		}
	}

	private static void autoCloseable() throws IOException {
		Path path = Paths.get("C:/file.txt");
		try (OutputStream os = Files.newOutputStream(path)) {

		} catch (IOException e) {
			System.out.println("catch block " + e);
		}
	}

}
