package bean;

import bean.manager.PushBean;
import bean.manager.SessionManagerBean;
import boundary.CharactersServiceRemote;
import model.User;
import util.EJBUtility;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;

@SessionScoped
@Named(value = "sessionBean")
public class SessionBean implements Serializable {

    private static final long serialVersionUID = 7163621059222464579L;

    @Inject
    private SessionManagerBean sessionManager;

    @Inject
    private PushBean pushBean;

    private User userFromSession;

    private CharactersServiceRemote charactersServiceRemote;

    SessionBean() throws NamingException {
        System.out.println("User session bean constructor");
        charactersServiceRemote = EJBUtility.lookupCharactersService();
    }

    @PostConstruct
    public void init() {
        userFromSession = charactersServiceRemote.getUserFromSessionWithTypeSet();
        String sessionMessage = sessionManager.getSessionMessage(userFromSession.getUsername());
        pushBean.dummyMethod();
        System.out.println("Initialization of session bean: " + sessionMessage);
        if(sessionMessage.equals("Another session")) logout(true);
    }

    @PreDestroy
    public void destroy() {
        sessionManager.logoutUser(userFromSession.getUsername());
        System.out.println("Pre destroy: " + userFromSession.getUsername());
    }

    public String getCurrentUserTypeSetId() {
        String id = Integer.toString(userFromSession.getTypeSet().getIdTypeSet());
        System.out.println(id);
        return id;
    }

    public User getUserFromSession() {
        return userFromSession;
    }

    public void setUserFromSession(User userFromSession) {
        this.userFromSession = userFromSession;
    }

    public void logout(boolean hasSession) {
        HttpServletRequest req = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext().getRequest());
        sessionManager.logoutUser(req.getRemoteUser());
        FacesContext facesContext = FacesContext.getCurrentInstance();

        if(hasSession) {
            facesContext.addMessage(
                    null, new FacesMessage("You already have a session")
            );
        }

        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.getFlash().setKeepMessages(true);

        try {
            externalContext.redirect("index.xhtml");
            facesContext.getMessageList().forEach(
                    facesMessage -> System.out.println(facesMessage.getSummary())
            );
        } catch (IOException e) {
            System.err.println("Redirect error");
        }

        externalContext.invalidateSession();

    }
}
