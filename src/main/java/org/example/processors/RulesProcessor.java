package org.example.processors;

import org.example.enums.ProcessorAction;
import org.example.rules.Rule;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RulesProcessor {
    public void process(List<Map<String, Object>> objects, List<Rule> rules, ProcessorAction action) {

        switch (action) {
            case COUNT:
            default:
                count(objects, rules);
                break;
        }
    }

    private void count(List<Map<String, Object>> objects, List<Rule> rules) {
        Map<String, Integer> resultMap = rules.stream().collect(Collectors.toMap(
                Rule::getName,
                rule -> 0
        ));

        for (Map<String, Object> entity : objects) {
            for (Rule rule : rules) {
                if (rule.matches(entity)) {
                    resultMap.compute(rule.getName(), (s, integer) -> integer + 1);
                }
            }
        }
        for (Map.Entry<String, Integer> result : resultMap.entrySet()) {
            System.out.println("name: '" + result.getKey() + "' value: " + result.getValue());
        }
    }

}
