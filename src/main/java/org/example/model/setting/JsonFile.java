package org.example.model.setting;

import java.util.List;

public class JsonFile {
    private List<Property> properties;
    private List<Rule> rules;

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public String toString() {
        return "JsonFile{" +
                "properties=" + properties +
                ", rules=" + rules +
                '}';
    }
}
