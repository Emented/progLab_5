package com.emented.client.commandLine;

import com.emented.client.entities.Coordinates;
import com.emented.client.entities.MusicBand;
import com.emented.client.entities.MusicGenre;
import com.emented.client.entities.Studio;

import java.util.Scanner;

public class MusicBandGenerator {
    private final String name;
    private final MusicBand generatedMusicBand;
    private final Coordinates coordinates = new Coordinates();
    private final Scanner sc = new Scanner(System.in);

    public MusicBandGenerator(String name) {
        this.name = name;
        generatedMusicBand = new MusicBand();
        generatedMusicBand.setCoordinates(coordinates);
        setGivenVariables();
    }

    public MusicBandGenerator(String name, Long id) {
        this.name = name;
        generatedMusicBand = new MusicBand();
        generatedMusicBand.setId(id);
        generatedMusicBand.setCoordinates(coordinates);
        setGivenVariables();
    }

    private void getName() {
        generatedMusicBand.setName(this.name);
    }

    private void getNumberOfParticipants() {
        System.out.println("Введите число участников группы");
        try {
            generatedMusicBand.setNumberOfParticipants(sc.nextLine());
        } catch (NumberFormatException nfe) {
            System.out.println("Неверный формат числа");
            getNumberOfParticipants();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            getNumberOfParticipants();
        }

    }

    private void getXCoordinate() {
        System.out.println("Введите координату X (ее значение должно быть не больше 947)");
        try {
            generatedMusicBand.getCoordinates().setX(sc.nextLine());
        } catch (NumberFormatException nfe) {
            System.out.println("Неверный формат числа");
            getXCoordinate();
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
            getXCoordinate();
        }
    }

    private void getYCoordinate() {
        System.out.println("Введите координату Y (ее значение должно быть не больше 104)");
        try {
            generatedMusicBand.getCoordinates().setY(sc.nextLine());
        } catch (NumberFormatException nfe) {
            System.out.println("Неверный формат числа");
            getYCoordinate();
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
            getYCoordinate();
        }
    }

    private void getDescription() {
        System.out.println("Введите описание группы (для пропуска нажмите ENTER)");
        String desc = sc.nextLine();
        if (!"".equals(desc)) {
            generatedMusicBand.setDescription(desc);
        } else {
            generatedMusicBand.setDescription(null);
        }
    }

    private void getMusicGenre() {
        System.out.println("Введите жанр музыки из предложенных ниже (для пропуска нажмите ENTER)");
        System.out.println(MusicGenre.show());
        String genre = sc.nextLine();
        if (!"".equals(genre)) {
            try {
                generatedMusicBand.setGenre(genre);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка при вводе жанра");
                getMusicGenre();
            }
        } else {
            generatedMusicBand.setGenre("null");
        }
    }

    private void getStudio() {
        System.out.println("Введите адрес студии (для пропуска нажмите ENTER)");
        String studio = sc.nextLine();
        if (!"".equals(studio)) {
            generatedMusicBand.setStudio(new Studio());
            generatedMusicBand.getStudio().setAdress(studio);
        } else {
            generatedMusicBand.setStudio(null);
        }
    }

    public void setGivenVariables() {
        getName();
    }

    public void setAnotherVariables() {
        getXCoordinate();
        getYCoordinate();
        getNumberOfParticipants();
        getDescription();
        getMusicGenre();
        getStudio();
    }

    public MusicBand getGeneratedMusicBand() {
        System.out.println("Создана новая музыкальная группа:");
        System.out.println(generatedMusicBand);
        return generatedMusicBand;
    }
}
