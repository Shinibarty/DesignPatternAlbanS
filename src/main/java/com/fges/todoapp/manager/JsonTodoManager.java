package com.fges.todoapp.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fges.todoapp.io.FileHandler;
import com.fges.todoapp.model.Todo;
import com.fges.todoapp.printer.TodoPrinter;

import java.io.IOException;

public class JsonTodoManager implements TodoManager {
    @Override
    public void insertTodo(String fileName, Todo todo) throws IOException {
        String fileContent = FileHandler.readFileContent(fileName);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(fileContent);
        if (actualObj instanceof MissingNode) {
            actualObj = JsonNodeFactory.instance.arrayNode();
        }

        if (actualObj instanceof ArrayNode arrayNode) {
            arrayNode.add(mapper.valueToTree(todo));
        }

        FileHandler.writeToFile(fileName, actualObj.toString());
    }

    @Override
    public void listTodos(String fileName, boolean showDone) throws IOException {
        String fileContent = FileHandler.readFileContent(fileName);
        TodoPrinter.printTodosFromJson(fileContent, showDone);
    }
}