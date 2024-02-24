package com.fges.todoapp.writer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fges.todoapp.io.FileHandler;
import com.fges.todoapp.model.Todo;

import java.io.IOException;
import java.util.List;

public class TodoWriterJson implements TodoWriter {
    @Override
    public void writeTodo(String fileName, Todo todo) throws IOException {
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
}

