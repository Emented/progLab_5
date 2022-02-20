package com.emented.client;

import com.emented.client.commandLine.CommandListener;
import com.emented.client.entities.CollectionOfMusicBands;
import com.emented.client.parser.XMLParser;

import java.io.FileNotFoundException;
import java.io.IOException;

public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) throws IOException {
        XMLParser r = new XMLParser();
        try {
            CollectionOfMusicBands cc = r.readFromXML(args[0]);
            CommandListener cl = new CommandListener(cc);
            cl.readCommandsFromConsole();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
    }
}
