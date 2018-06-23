package dao;

import model.User;
import org.jboss.ejb3.annotation.SecurityDomain;
import util.PasswordEncoder;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Stateless
@SecurityDomain("soaEJBApplicationDomain")
@RolesAllowed({"User"})
public class UserDAO extends AbstractDAO<User, Integer> {

    public UserDAO() {
        this.entityClass = User.class;
    }

    public boolean existsByUsername(String username) {
        TypedQuery<Boolean> query = em.createQuery(
                "SELECT (count(u) = 1) FROM User u WHERE u.username = :username",
                Boolean.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    public Optional<User> findByUsername(String username) {
        Optional<User> user;
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u " +
                        "WHERE u.username = :username",
                User.class);
        query.setParameter("username", username);
        try {
            user = Optional.of(query.getSingleResult());
        } catch (PersistenceException e) {
            user = Optional.empty();
        }
        return user;
    }

    public Optional<User> findByUsernameWithTypeSet(String username) {
        Optional<User> user;
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u " +
                        "JOIN FETCH u.typeSet t " +
                        "WHERE u.username = :username",
                User.class);
        query.setParameter("username", username);
        try {
            user = Optional.of(query.getSingleResult());
        } catch (PersistenceException e) {
            user = Optional.empty();
        }
        return user;
    }

    public void update(Integer idUser, String username, String password) {
        findById(idUser).ifPresent(user -> {
            if (username != null && !username.isEmpty())
                user.setUsername(username);
            if (password != null && !password.isEmpty())
                user.setPassword(PasswordEncoder.getEncodedPassword(password));
        });
    }
}
