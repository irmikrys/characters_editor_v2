package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "typesSets", schema = "soa_game")
public class TypeSet implements Serializable {

    private static final long serialVersionUID = -4202787494386299974L;

    private int idTypeSet;
    private String categoryType;
    private String sizeType;
    private String elementType;
    private String elementFortune;
    private String elementProp;
    private Collection<User> users;

    public TypeSet() {

    }

    @Id
    @Column(name = "idTypeSet", nullable = false)
    public int getIdTypeSet() {
        return idTypeSet;
    }

    public void setIdTypeSet(int idTypeSet) {
        this.idTypeSet = idTypeSet;
    }

    @Basic
    @Column(name = "categoryType", nullable = false, length = 191)
    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    @Basic
    @Column(name = "sizeType", nullable = false, length = 255)
    public String getSizeType() {
        return sizeType;
    }

    public void setSizeType(String sizeType) {
        this.sizeType = sizeType;
    }

    @Basic
    @Column(name = "elementType", nullable = false, length = 191)
    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    @Basic
    @Column(name = "elementFortune", nullable = false, length = 255)
    public String getElementFortune() {
        return elementFortune;
    }

    public void setElementFortune(String elementFortune) {
        this.elementFortune = elementFortune;
    }

    @Basic
    @Column(name = "elementProp", nullable = false, length = 255)
    public String getElementProp() {
        return elementProp;
    }

    public void setElementProp(String elementProp) {
        this.elementProp = elementProp;
    }

    @OneToMany(mappedBy = "typeSet", fetch = FetchType.EAGER)
    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeSet typeSet = (TypeSet) o;
        return idTypeSet == typeSet.idTypeSet &&
                Objects.equals(categoryType, typeSet.categoryType) &&
                Objects.equals(sizeType, typeSet.sizeType) &&
                Objects.equals(elementType, typeSet.elementType) &&
                Objects.equals(elementFortune, typeSet.elementFortune) &&
                Objects.equals(elementProp, typeSet.elementProp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idTypeSet, categoryType, sizeType, elementType, elementFortune, elementProp);
    }

    @Override
    public String toString() {
        return String.format("TypeSet[%d, %s, %s, %s, %s, %s]",
                idTypeSet, categoryType, sizeType, elementType, elementFortune, elementProp);
    }
}
