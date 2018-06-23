package model;

import java.io.Serializable;

public class TreeNodeData implements Serializable {

    private static final long serialVersionUID = 2291949203014029830L;

    private String type;
    private Integer id;
    private String name;
    private String typeSetType;
    private String owner;
    private String properties;

    public TreeNodeData(String type, Integer id, String name, String typeSetType, String owner,
                        String properties) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.typeSetType = typeSetType;
        this.owner = owner;
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeSetType() {
        return typeSetType;
    }

    public void setTypeSetType(String typeSetType) {
        this.typeSetType = typeSetType;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }
}
