package com.fges.todoapp.manager;

import com.fges.todoapp.io.FileHandler;
import com.fges.todoapp.model.Todo;
import com.fges.todoapp.printer.TodoPrinter;

import java.io.IOException;

public class CsvTodoManager implements TodoManager {
    @Override
    public void insertTodo(String fileName, Todo todo) throws IOException {
        String fileContent = FileHandler.readFileContent(fileName);

        String todoStr = String.format("%s,,,%b", todo.getDescription(), todo.isDone());

        if (!fileContent.endsWith("\n") && !fileContent.isEmpty()) {
            fileContent += "\n";
        }
        fileContent += todoStr;

        FileHandler.writeToFile(fileName, fileContent);
    }

    @Override
    public void listTodos(String fileName, boolean showDone) throws IOException {
        String fileContent = FileHandler.readFileContent(fileName);
        TodoPrinter.printTodosFromCsv(fileContent, showDone);
    }
}