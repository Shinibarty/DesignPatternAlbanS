package com.fges.todoapp;

import java.io.IOException;
import java.util.List;

public interface TodoReader {
    List<Todo> readTodos(String content) throws IOException;
}

