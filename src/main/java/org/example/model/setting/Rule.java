package org.example.model.setting;

import java.util.List;

public class Rule {
    private String name;
    private List<Condition> condition;
    private Integer count = 0;

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

    public Integer getCount() {
        return count;
    }

    public void addCount() {
        this.count++;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "name='" + name + '\'' +
                ", condition=" + condition +
                '}';
    }
}

