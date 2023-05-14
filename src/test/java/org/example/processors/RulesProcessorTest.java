package org.example.processors;

import org.example.enums.ProcessorAction;
import org.example.rules.Condition;
import org.example.rules.Rule;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RulesProcessorTest {

    @Test
    void count() {
        List<Map<String, Object>> objects = new ArrayList<>();
        List<Rule> rules = new ArrayList<>();

        List<Condition> conditions1 = new ArrayList<>(
                List.of(new Condition("type", "in", List.of("Травоядное", "Плотоядное")),
                        new Condition("height", "equals", "Маленькое")));

        Rule rule1 = new Rule("TestName1", conditions1);
        rules.add(rule1);

        List<Condition> conditions2 = new ArrayList<>(
                List.of(new Condition("weight", "equals", "Высокое")));

        Rule rule2 = new Rule("TestName2", conditions2);
        rules.add(rule2);

        Map<String, Object> entity1 = new HashMap<>(Map.of("type", "Плотоядное", "height", "Маленькое"));
        objects.add(entity1);

        Map<String, Object> entity2 = new HashMap<>(Map.of("weight", "Высокое", "height", "Маленькое"));
        objects.add(entity2);

        RulesProcessor rulesProcessor = new RulesProcessor();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        rulesProcessor.process(objects, rules, ProcessorAction.COUNT);

        List<String> outputList = Arrays.asList(baos.toString().split("\\r?\\n"));
        List<String> expectedList = List.of("name: 'TestName1' value: 1", "name: 'TestName2' value: 1");

        assertEquals(expectedList, outputList);

        assertThrows(NullPointerException.class, () -> {
            Map<String, Object> entityThrows = new HashMap<>(Map.of("weight", null, "height", "Маленькое"));
            objects.add(entityThrows);
        });
    }
}
