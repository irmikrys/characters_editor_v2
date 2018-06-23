package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "elements", schema = "soa_game")
public class Element implements Serializable {

    private static final long serialVersionUID = -5383214602565441079L;

    private int idElement;
    private String name;
    private int fortune;
    private int property;
    private int power;
    private Category category;
    
    public Element() {

    }

    public Element(Category category, String name, int fortune, int property, int power) {
        this.category = category;
        this.name = name;
        this.fortune = fortune;
        this.property = property;
        this.power = power;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idElement", nullable = false)
    public int getIdElement() {
        return idElement;
    }

    public void setIdElement(int idElement) {
        this.idElement = idElement;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "fortune", nullable = false)
    public int getFortune() {
        return fortune;
    }

    public void setFortune(int arrowsNum) {
        this.fortune = arrowsNum;
    }

    @Column(name = "property", nullable = false)
    public int getProperty() {
        return property;
    }

    public void setProperty(int crossbow) {
        this.property = crossbow;
    }

    @Column(name = "power", nullable = false)
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategory", referencedColumnName = "idCategory", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category CategoryByIdCategory) {
        this.category = CategoryByIdCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return idElement == element.idElement &&
                fortune == element.fortune &&
                property == element.property &&
                power == element.power &&
                Objects.equals(name, element.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idElement, name, fortune, property, power);
    }

    @Override
    public String toString() {
        return String.format("Element[%d, %s, %d, %d]", idElement, name, property, power);
    }
}
