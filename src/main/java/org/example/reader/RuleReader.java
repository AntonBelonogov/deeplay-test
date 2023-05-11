package org.example.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.setting.JsonFile;
import org.example.model.setting.Rule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RuleReader {

    public List<Rule> readRules(String filename) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(filename), JsonFile.class).getRules();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
