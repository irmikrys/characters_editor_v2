package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "soa_game")
public class User implements Serializable {

    private static final long serialVersionUID = 3599516230831373108L;

    private int idUser;
    private String username;
    private String password;
    private TypeSet typeSet;
    private Collection<Category> categories;
    
    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Column(name = "username", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTypeSet", referencedColumnName = "idTypeSet", nullable = false)
    public TypeSet getTypeSet() {
        return typeSet;
    }

    public void setTypeSet(TypeSet typeSet) {
        this.typeSet = typeSet;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return idUser == user.idUser &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idUser, username, password);
    }

    @Override
    public String toString() {
        return String.format("User[%d, %s]", idUser, username);
    }
}
