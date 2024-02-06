package com.fges.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {

    /**
     * Do not change this method
     */
    public static void main(String[] args) throws Exception {
        System.exit(exec(args));
    }

    public static int exec(String[] args) throws IOException, ParseException {
        CommandLine cmd = CommandLineHandler.parseArguments(args);
        String fileName = cmd.getOptionValue("s");

        List<String> positionalArgs = cmd.getArgList();
        if (positionalArgs.isEmpty()) {
            System.err.println("Missing Command");
            return 1;
        }

        String command = positionalArgs.get(0);

        TodoManager todoManager;

        if (command.equals("insert")) {
            boolean isDone = cmd.hasOption("done");
            String description = String.join(" ", positionalArgs.subList(1, positionalArgs.size()));
            Todo todo = new Todo(description, isDone);

            if (fileName.endsWith(".json")) {
                todoManager = new JsonTodoManager();
            } else if (fileName.endsWith(".csv")) {
                todoManager = new CsvTodoManager();
            } else {
                System.err.println("Unsupported file format");
                return 1;
            }

            todoManager.insertTodo(fileName, todo);
        }

        if (command.equals("list")) {
            if (fileName.endsWith(".json")) {
                todoManager = new JsonTodoManager();
            } else if (fileName.endsWith(".csv")) {
                todoManager = new CsvTodoManager();
            } else {
                System.err.println("Unsupported file format");
                return 1;
            }

            todoManager.listTodos(fileName);
        }

        System.err.println("Done.");
        return 0;
    }

}
