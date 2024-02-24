package com.fges.todoapp.reader;

import com.fges.todoapp.model.Todo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TodoReaderCsv implements TodoReader {
    @Override
    public List<Todo> readTodos(String content) {
        List<String> lines = Arrays.asList(content.split("\n"));

        return lines.stream()
                .map(line -> {
                    String[] parts = line.split(",,,");
                    if (parts.length >= 2) {
                        String description = parts[0].trim();
                        boolean isDone = Boolean.parseBoolean(parts[1].trim());
                        return new Todo(description, isDone);
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}