package com.emented.client;

import com.emented.client.commandLine.CommandListener;
import com.emented.client.entities.CollectionOfMusicBands;
import com.emented.client.parser.XMLParser;
import com.emented.client.util.OutputUtil;

import java.io.IOException;

public final class Client {

    public static void main(String[] args) {
        XMLParser r = new XMLParser();
        try {
//            CollectionOfMusicBands cc = r.readFromXML(args[0]);
            CollectionOfMusicBands cc = r.readFromXML("MusicBands.xml");
            CommandListener cl = new CommandListener(cc);
            cl.readCommandsFromConsole();
        } catch (IOException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
