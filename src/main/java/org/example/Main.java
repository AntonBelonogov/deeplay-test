package org.example;

import org.example.model.Animal;
import org.example.model.setting.Property;
import org.example.model.setting.Rule;
import org.example.reader.AnimalReader;
import org.example.reader.PropertyReader;
import org.example.reader.RuleReader;
import org.example.validator.CsvValidator;

import java.util.List;

import static org.example.ruleSolver.RuleSolver.solveRule;

/*
* При запуске использовать файл строку args: "src/main/resources/properties.json" "src/main/resources/data.csv" "src/main/resources/rules.json"
* */

public class Main {
    public static void main(String[] args) {
        PropertyReader propertyReader = new PropertyReader();
        RuleReader ruleReader = new RuleReader();

        List<Property> properties = propertyReader.readProperties(args[0]);
        CsvValidator csvValidator = new CsvValidator(properties);
        List<Animal> animals = AnimalReader.readEntity(args[1], csvValidator);
        csvValidator.validateEntity(animals);
        List<Rule> rules = ruleReader.readRules(args[2]);
        rules.forEach(rule -> solveRule(rule, animals));
    }
}