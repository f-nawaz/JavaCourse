package qualifiers;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.*;
import static java.lang.annotation.ElementType.*;
import javax.inject.Qualifier;


/**
 * Use qualifiers to provide various implementations of a particular bean type.
 */
@Qualifier
@Target({TYPE, FIELD})
@Retention(RUNTIME)
public @interface Jpa {

}
