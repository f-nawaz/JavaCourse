package jaxrs;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * The Application class defines the components of a JAX-RS application and
 * supplies additional meta-data. A JAX-RS application or implementation
 * supplies a concrete subclass of this abstract class. The Path annotation
 * identifies the application path that serves as the base URI for all resource
 * URIs provided by Path.
 *
 */
@ApplicationPath("/rest")
public class RestConfig extends Application {
}
