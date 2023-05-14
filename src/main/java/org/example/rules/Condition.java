package org.example.rules;

import java.util.List;
import java.util.Map;

public class Condition {
    private String property;
    private String type;
    private Object value;

    public Condition(String property, String type, Object value) {
        this.property = property;
        this.type = type;
        this.value = value;
    }

    public Condition() {
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean match(Map<String, Object> entity) {
        Object attribute = entity.get(property);

        if (attribute == null) {
            return false;
        }

        switch (type) {
            case "in":
                return ((List<Object>) this.value).contains(attribute);
            case "notIn":
                return !((List<Object>) this.value).contains(attribute);
            case "equals":
                return attribute.equals(value);
            case "notEquals":
                return !attribute.equals(value);
            default:
                return false;
        }
    }
}

