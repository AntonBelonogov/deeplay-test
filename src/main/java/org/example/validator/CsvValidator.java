package org.example.validator;

import org.example.model.setting.Property;
import org.example.model.setting.PropertyValue;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class CsvValidator {

    private List<Property> properties;

    public CsvValidator(List<Property> properties) {
        if (properties.size() != new HashSet<>(properties).size()) {
            throw new RuntimeException("Headers can't have duplicates");
        }
        this.properties = properties;
    }

    public List<String> getHeaderProperties() {
        return properties.stream()
                .map(Property::getName)
                .collect(Collectors.toList());
    }

    public void validateEntity(List<?> list) {
        if (!getHeaderProperties().equals(getClassFieldsName(list))) {
            throw new IllegalArgumentException("File header not equals to class fields.");
        }
    }

    public Map<String, List<String>> validateValues() {
        Map<String, List<String>> fieldChecker = new HashMap<>();
        for (Property property : properties) {
            String propertyName = property.getName();
            List<String> propertyValues = property.getValues().stream()
                    .map(PropertyValue::getName)
                    .collect(Collectors.toList());
            fieldChecker.put(propertyName, propertyValues);
        }
        return fieldChecker;
    }

    private static List<String> getClassFieldsName(List<?> list) {
        Class<?> myClass = list.stream().findFirst().get().getClass();
        Field[] fields = myClass.getDeclaredFields();
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }
}
