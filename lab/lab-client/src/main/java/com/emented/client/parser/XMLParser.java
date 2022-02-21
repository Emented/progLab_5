package com.emented.client.parser;

import com.emented.client.entities.CollectionOfMusicBands;
import com.emented.client.entities.MusicBand;
import com.emented.client.util.StreamUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.time.LocalDate;

/**
 * Класс, реализующий парсинг XML файла, а так же запись в файл
 */
public class XMLParser {

    /**
     * Создаем экземпляр класса, отвечающего за парсинг файла
     */
    private final XStream xStream = new XStream();
    /**
     * Создаем экземпляр класса, отвечающего за конвертацию потока в строку
     */
    private final StreamUtil converter = new StreamUtil();

    /**
     * Метод, инициализирующий парсер
     */
    private void initializeParser() {
        xStream.addPermission(AnyTypePermission.ANY);
        xStream.processAnnotations(CollectionOfMusicBands.class);
    }

    /**
     * Метод, считывающий коллекцию из XML файла
     * @param fileName Имя исходного файла
     * @return Экземпляр класса, хранящего в себе коллекцию
     * @throws IOException Возможна ошибка доступа либо отсутствия файла по данному адресу
     */
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

    /**
     * Метод, записывающий коллекцию в XML файл с указанным именем
     * @param fileName Имя исходного файла
     * @param musicBands Класс, содежащий коллекцию, которую необходимо записать
     * @throws IOException Возможна ошибка доступа либо отсутствия файла по данному адресу
     */
    public void writeToXML(String fileName, CollectionOfMusicBands musicBands) throws IOException {
        initializeParser();
        xStream.processAnnotations(CollectionOfMusicBands.class);
        String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + xStream.toXML(musicBands.getMusicBands());
        PrintWriter p = new PrintWriter(fileName);
        p.write(text);
        p.close();
    }

    /**
     * Метод, записывающий коллекцию в XML файл с именем указанным в классе коллекции
     * @param musicBands Класс, содежащий коллекцию, которую необходимо записать
     * @throws IOException Возможна ошибка доступа либо отсутствия файла по данному адресу
     */
    public void writeToXMLofExistingInstance(CollectionOfMusicBands musicBands) throws IOException {
        initializeParser();
        xStream.processAnnotations(CollectionOfMusicBands.class);
        String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + xStream.toXML(musicBands.getMusicBands());
        PrintWriter p = new PrintWriter(musicBands.getFileName());
        p.write(text);
        p.close();
    }
}
