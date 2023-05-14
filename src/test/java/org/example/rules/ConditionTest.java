package org.example.rules;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ConditionTest {

    @Test
    void match() {
        Condition condition = new Condition("type", "equals", "Травоядное");

        assertThrows(NullPointerException.class, () -> {
            Map<String, Object> entityEmpty = new HashMap<>(null);
            condition.match(entityEmpty);
        });

        Map<String, Object> entityTypeFalse = new HashMap<>(Map.of("notType", "Травоядное"));
        assertFalse(condition.match(entityTypeFalse));

        Map<String, Object> entityValueFalse = new HashMap<>(Map.of("notType", "Травоядное"));
        assertFalse(condition.match(entityValueFalse));

        Map<String, Object> entityTrue = new HashMap<>(Map.of("type", "Травоядное"));
        assertTrue(condition.match(entityTrue));


    }
}