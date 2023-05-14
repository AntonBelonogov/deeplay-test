package org.example.rules;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RuleTest {

    @Test
    void matches() {
        List<Condition> conditions = new ArrayList<>(
                List.of(new Condition("type", "in", List.of("Травоядное", "Плотоядное")),
                        new Condition("height", "equals", "Маленькое")));

        Rule rule = new Rule("TestName", conditions);

        Map<String, Object> entityFalse = new HashMap<>(Map.of("type", "TestType", "height", "Маленькое"));
        assertFalse(rule.matches(entityFalse));

        Map<String, Object> entityTrue = new HashMap<>(Map.of("type", "Травоядное", "height", "Маленькое"));
        assertTrue(rule.matches(entityTrue));

        assertThrows(NullPointerException.class, () -> {
            Map<String, Object> entityThrows = new HashMap<>(Map.of("type", null, null, "Маленькое"));
            rule.matches(entityThrows);
        });
    }
}