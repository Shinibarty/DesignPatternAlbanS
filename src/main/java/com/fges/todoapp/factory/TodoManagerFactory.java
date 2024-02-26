package com.fges.todoapp.factory;

import com.fges.todoapp.manager.CsvTodoManager;
import com.fges.todoapp.manager.JsonTodoManager;
import com.fges.todoapp.manager.TodoManager;
import com.fges.todoapp.reader.TodoReaderCsv;
import com.fges.todoapp.reader.TodoReaderJson;
import com.fges.todoapp.writer.TodoWriterCsv;
import com.fges.todoapp.writer.TodoWriterJson;

public class TodoManagerFactory {
    public static TodoManager getTodoManager(String fileName) {
        if (fileName.endsWith(".json")) {
            TodoReaderJson todoReaderJson = new TodoReaderJson();
            TodoWriterJson todoWriterJson = new TodoWriterJson();
            return new JsonTodoManager(todoReaderJson, todoWriterJson);
        } else if (fileName.endsWith(".csv")) {
            TodoReaderCsv todoReaderCsv = new TodoReaderCsv();
            TodoWriterCsv todoWriterCsv = new TodoWriterCsv();
            return new CsvTodoManager(todoReaderCsv, todoWriterCsv);
        } else {
            throw new IllegalArgumentException("Unsupported file format");
        }
    }
}