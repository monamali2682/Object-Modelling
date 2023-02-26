package com.crio.jukebox.commands;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CommandRegistry {
    HashMap<String, Icommand> map = new HashMap<>();
    
    public void register(String name, Icommand command) {
        map.put(name, command);
    }

    public Icommand getCommand(String name){
        return map.get(name);   
    }

    private List<String> parse(String line){
        return Arrays.stream(line.split(" ")).collect(Collectors.toList());
    }
    
    public void invokeCommand(String line) {
        List<String> tokens = parse(line);
        String commandName = tokens.get(0);
        Icommand command = this.getCommand(commandName);
        if(command==null) throw new RuntimeException("Invalid Command");
        command.invoke(tokens);
        return;
    }

}
