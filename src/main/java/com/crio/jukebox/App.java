package com.crio.jukebox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.AppConfig.AppConfiguration;
import com.crio.jukebox.commands.CommandRegistry;


public class App {
    // To run the application  ./gradlew run --args="INPUT_FILE=jukebox-input.txt"
	public static void main(String[] args) {
		List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
        String expectedSequence = "INPUT-FILE";
        String actualSequence = commandLineArgs.stream()
                .map(a -> a.split("=")[0])
                .collect(Collectors.joining("$"));
        if(expectedSequence.equals(actualSequence)){
            run(commandLineArgs);
        }
	}

    public static void run(List<String> commandLineArgs) {
    // Complete the final logic to run the complete program.
        AppConfiguration config = AppConfiguration.getINSTANCE();
        CommandRegistry commandRegistry = config.getCommandRegistry();
        String inputfile= commandLineArgs.get(0).split("=")[1];
        // using try with resources
        try (BufferedReader reader = new BufferedReader(new FileReader(inputfile))) {
            while(true){
                String line = reader.readLine();
                if(line==null){
                    break;
                }
                commandRegistry.invokeCommand(line);
            } 
        } catch (Exception e) {
            
            e.printStackTrace();
        }

    }
}



/*
 * args = ["INPUT-FILE=jukebox-input.txt"] ------(array)
 * commandLineArgs  = ["INPUT-FILE=jukebox-input.txt"] ----(linked list)
 * inputfile = "jukebox-input.txt"
 */