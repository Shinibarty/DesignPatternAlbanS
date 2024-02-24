package com.fges.todoapp.manager;

import com.fges.todoapp.model.Todo;

import java.io.IOException;

public interface TodoManager {
    void insertTodo(String fileName, Todo todo) throws IOException;
    void listTodos(String fileName, boolean done) throws IOException;
}