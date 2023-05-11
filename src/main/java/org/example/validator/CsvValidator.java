package org.example.validator;

import org.example.model.setting.Property;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/*
*  TODO Сделать проверку на то что каждый элимент уникальный (List.size() == Set.size())
* */

public class CsvValidator {

    private List<Property> properties;

    public CsvValidator(List<Property> properties) {
        if (properties.size() != new HashSet<>(properties).size()) {
            throw new RuntimeException();
        }
        this.properties = properties;
    }

    public List<String> getHeaderProperties() {
        return properties.stream()
                .map(Property::getName)
                .collect(Collectors.toList());
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
