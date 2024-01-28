package com.fges.todoapp;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TodoManager {
    public static void insertTodo(String fileName, List<String> todos) throws IOException {
        String fileContent = FileHandler.readFileContent(fileName);

        if (fileName.endsWith(".json")) {
            // Logique d'insertion pour JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(fileContent);
            if (actualObj instanceof MissingNode) {
                actualObj = JsonNodeFactory.instance.arrayNode();
            }

            if (actualObj instanceof ArrayNode arrayNode) {
                arrayNode.addAll(todos.stream().map(JsonNodeFactory.instance::textNode).collect(Collectors.toList()));
            }

            FileHandler.writeToFile(fileName, actualObj.toString());
        }

        if (fileName.endsWith(".csv")) {
            // Logique d'insertion pour CSV
            String todoStr = todos.stream().collect(Collectors.joining("\n"));
            if (!fileContent.endsWith("\n") && !fileContent.isEmpty()) {
                fileContent += "\n";
            }
            fileContent += todoStr;

            FileHandler.writeToFile(fileName, fileContent);
        }
    }

    public static void listTodos(String fileName) throws IOException {
        String fileContent = FileHandler.readFileContent(fileName);

        if (fileName.endsWith(".json")) {
            // Logique d'affichage pour JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(fileContent);
            if (actualObj instanceof MissingNode) {
                actualObj = JsonNodeFactory.instance.arrayNode();
            }

            if (actualObj instanceof ArrayNode arrayNode) {
                arrayNode.forEach(node -> System.out.println("- " + node.toString()));
            }
        }

        if (fileName.endsWith(".csv")) {
            // Logique d'affichage pour CSV
            System.out.println(Arrays.stream(fileContent.split("\n"))
                    .map(todo -> "- " + todo)
                    .collect(Collectors.joining("\n"))
            );
        }
    }
}