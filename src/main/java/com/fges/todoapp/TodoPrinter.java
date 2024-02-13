package com.fges.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TodoPrinter {
    public static void printTodos(List<Todo> todos, boolean showDone) {
        todos.forEach(todo -> {
            if (showDone && todo.isDone()) {
                System.out.println("Done: " + todo.getDescription());
            } else if (!showDone && todo.isDone()) {
                System.out.println("Done: " + todo.getDescription());
            } else if (!showDone && !todo.isDone()) {
                System.out.println(todo.getDescription());
            }
        });
    }

    public static void printTodosFromJson(String jsonContent, boolean showDone) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(jsonContent);
        if (actualObj instanceof MissingNode) {
            actualObj = JsonNodeFactory.instance.arrayNode();
        }

        if (actualObj instanceof ArrayNode arrayNode) {
            List<Todo> todos = new ArrayList<>();
            arrayNode.forEach(node -> todos.add(mapper.convertValue(node, Todo.class)));

            printTodos(todos, showDone);
        }
    }

    public static void printTodosFromCsv(String csvContent, boolean showDone) {
        List<String> descriptions = Arrays.asList(csvContent.split("\n"));

        List<Todo> todos = descriptions.stream()
                .map(description -> new Todo(description.trim(), false)) // Assuming todos from CSV are not done
                .collect(Collectors.toList());

        printTodos(todos, showDone);
    }
}

