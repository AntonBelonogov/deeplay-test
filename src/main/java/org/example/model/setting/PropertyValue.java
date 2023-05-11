package org.example.model.setting;

public class PropertyValue {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PropertyValue{" +
                "name='" + name + '\'' +
                '}';
    }
}
