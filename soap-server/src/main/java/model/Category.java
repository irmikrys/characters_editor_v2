package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "categories", schema = "soa_game")
public class Category implements Serializable {

    private static final long serialVersionUID = 5793941245980666186L;

    private Integer idCategory;
    private String name;
    private Integer size;
    private Integer idUser;
    private Collection<Element> elements;

    public Category() {

    }

    public Category(String name, Integer size, Integer idUser) {
        this.name = name;
        this.size = size;
        this.idUser = idUser;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategory", nullable = false)
    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 191)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "size", nullable = false)
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Basic
    @Column(name = "idUser", nullable = false)
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    public Collection<Element> getElements() {
        return elements;
    }

    public void setElements(Collection<Element> elements) {
        this.elements = elements;
    }


    @Override
    public String toString() {
        return String.format("Category[%d, %s, %d]", idCategory, name, size);
    }
}
