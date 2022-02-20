package com.emented.client.parser;

import com.emented.client.entities.CollectionOfMusicBands;
import com.emented.client.entities.MusicBand;
import com.emented.client.util.StreamUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.*;
import java.time.LocalDate;

public class XMLParser {

    private final XStream xStream = new XStream();
    private final StreamUtil converter = new StreamUtil();

    private void initializeParser() {
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.processAnnotations(CollectionOfMusicBands.class);
    }

    public CollectionOfMusicBands readFromXML(String fileName) throws IOException {
        FileInputStream stream = new FileInputStream(fileName);
        initializeParser();
        String xmlText = converter.streamToString(stream);
        stream.close();
        CollectionOfMusicBands collection = (CollectionOfMusicBands) xStream.fromXML(xmlText);
        for (MusicBand band : collection.getMusicBands()) {
            band.setId();
        }
        collection.setFileName(fileName);
        collection.setDateOfInitialization(LocalDate.now());
        return collection;
    }

    public void writeToXML(String fileName, CollectionOfMusicBands musicBands) throws IOException {
        initializeParser();
        xStream.processAnnotations(CollectionOfMusicBands.class);
        String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + xStream.toXML(musicBands.getMusicBands());
        PrintWriter p = new PrintWriter(fileName);
        p.write(text);
        p.close();
    }

    public void writeToXMLofExistingInstance(CollectionOfMusicBands musicBands) throws IOException {
        initializeParser();
        xStream.processAnnotations(CollectionOfMusicBands.class);
        String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + xStream.toXML(musicBands.getMusicBands());
        PrintWriter p = new PrintWriter(musicBands.getFileName());
        p.write(text);
        p.close();
    }
}
