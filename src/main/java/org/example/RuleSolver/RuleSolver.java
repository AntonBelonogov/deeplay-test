package org.example.RuleSolver;

import org.example.model.Animal;
import org.example.model.setting.Condition;
import org.example.model.setting.Rule;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class RuleSolver {
    public static String solveRule(Rule rule, List<Animal> animals) {
        String ruleName = rule.getName();
        List<Condition> ruleCondition = rule.getCondition();
        List<Animal> animalList = List.copyOf(animals);
        for (Condition condition : ruleCondition) {
            String conditionProperty = condition.getProperty();
            Object values = condition.getValue();
            List<String> conditionValues = valuesToList(values);
            Field field = getClassType(animals, conditionProperty);
            animalList = animalList.stream().filter(animal -> {
                try {
                    return conditionValues.contains((String) field.get(animal));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
        }
        return String.format("Rule name: %s, count: %d", ruleName, animalList.size());
    }

    private static Field getClassType(List<Animal> animals, String conditionProperty) {
        try {
            Class<?> myClass = animals.stream().findFirst().get().getClass();
            Field field = myClass.getDeclaredField(conditionProperty);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException noSuchFieldException) {
            noSuchFieldException.printStackTrace();
        }
        return null;
    }

    private static List<String> valuesToList(Object values) {
        if (values instanceof String) {
            return List.of((String) values);
        } else {
            return (List<String>) values;
        }
    }
}
