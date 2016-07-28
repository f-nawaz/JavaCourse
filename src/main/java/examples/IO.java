package examples;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

public class IO {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// writePrimitives();
		// writeText();
		// readText();
	}
	
	private static void writePrimitives() throws IOException {
		Path path = Paths.get("file.bin");
		try (DataOutputStream oos = new DataOutputStream(Files.newOutputStream(path))) {
			oos.writeByte(127); // 1 byte
			oos.writeChar('\u0061'); // 2 bytes
			oos.writeInt(2147483647); // 4 bytes
			oos.writeDouble(Double.MAX_VALUE); // 8 bytes
		}
	}

	private static void writeText() throws IOException {
		Path path = Paths.get("file.txt");
		Set<String> zoneIds = ZoneId.getAvailableZoneIds();
		Files.write(path, zoneIds, StandardOpenOption.CREATE);
	}

	private static void readText() throws IOException {
		Path path = Paths.get("file.txt");
		List<String> lines = Files.readAllLines(path);
		lines.forEach(System.out::println);
	}
}
