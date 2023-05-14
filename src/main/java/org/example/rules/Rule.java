package org.example.rules;

import java.util.List;
import java.util.Map;

public class Rule {
    private String name;
    private List<Condition> condition;

    public Rule(String name, List<Condition> condition) {
        this.name = name;
        this.condition = condition;
    }

    public Rule() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Condition> getCondition() {
        return condition;
    }

    public void setCondition(List<Condition> condition) {
        this.condition = condition;
    }

    public boolean matches(Map<String, Object> entity) {
        for (Condition condition: condition) {
            if(!condition.match(entity)) {
                return false;
            }
        }
        return true;
    }
}

