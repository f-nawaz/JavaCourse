package session;

import entity.Film;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface Serializer {
	//Serialized objects must implement the java.io.Serializable interface

	void serialize(Map<Long, Film> films);

	Map<Long, Film> deserialize();
}
