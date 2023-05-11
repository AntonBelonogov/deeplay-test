package org.example.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.setting.JsonFile;
import org.example.model.setting.Rule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RuleReader {

    public List<Rule> readRules(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filename), JsonFile.class).getRules();
    }
}
