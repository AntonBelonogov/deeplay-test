package org.example.ruleSolver;

import org.example.model.Animal;
import org.example.model.setting.Condition;
import org.example.model.setting.Rule;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class RuleSolver {
    public static void solveRule(Rule rule, List<?> list) {
        String ruleName = rule.getName();
        List<Condition> ruleCondition = rule.getCondition();
        List<?> copyList = List.copyOf(list);
        for (Condition condition : ruleCondition) {
            String conditionProperty = condition.getProperty();
            Object values = condition.getValue();
            List<String> conditionValues = valuesToList(values);
            Field field = getClassType(list, conditionProperty);
            copyList = copyList.stream().filter(animal -> {
                try {
                    return conditionValues.contains((String) field.get(animal));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
        }
        System.out.println(String.format("Rule name: %s, count: %d", ruleName, copyList.size()));
    }

    private static Field getClassType(List<?> list, String conditionProperty) {
        try {
            Class<?> newClass = list.stream().findFirst().get().getClass();
            Field field = newClass.getDeclaredField(conditionProperty);
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
