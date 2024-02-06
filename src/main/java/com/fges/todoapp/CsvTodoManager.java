package com.fges.todoapp;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvTodoManager implements TodoManager {
    @Override
    public void insertTodo(String fileName, Todo todo) throws IOException {
        String fileContent = FileHandler.readFileContent(fileName);

        String todoStr = Arrays.asList(todo.getDescription()).stream().collect(Collectors.joining("\n"));
        if (!fileContent.endsWith("\n") && !fileContent.isEmpty()) {
            fileContent += "\n";
        }
        fileContent += todoStr;

        FileHandler.writeToFile(fileName, fileContent);
    }

    @Override
    public void listTodos(String fileName) throws IOException {
        String fileContent = FileHandler.readFileContent(fileName);

        System.out.println(Arrays.stream(fileContent.split("\n"))
                .map(description -> "- " + description)
                .collect(Collectors.joining("\n"))
        );
    }
}


