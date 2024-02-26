package com.fges.todoapp.manager;

import com.fges.todoapp.io.FileHandler;
import com.fges.todoapp.model.Todo;
import com.fges.todoapp.printer.TodoPrinter;
import com.fges.todoapp.reader.TodoReaderCsv;
import com.fges.todoapp.writer.TodoWriterCsv;

import java.io.IOException;

public class CsvTodoManager implements TodoManager {

    private final TodoReaderCsv todoReaderCsv;
    private final TodoWriterCsv todoWriterCsv;

    public CsvTodoManager(TodoReaderCsv todoReaderCsv, TodoWriterCsv todoWriterCsv) {
        this.todoReaderCsv = todoReaderCsv;
        this.todoWriterCsv = todoWriterCsv;
    }

    @Override
    public void insertTodo(String fileName, Todo todo) throws IOException {
        todoWriterCsv.writeTodo(fileName, todo);
    }

    @Override
    public void listTodos(String fileName, boolean showDone) throws IOException {
        String fileContent = FileHandler.readFileContent(fileName);
        TodoPrinter.printTodosFromCsv(fileContent, showDone);
    }
}