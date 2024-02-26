package com.fges.todoapp;

import com.fges.todoapp.factory.TodoDestinationWriterFactory;
import com.fges.todoapp.factory.TodoManagerFactory;
import com.fges.todoapp.factory.TodoSourceReaderFactory;
import com.fges.todoapp.io.CommandLineHandler;
import com.fges.todoapp.io.MigrateCommand;
import com.fges.todoapp.manager.CsvTodoManager;
import com.fges.todoapp.manager.JsonTodoManager;
import com.fges.todoapp.manager.TodoManager;
import com.fges.todoapp.model.Todo;
import com.fges.todoapp.reader.TodoReader;
import com.fges.todoapp.writer.TodoWriter;
import org.apache.commons.cli.*;
import java.text.ParseException;

import java.io.IOException;
import java.util.List;

public class App {

    /**
     * Do not change this method
     */
    public static void main(String[] args) throws Exception {
        System.exit(exec(args));
    }
    public static int exec(String[] args) throws IOException, org.apache.commons.cli.ParseException {
        CommandLine cmd = CommandLineHandler.parseArguments(args);
        String fileName = cmd.getOptionValue("s");

        List<String> positionalArgs = cmd.getArgList();
        if (positionalArgs.isEmpty()) {
            System.err.println("Missing Command");
            return 1;
        }

        String command = positionalArgs.get(0);

        TodoManager todoManager = TodoManagerFactory.getTodoManager(fileName);

        switch (command) {
            case "insert" -> {
                boolean isDone = cmd.hasOption("done");
                String description = String.join(" ", positionalArgs.subList(1, positionalArgs.size()));
                Todo todo = new Todo(description, isDone);

                todoManager.insertTodo(fileName, todo);
            }
            case "list" -> {
                boolean showDone = cmd.hasOption("done");
                todoManager.listTodos(fileName, showDone);
            }
            case "migrate" -> {
                String outputFileName = cmd.getOptionValue("output");
                if (outputFileName == null) {
                    System.err.println("Missing required option --output");
                    return 1;
                }

                MigrateCommand migrateCommand = new MigrateCommand();
                migrateCommand.migrate(fileName, outputFileName);
            }
        }

        System.err.println("Done.");
        return 0;
    }
    }