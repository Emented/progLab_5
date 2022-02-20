package com.emented.client.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StreamUtil {
    public String streamToString(FileInputStream file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file));
        StringBuilder fString = new StringBuilder();
        bufferedReader.readLine();
        String line = bufferedReader.readLine();
        while (line != null) {
            fString.append(line.trim());
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return fString.toString();
    }

    public ArrayList<String> streamToArrayOfCommands(FileInputStream file) throws IOException {
        ArrayList<String> commands = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(file));
        String line = br.readLine();
        while (line != null) {
            commands.add(line);
            line = br.readLine();
        }
        br.close();
        return commands;
    }
}
