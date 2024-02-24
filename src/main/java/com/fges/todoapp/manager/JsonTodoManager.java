package com.fges.todoapp.manager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fges.todoapp.io.FileHandler;
import com.fges.todoapp.model.Todo;
import com.fges.todoapp.printer.TodoPrinter;
import com.fges.todoapp.reader.TodoReaderJson;
import com.fges.todoapp.writer.TodoWriterJson;

import java.io.IOException;

public class JsonTodoManager implements TodoManager {

    private final TodoReaderJson todoReaderJson;
    private final TodoWriterJson todoWriterJson;

    public JsonTodoManager(TodoReaderJson todoReaderJson, TodoWriterJson todoWriterJson) {
        this.todoReaderJson = todoReaderJson;
        this.todoWriterJson = todoWriterJson;
    }

    @Override
    public void insertTodo(String fileName, Todo todo) throws IOException {
        todoWriterJson.writeTodo(fileName, todo);
    }

    @Override
    public void listTodos(String fileName, boolean showDone) throws IOException {
        String fileContent = FileHandler.readFileContent(fileName);
        TodoPrinter.printTodosFromJson(fileContent, showDone);
    }
}
