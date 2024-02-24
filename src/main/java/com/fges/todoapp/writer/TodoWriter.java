package com.fges.todoapp.writer;

import com.fges.todoapp.model.Todo;

import java.io.IOException;
import java.util.List;

public interface TodoWriter {
    void writeTodo(String fileName, Todo todo) throws IOException;
}