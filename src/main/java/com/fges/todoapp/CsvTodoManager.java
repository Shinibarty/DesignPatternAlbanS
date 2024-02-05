package com.fges.todoapp;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvTodoManager implements TodoManager {
    @Override
    public void insertTodo(String fileName, List<String> todos) throws IOException {
        // Logique d'insertion pour CSV
        String fileContent = FileHandler.readFileContent(fileName);

        String todoStr = todos.stream().collect(Collectors.joining("\n"));
        if (!fileContent.endsWith("\n") && !fileContent.isEmpty()) {
            fileContent += "\n";
        }
        fileContent += todoStr;

        FileHandler.writeToFile(fileName, fileContent);
    }

    @Override
    public void listTodos(String fileName) throws IOException {
        // Logique d'affichage pour CSV
        String fileContent = FileHandler.readFileContent(fileName);

        System.out.println(Arrays.stream(fileContent.split("\n"))
                .map(todo -> "- " + todo)
                .collect(Collectors.joining("\n"))
        );
    }
}

