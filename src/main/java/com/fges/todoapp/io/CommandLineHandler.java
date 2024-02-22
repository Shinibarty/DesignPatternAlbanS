package com.fges.todoapp.io;
import org.apache.commons.cli.*;

public class CommandLineHandler {
    public static CommandLine parseArguments(String[] args) throws ParseException {
        Options cliOptions = new Options();
        cliOptions.addRequiredOption("s", "source", true, "File containing the todos");
        cliOptions.addOption("d","done",false, "Si présent, la tâche est finie");

        CommandLineParser parser = new DefaultParser();
        return parser.parse(cliOptions, args);
    }
}
