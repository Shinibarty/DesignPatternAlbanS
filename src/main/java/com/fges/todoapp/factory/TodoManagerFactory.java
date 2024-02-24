package com.fges.todoapp.factory;

import com.fges.todoapp.manager.CsvTodoManager;
import com.fges.todoapp.manager.JsonTodoManager;
import com.fges.todoapp.manager.TodoManager;

public class TodoManagerFactory {
    public static TodoManager getTodoManager(String fileName) {
        if (fileName.endsWith(".json")) {
            return new JsonTodoManager();
        } else if (fileName.endsWith(".csv")) {
            return new CsvTodoManager();
        } else {
            throw new IllegalArgumentException("Unsupported file format");
        }
    }
}