package com.emented.client.commandLine;

import com.emented.client.util.StreamUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ScriptReader {

    private ArrayList<String> commandsFromFile = new ArrayList<>();

    public void readCommandsFromFile(String fileName) {
        final StreamUtil streamUtil = new StreamUtil();
        try {
            FileInputStream stream = new FileInputStream(fileName);
            commandsFromFile = streamUtil.streamToArrayOfCommands(stream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> getCommandsFromFile() {
        return commandsFromFile;
    }
}
