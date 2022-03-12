package com.emented.client.commandLine;

import com.emented.client.util.StreamUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс, отвечающий за считывание скрипта с файла
 */
public class ScriptReader {

    /**
     * Коллекция, хранящая команды в виде строк
     */
    private ArrayList<String> commandsFromFile = new ArrayList<>();

    /**
     * Метод заполняющий коллекцию командами из файла
     *
     * @param fileName Имя файла, в котором хранится скрипт
     */
    public void readCommandsFromFile(String fileName) throws IOException {
        final StreamUtil streamUtil = new StreamUtil();
        FileInputStream stream = new FileInputStream(fileName);
        commandsFromFile = streamUtil.streamToArrayOfCommands(stream);
    }

    /**
     * Метод, возвращающий коллекцию команд
     *
     * @return Коллекция команд
     */
    public ArrayList<String> getCommandsFromFile() {
        return commandsFromFile;
    }
}
