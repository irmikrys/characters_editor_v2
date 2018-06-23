package model;

import dto.ElementDTO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "elements", schema = "soa_game")
public class Element implements Serializable {

    private static final long serialVersionUID = -5383214602565441079L;

    private Integer idElement;
    private String name;
    private Integer fortune;
    private Integer property;
    private Integer power;
    private Category category;

    public Element() {

    }

    public Element(ElementDTO elementDTO, Category category) {
        this.category = category;
        this.name = elementDTO.getName();
        this.fortune = elementDTO.getFortune();
        this.property = elementDTO.getProperty();
        this.power = elementDTO.getPower();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idElement", nullable = false)
    public Integer getIdElement() {
        return idElement;
    }

    public void setIdElement(Integer idElement) {
        this.idElement = idElement;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "fortune", nullable = false)
    public Integer getFortune() {
        return fortune;
    }

    public void setFortune(Integer fortune) {
        this.fortune = fortune;
    }

    @Basic
    @Column(name = "property", nullable = false)
    public Integer getProperty() {
        return property;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    @Basic
    @Column(name = "power", nullable = false)
    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategory", referencedColumnName = "idCategory", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("Element[%d, %s, %d, %d]", idElement, name, property, power);
    }
}
