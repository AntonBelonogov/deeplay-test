package org.example.reader;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.example.model.Animal;
import org.example.validator.CsvValidator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnimalReader {
    private static final CsvMapper csvMapper = new CsvMapper();
    private static final CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();

    public static List<Animal> readEntity(String csvFile, CsvValidator csvValidator) {
        try {
            MappingIterator<Animal> animalIterator = csvMapper.readerWithSchemaFor(Animal.class)
                    .with(csvSchema)
                    .readValues(new File(csvFile));
            List<Animal> animalList = new ArrayList<>();
            Map<String, List<String>> fieldChecker = csvValidator.validateValues();

            while (animalIterator.hasNext()) {
                Animal animal = animalIterator.next();
                if (validateAnimal(animal, fieldChecker)) {
                    throw new IllegalArgumentException("Invalid value for field.");
                }
                animalList.add(animal);

            }
            return animalList;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private static boolean validateAnimal(Animal animal, Map<String, List<String>> fieldChecker) {
        return !fieldChecker.get("weight").contains(animal.getWeight()) ||
                !fieldChecker.get("height").contains(animal.getHeight()) ||
                !fieldChecker.get("type").contains(animal.getType());
    }

}
