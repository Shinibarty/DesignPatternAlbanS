package com.fges.todoapp.factory;

import com.fges.todoapp.reader.TodoReader;
import com.fges.todoapp.reader.TodoReaderCsv;
import com.fges.todoapp.reader.TodoReaderJson;

public class TodoSourceReaderFactory {
    public static TodoReader createReader(String fileName) {
        if (fileName.endsWith(".json")) {
            return new TodoReaderJson();
        } else if (fileName.endsWith(".csv")) {
            return new TodoReaderCsv();
        } else {
            throw new IllegalArgumentException("Unsupported file format");
        }
    }
}