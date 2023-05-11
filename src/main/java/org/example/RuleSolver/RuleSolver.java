package org.example.RuleSolver;

import org.example.model.Animal;
import org.example.model.setting.Condition;
import org.example.model.setting.Rule;

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
            animalList = animalList.stream().filter(animal -> {
                boolean isValid = true;
                switch (conditionProperty) {
                    case "weight":
                        isValid = conditionValues.contains(animal.getWeight());
                        break;
                    case "height":
                        isValid = conditionValues.contains(animal.getHeight());
                        break;
                    case "type":
                        isValid = conditionValues.contains(animal.getType());
                        break;
                }
                return isValid;
            }).collect(Collectors.toList());
        }
        return String.format("Rule name: %s, count: %d", ruleName, animalList.size());
    }

    private static List<String> valuesToList(Object values) {
        if (values instanceof String) {
            return List.of((String) values);
        } else {
            return (List<String>) values;
        }
    }
}
