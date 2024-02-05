package com.fges.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JsonTodoManager implements TodoManager {
    @Override
    public void insertTodo(String fileName, List<String> todos) throws IOException {
        // Logique d'insertion pour JSON
        String fileContent = FileHandler.readFileContent(fileName);

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

    @Override
    public void listTodos(String fileName) throws IOException {
        // Logique d'affichage pour JSON
        String fileContent = FileHandler.readFileContent(fileName);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(fileContent);
        if (actualObj instanceof MissingNode) {
            actualObj = JsonNodeFactory.instance.arrayNode();
        }

        if (actualObj instanceof ArrayNode arrayNode) {
            arrayNode.forEach(node -> System.out.println("- " + node.toString()));
        }
    }
}

