package com.fges.todoapp;

import java.io.IOException;
import java.util.List;

public class TodoPrinter {
    private static final TodoReader jsonTodoReader = new TodoReaderJson();
    private static final TodoReader csvTodoReader = new TodoReaderCsv();
    private static final TodoPrinter consoleTodoPrinter = new TodoPrinterConsole();

    public void printTodos(List<Todo> todos, boolean showDone) {
        consoleTodoPrinter.printTodos(todos, showDone);
    }

    public static void printTodosFromJson(String jsonContent, boolean showDone) throws IOException {
        List<Todo> todos = jsonTodoReader.readTodos(jsonContent);
        consoleTodoPrinter.printTodos(todos, showDone);
    }

    public static void printTodosFromCsv(String csvContent, boolean showDone) throws IOException {
        List<Todo> todos = csvTodoReader.readTodos(csvContent);
        consoleTodoPrinter.printTodos(todos, showDone);
    }
}