package org.example.model.setting;

public class Condition {
    private String property;
    private Object value;

    public Condition(String property, Object value) {
        this.property = property;
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

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "Condition{" +
                "property='" + property + '\'' +
                ", value=" + value +
                '}';
    }
}

