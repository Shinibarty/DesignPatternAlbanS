package com.fges.todoapp.writer;

import com.fges.todoapp.io.FileHandler;
import com.fges.todoapp.model.Todo;

import java.io.IOException;

public class TodoWriterCsv implements TodoWriter {

    @Override
    public void writeTodo(String fileName, Todo todo) throws IOException {
        String fileContent = FileHandler.readFileContent(fileName);

        String todoStr = String.format("%s,,,%b", todo.getDescription(), todo.isDone());

        if (!fileContent.endsWith("\n") && !fileContent.isEmpty()) {
            fileContent += "\n";
        }
        fileContent += todoStr;

        FileHandler.writeToFile(fileName, fileContent);
    }
}
