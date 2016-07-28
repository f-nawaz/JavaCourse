package session;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import entity.Film;

public class SerializerImpl implements Serializer {
	private Path path = Paths.get("object.bin");

	//Serialized objects must implement the java.io.Serializable interface
	@Override
	public void serialize(Map<Long, Film> films) {
		try (ObjectOutputStream oos = new ObjectOutputStream(
				Files.newOutputStream(path))) {
			oos.writeObject(films);
		} catch (IOException e) {
			System.out.println(e);
			throw new FilmException(e.getMessage());
		}
	}

	@Override
	public Map<Long, Film> deserialize() {
		if (!Files.exists(path))
			return new ConcurrentHashMap<Long, Film>();
		try (ObjectInputStream ois = new ObjectInputStream(
				Files.newInputStream(path))) {
			return (ConcurrentHashMap<Long, Film>) ois.readObject();
		} catch (Exception e) {
			System.out.println(e);
			throw new FilmException(e.getMessage());
		}
	}
}
