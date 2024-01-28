package com.fges.todoapp;
import org.apache.commons.cli.*;

public class CommandLineHandler {
    public static CommandLine parseArguments(String[] args) throws ParseException {
        Options cliOptions = new Options();
        cliOptions.addRequiredOption("s", "source", true, "File containing the todos");

        CommandLineParser parser = new DefaultParser();
        return parser.parse(cliOptions, args);
    }
}
