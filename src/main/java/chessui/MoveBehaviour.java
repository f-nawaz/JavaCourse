package chessui;

import java.util.Collection;
import java.util.HashSet;

import javafx.geometry.Point2D;

public interface MoveBehaviour {
	Collection<Point2D> moves();
}

