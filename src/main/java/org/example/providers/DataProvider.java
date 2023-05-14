package org.example.providers;

import org.example.rules.Rule;

import java.util.List;
import java.util.Map;

public interface DataProvider {
    List<Map<String,Object>> readData(String[] args);

    List<Rule> getRules(String[] args);
}
