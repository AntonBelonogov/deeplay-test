package org.example;

import org.example.model.Animal;
import org.example.model.setting.Property;
import org.example.model.setting.Rule;
import org.example.reader.AnimalReader;
import org.example.reader.PropertyReader;
import org.example.reader.RuleReader;
import org.example.validator.CsvValidator;

import java.io.IOException;
import java.util.List;

import static org.example.RuleSolver.RuleSolver.solveRule;

/*
 * TODO Сделать чтобы в CSV игнорился регистр
 * */


public class Main {
    public static void main(String[] args) throws IOException {
        PropertyReader propertyReader = new PropertyReader();
        RuleReader ruleReader = new RuleReader();
        List<Property> properties = propertyReader.readProperties("src/main/resources/properties.json");

        CsvValidator csvValidator = new CsvValidator(properties);

        List<String> animalHeader = csvValidator.getHeaderProperties();

        List<Animal> animals = AnimalReader.readEntity("src/main/resources/data.csv");

        List<Rule> rules = ruleReader.readRules("src/main/resources/rules.json");

        for (Rule rule : rules) {
            System.out.println(solveRule(rule, animals));
        }
    }
}