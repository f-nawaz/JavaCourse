package examples;

import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class Locales {
	public static void main(String[] args) {
		//a Locale identifies a language and region
		Locale locale1 = Locale.forLanguageTag("es-AR"); //IETF BCP 47
		Locale.setDefault(locale1);
		System.out.printf("Default locale is %s%n",Locale.getDefault().getDisplayName()); //Spanish (Argentina)
		System.out.printf(locale1, 
				"Numbers and dates formatted for this locale: %-10.2f%,-10d%-15s%tB %4$tY%n",     
		              Math.PI, 10000, "some text", LocalDate.now());

		// Create ResourceBundle from resources.properties
		ResourceBundle messages = ResourceBundle.getBundle("resources.labels");
		// Fetch the Text from the ResourceBundle
		String headerText  = messages.getString("header");
		System.out.printf("value of header key for this locale: %s",headerText );
	}
}
