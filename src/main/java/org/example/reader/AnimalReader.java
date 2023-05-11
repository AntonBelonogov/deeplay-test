package org.example.reader;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.example.model.Animal;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AnimalReader {
    private static final CsvMapper csvMapper = new CsvMapper();
    private static final CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
    public static List<Animal> readEntity(String csvFile) throws IOException {
        MappingIterator<Animal> animalIterator = csvMapper.readerWithSchemaFor(Animal.class)
                .with(csvSchema)
                .readValues(new File(csvFile));
        return animalIterator.readAll();
    }

}
