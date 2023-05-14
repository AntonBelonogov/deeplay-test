package org.example;

import org.example.enums.Action;
import org.example.enums.ProcessorAction;
import org.example.processors.RulesProcessor;
import org.example.providers.DataProvider;
import org.example.providers.JsonFileProvider;
import org.example.rules.Rule;

import java.util.*;

public class Main {

    private final static Map<String, DataProvider> providers = Map.of("jsonFile", new JsonFileProvider());
    private static List<Map<String, Object>> entities = new ArrayList<>();

    private static RulesProcessor rulesProcessor = new RulesProcessor();

    public static void main(String[] args) {
        String providerType;
        DataProvider dataProvider;
        ProcessorAction processorAction;


        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Введите команду:");
                String command = scanner.nextLine();
                String[] parsedCommand = command.split(" ");
                Queue<String> argsQueue = new ArrayDeque<>(List.of(parsedCommand));

                Action action = Action.valueOf(argsQueue.poll().toUpperCase());

                switch (action) {
                    case LOAD:
                        providerType = argsQueue.poll();
                        dataProvider = providers.get(providerType);
                        try {
                            entities = dataProvider.readData(argsQueue.toArray(new String[0]));
                            System.out.println("Объектов загружено: " + entities.size());
                        } catch (Exception e) {
                            System.out.println("Ошибка при загрузке данных: " + e.getMessage());
                        }
                        break;
                    case PROCESS:
                        processorAction = ProcessorAction.valueOf(argsQueue.poll().toUpperCase());
                        providerType = argsQueue.poll();
                        dataProvider = providers.get(providerType);
                        List<Rule> rules = dataProvider.getRules(argsQueue.toArray(new String[0]));
                        rulesProcessor.process(entities, rules, processorAction);
                        break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}