package org.example.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.setting.JsonFile;
import org.example.model.setting.Property;

import java.io.File;
import java.io.IOException;
import java.util.List;

/*
*  TODO Сделать отдельный класс для Properties и Rules вместо того чтобы использовать JSON
* */


public class PropertyReader {
    public List<Property> readProperties(String filename) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(filename), JsonFile.class).getProperties();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
