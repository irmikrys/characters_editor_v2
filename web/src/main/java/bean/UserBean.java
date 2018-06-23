package bean;


import boundary.CharactersServiceRemote;
import model.User;
import util.EJBUtility;
import util.MessagesUtility;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.naming.NamingException;
import java.io.Serializable;
import java.util.List;

@Named("userBean")
@ViewScoped
public class UserBean implements Serializable {

    private static final long serialVersionUID = 3432068515031339999L;

    private List<User> users;
    private String username;
    private String newPassword;

    private String successMessage;
    private String errorMessage;

    private CharactersServiceRemote charactersServiceRemote;

    UserBean() throws NamingException {
        charactersServiceRemote = EJBUtility.lookupCharactersService();
        System.out.println("User bean");
    }

    @PostConstruct
    public void init() {
        updateUsers();
        clearFields();
    }

    public void submitPassword() {
        try {
            changePassword();
            updateUsers();
        } catch (Exception e) {
            errorMessage = MessagesUtility.getSimpleMessageFromException(e.getMessage());
        }
    }

    public void updateUsers() {
        users = charactersServiceRemote.getAllUsers();
    }

    private void changePassword() {
        charactersServiceRemote.updatePassword(username, newPassword);
        clearFields();
        successMessage = "Password successfully submitted!";
        errorMessage = null;
    }

    private void clearFields() {
        username = null;
        newPassword = null;
        successMessage = null;
        errorMessage = null;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
