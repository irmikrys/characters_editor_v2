package util;

import boundary.CharactersServiceRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class EJBUtility {

    public static CharactersServiceRemote lookupCharactersService() throws NamingException {
        Context context = getContext();
        return (CharactersServiceRemote) context.lookup("ejb:/web_main_war/CharactersService!" +
                CharactersServiceRemote.class.getName());
    }

    private static Context getContext() throws NamingException {
        final Properties jndiProperties = new Properties();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        return new InitialContext(jndiProperties);
    }

}
