package org.example.model.setting;

import java.util.List;

public class Property {
    private String name;
    private List<PropertyValue> values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PropertyValue> getValues() {
        return values;
    }

    public void setValues(List<PropertyValue> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", values=" + values +
                '}';
    }
}

