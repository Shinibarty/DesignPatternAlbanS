package com.fges.todoapp.reader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fges.todoapp.model.Todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodoReaderJson implements TodoReader {
    @Override
    public List<Todo> readTodos(String content) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(content);
        if (actualObj instanceof MissingNode) {
            actualObj = JsonNodeFactory.instance.arrayNode();
        }

        if (actualObj instanceof ArrayNode arrayNode) {
            List<Todo> todos = new ArrayList<>();
            arrayNode.forEach(node -> todos.add(mapper.convertValue(node, Todo.class)));
            return todos;
        }
        return new ArrayList<>();
    }
}