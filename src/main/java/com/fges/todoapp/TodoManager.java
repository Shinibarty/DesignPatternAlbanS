package com.fges.todoapp;

import java.io.IOException;
import java.util.List;

public interface TodoManager {
    void insertTodo(String fileName, List<String> todos) throws IOException;
    void listTodos(String fileName) throws IOException;

}