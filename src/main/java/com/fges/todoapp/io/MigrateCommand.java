package com.fges.todoapp.io;

import com.fges.todoapp.manager.TodoManager;
import com.fges.todoapp.factory.TodoSourceReaderFactory;
import com.fges.todoapp.factory.TodoDestinationWriterFactory;
import com.fges.todoapp.model.Todo;
import com.fges.todoapp.reader.TodoReader;
import com.fges.todoapp.writer.TodoWriter;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.List;

public class MigrateCommand {
    public void migrate(String sourceFileName, String destinationFileName) throws IOException {
        TodoReader sourceReader = TodoSourceReaderFactory.createReader(sourceFileName);
        TodoWriter destinationWriter = TodoDestinationWriterFactory.createWriter(destinationFileName);
        String sourceFileContent = FileHandler.readFileContent(sourceFileName);

        List<Todo> todos = sourceReader.readTodos(sourceFileContent);
        for (Todo todo : todos) {
            destinationWriter.writeTodo(destinationFileName, todo);
        }

        System.err.println("Migration completed.");
    }
}