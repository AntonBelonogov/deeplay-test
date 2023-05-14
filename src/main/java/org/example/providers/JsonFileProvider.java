package org.example.providers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.execeptions.ReaderException;
import org.example.rules.Rule;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonFileProvider implements DataProvider {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Map<String, Object>> readData(String[] args) {
        File file = new File(args[0]);
        try {
            return (List<Map<String, Object>>) mapper.readValue(file, Object.class);
        } catch (IOException exception) {
            throw new ReaderException(exception.getMessage());
        }
    }

    @Override
    public List<Rule> getRules(String[] args) {
        File file = new File(args[0]);
        try {
            return mapper.readValue(file, new TypeReference<>(){});
        } catch (IOException exception) {
            throw new ReaderException(exception.getMessage());
        }
    }
}
