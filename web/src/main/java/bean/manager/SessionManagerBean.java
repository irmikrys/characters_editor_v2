package bean.manager;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Named
@ApplicationScoped
public class SessionManagerBean implements Serializable {

    private static final long serialVersionUID = -7902579108607730057L;

    private List<String> loggedInUsers;

    public SessionManagerBean() {
        System.out.println("Manager bean constructor");
    }

    @PostConstruct
    public void init() {
        loggedInUsers = new LinkedList<>();
        System.out.println("Manager bean initialized");
    }

    public String getSessionMessage(String username) {

        String sessionMessage;

        if (loggedInUsers.contains(username)) {
            System.out.println("User '" + username + "' already has got session");
            sessionMessage = "Another session";
        } else {
            System.out.println("User '" + username + "' started new session");
            sessionMessage = "New session";
            loggedInUsers.add(username);
        }

        return sessionMessage;
    }

    public void logoutUser(String username) {
        System.out.println("user: '" + username + "' logout");
        loggedInUsers.remove(username);
    }

}
