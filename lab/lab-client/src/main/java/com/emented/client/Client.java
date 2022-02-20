package com.emented.client;

import com.emented.client.commandLine.CommandListener;
import com.emented.client.entities.CollectionOfMusicBands;
import com.emented.client.entities.MusicBand;
import com.emented.client.parser.XMLParser;

import java.io.*;

public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) throws IOException {
        XMLParser r = new XMLParser();
        CollectionOfMusicBands cc = r.readFromXML("MusicBands.xml");
//        for (MusicBand c : cc.getMusicBands()) {
//            System.out.println(c.getId());
//        }
//        cc.show();
//        System.out.println(cc.getFileName() + " " + cc.getDateOfInitialization());
////        r.writeToXML("asd.xml", cc);
//        System.out.println(cc.countLessThanNumberOfParticipents(1L));
        CommandListener cl = new CommandListener(cc);
        cl.readCommandsFromConsole();
    }
}