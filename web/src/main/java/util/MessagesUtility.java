package util;

import org.primefaces.PrimeFaces;

import javax.faces.context.FacesContext;

public class MessagesUtility {

    public static String getSimpleMessageFromException(String message) {
        return message.substring(message.indexOf(':') + 1);
    }

    public static void showInfoMessage(PrimeFaces primeFaces, String summary, String detail) {
        primeFaces.executeScript("showNewMessage('" + summary + "', '" + detail + "', 'info');");
    }

    public static String getParamFromContext(String paramName) {
        return FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get(paramName);
    }
}
