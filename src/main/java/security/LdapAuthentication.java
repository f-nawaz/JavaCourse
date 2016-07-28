package security;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;


public class LdapAuthentication {

	public static void main(String[] args) {
		try {
			Hashtable env = new Hashtable();
			/*
			use the LDAP service provider as the initial context.
			https://docs.oracle.com/javase/8/docs/technotes/guides/jndi/jndi-ldap.html#PROP
			 */
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			/*
			http://www.forumsys.com/en/tutorials/integration-how-to/ldap/online-ldap-test-server/
			 */
			env.put(Context.PROVIDER_URL, "ldap://ldap.forumsys.com");
			env.put(Context.SECURITY_PRINCIPAL, "uid=riemann,dc=example,dc=com");
			env.put(Context.SECURITY_CREDENTIALS, "password");

			// Create the initial context
			DirContext ctx = new InitialDirContext(env);
			System.out.println("authenticated");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
