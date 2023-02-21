package com.crio.codingame.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crio.codingame.exceptions.NoSuchCommandException;

public class CommandInvoker {
    private static final Map<String, ICommand> commandMap = new HashMap<>();

    // Register the command into the HashMap
    public void register(String commandName, ICommand command){
        commandMap.put(commandName,command);
    }

    // Get the registered Command
    private ICommand get(String commandName){
        return commandMap.get(commandName);
    }

    // Execute the registered Command
    public void executeCommand(String commandName, List<String> tokens) throws NoSuchCommandException {
        ICommand command = get(commandName);
        if(command == null){
            // Handle Exception
            throw new NoSuchCommandException();
        }
        command.execute(tokens);
    }

    /* Alternative(better) implementation from LLD sprint - Restaurant Rating application

    public void invokeCommand(String line) {
        List<String> tokens = parse(line);
        ICommand command = getCommand(tokens.get(0));
        if(command == null){
            // Handle Exception
            throw new RuntimeException("INVALID COMMAND 🛑");
        } 
        command.invoke(tokens);
        return;
    }

    private List<String> parse(String line){
        return Arrays.stream(line.split(" ")).collect(Collectors.toList());
    }
    */

}
