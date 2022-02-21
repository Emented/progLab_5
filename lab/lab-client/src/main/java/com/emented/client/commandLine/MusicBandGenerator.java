package com.emented.client.commandLine;

import com.emented.client.entities.Coordinates;
import com.emented.client.entities.MusicBand;
import com.emented.client.entities.MusicGenre;
import com.emented.client.entities.Studio;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс, предназанченный для генерации новой музыкальной группы
 */
public class MusicBandGenerator {

    /**
     * Имя новой музыкальной группы
     */
    private final String name;

    /**
     * Новая музыкальная группа
     */
    private final MusicBand generatedMusicBand;

    /**
     * Координаты новой музыкальной группы
     */
    private final Coordinates coordinates = new Coordinates();

    /**
     * Сканер для считывания в интерактином режиме
     */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Конструктор класса
     * @param name Имя новой музыкальной группы
     */
    public MusicBandGenerator(String name) {
        this.name = name;
        generatedMusicBand = new MusicBand();
        generatedMusicBand.setCoordinates(coordinates);
        setGivenVariables();
    }

    /**
     * Конструктор класса с заданным ID
     * @param name Имя новой музыкальной группы
     * @param id ID новой музыкальной группы
     */
    public MusicBandGenerator(String name, Long id) {
        this.name = name;
        generatedMusicBand = new MusicBand();
        generatedMusicBand.setId(id);
        generatedMusicBand.setCoordinates(coordinates);
        setGivenVariables();
    }

    /**
     * Метод, устанавливающий имя для новой музыкальной группы
     */
    private void getName() {
        generatedMusicBand.setName(this.name);
    }

    /**
     * Метод, считывающий число учатников с коммандной строки и устанавливающий его для новой муыкальной группы
     */
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
        } catch (NoSuchElementException e) {
            System.out.println("Введен недопустимый символ");
            System.exit(0);
        }

    }

    /**
     * Метод, считывающий координату X с коммандной строки и устанавливающий ее для новой муыкальной группы
     */
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
        } catch (NoSuchElementException e) {
            System.out.println("Введен недопустимый символ");
            System.exit(0);
        }
    }

    /**
     * Метод, считывающий координату Y с коммандной строки и устанавливающий ее для новой муыкальной группы
     */
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
        } catch (NoSuchElementException e) {
            System.out.println("Введен недопустимый символ");
            System.exit(0);
        }
    }

    /**
     * Метод, считывающий описание с коммандной строки и устанавливающий его для новой муыкальной группы
     */
    private void getDescription() {
        System.out.println("Введите описание группы (для пропуска нажмите ENTER)");
        try {
            String desc = sc.nextLine();
            if (!"".equals(desc)) {
                generatedMusicBand.setDescription(desc);
            } else {
                generatedMusicBand.setDescription(null);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Введен недопустимый символ");
            System.exit(0);
        }
    }

    /**
     * Метод, считывающий жанр с коммандной строки и устанавливающий его для новой муыкальной группы
     */
    private void getMusicGenre() {
        System.out.println("Введите жанр музыки из предложенных ниже (для пропуска нажмите ENTER)");
        System.out.println(MusicGenre.show());
        try {
            String genre = sc.nextLine();
            if (!"".equals(genre)) {
                    generatedMusicBand.setGenre(genre);
                } else {
                generatedMusicBand.setGenre("null");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Введен недопустимый символ");
            System.exit(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при вводе жанра");
            getMusicGenre();
        }
    }

    /**
     * Метод, считывающий студию с коммандной строки и устанавливающий ее для новой муыкальной группы
     */
    private void getStudio() {
        System.out.println("Введите адрес студии (для пропуска нажмите ENTER)");
        try {
            String studio = sc.nextLine();
            if (!"".equals(studio)) {
                generatedMusicBand.setStudio(new Studio());
                generatedMusicBand.getStudio().setAdress(studio);
            } else {
                generatedMusicBand.setStudio(null);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Введен недопустимый символ");
            System.exit(0);
        }
    }

    /**
     * Метод, устанавливающий переменные, переданные как аргументы
     */
    public void setGivenVariables() {
        getName();
    }

    /**
     * Метод, устанавливающий переменные, считанные в интерактивном режиме
     */
    public void setAnotherVariables() {
        getXCoordinate();
        getYCoordinate();
        getNumberOfParticipants();
        getDescription();
        getMusicGenre();
        getStudio();
    }

    /**
     * Метод, возвращающий новую музыкальную группу
     * @return Новая музыкальная группа
     */
    public MusicBand getGeneratedMusicBand() {
        System.out.println("Создана новая музыкальная группа:");
        System.out.println(generatedMusicBand);
        return generatedMusicBand;
    }
}
