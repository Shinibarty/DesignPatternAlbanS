package com.fges.todoapp;

import java.util.List;

public class TodoPrinterConsole extends TodoPrinter {
    @Override
    public void printTodos(List<Todo> todos, boolean showDone) {
        todos.forEach(todo -> {
            if (showDone && todo.isDone()) {
                System.out.println("- Done: " + todo.getDescription());
            } else if (!showDone && todo.isDone()) {
                System.out.println("- Done: " + todo.getDescription());
            } else if (!showDone && !todo.isDone()) {
                System.out.println("- " + todo.getDescription());
            }
        });
    }
}