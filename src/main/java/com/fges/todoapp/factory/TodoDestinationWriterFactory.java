package com.fges.todoapp.factory;

import com.fges.todoapp.writer.TodoWriter;
import com.fges.todoapp.writer.TodoWriterCsv;
import com.fges.todoapp.writer.TodoWriterJson;

public class TodoDestinationWriterFactory {
    public static TodoWriter createWriter(String fileName) {
        if (fileName.endsWith(".json")) {
            return new TodoWriterJson();
        } else if (fileName.endsWith(".csv")) {
            return new TodoWriterCsv();
        } else {
            throw new IllegalArgumentException("Unsupported file format");
        }
    }
}