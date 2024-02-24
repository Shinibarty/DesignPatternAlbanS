package com.fges.todoapp.reader;

import com.fges.todoapp.model.Todo;

import java.io.IOException;
import java.util.List;

public interface TodoReader {
    List<Todo> readTodos(String content) throws IOException;
}