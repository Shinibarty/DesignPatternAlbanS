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
    public static void migrate(CommandLine cmd) throws IOException {
        String sourceFile = cmd.getOptionValue("s");
        String outputFile = cmd.getOptionValue("o");

        TodoReader sourceReader = TodoSourceReaderFactory.createReader(sourceFile);
        TodoWriter destinationWriter = TodoDestinationWriterFactory.createWriter(outputFile);

        List<Todo> todos = sourceReader.readTodos(sourceFile);
        for (Todo todo : todos) {
            destinationWriter.writeTodo(outputFile,todo);
        }

        System.out.println("Migration completed.");
    }
}