package com.emented.client.util;

import com.emented.client.entities.Coordinates;
import com.emented.client.entities.MusicBand;
import com.emented.client.entities.MusicGenre;
import com.emented.client.entities.Studio;
import com.emented.client.validator.Validators;

import java.util.Locale;
import java.util.Scanner;

/**
 * Класс, предназанченный для генерации новой музыкальной группы
 */
public class MusicBandGenerator {

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
     */
    public MusicBandGenerator() {
        generatedMusicBand = new MusicBand();
        generatedMusicBand.setCoordinates(coordinates);
    }

    /**
     * Конструктор класса с заданным ID
     *
     * @param id ID новой музыкальной группы
     */
    public MusicBandGenerator(Long id) {
        generatedMusicBand = new MusicBand();
        generatedMusicBand.setId(id);
        generatedMusicBand.setCoordinates(coordinates);
    }

    /**
     * Метод, устанавливающий имя для новой музыкальной группы
     */
    private void getName() {
        String name = Validators.validateStringInput("Введите название музыкальной группы",
                false,
                sc);
        generatedMusicBand.setName(name);
    }

    /**
     * Метод, считывающий число учатников с коммандной строки и устанавливающий его для новой муыкальной группы
     */
    private void getNumberOfParticipants() {
        long numberOfParticipants = Validators.validateInput(arg -> ((long) arg) > 0,
                "Введите число участников группы",
                "Ошибка при обработке числа, повторите ввод",
                "Число участников группы должно быть больше 0, повторите ввод",
                Long::parseLong,
                false,
                sc);
        generatedMusicBand.setNumberOfParticipants(numberOfParticipants);
    }

    /**
     * Метод, считывающий координату X с коммандной строки и устанавливающий ее для новой муыкальной группы
     */
    private void getXCoordinate() {
        double x = Validators.validateInput(arg -> ((double) arg) < Coordinates.MAX_X,
                "Введите координату по X группы (ее значение должно быть не больше " + Coordinates.MAX_X + ")",
                "Ошибка при обработке числа, повторите ввод",
                "Координата по X должна быть не больше " + Coordinates.MAX_X + ", повторите ввод",
                Double::parseDouble,
                false,
                sc);
        generatedMusicBand.getCoordinates().setX(x);
    }

    /**
     * Метод, считывающий координату Y с коммандной строки и устанавливающий ее для новой муыкальной группы
     */
    private void getYCoordinate() {
        Float y = Validators.validateInput(arg -> ((Float) arg) < Coordinates.MAX_Y,
                "Введите координату по Y группы (ее значение должно быть не больше " + Coordinates.MAX_Y + ")",
                "Ошибка при обработке числа, повторите ввод",
                "Координата по Y должна быть не больше " + Coordinates.MAX_Y + ", повторите ввод",
                Float::parseFloat,
                false,
                sc);
        generatedMusicBand.getCoordinates().setY(y);
    }

    /**
     * Метод, считывающий описание с коммандной строки и устанавливающий его для новой муыкальной группы
     */
    private void getDescription() {
        String description = Validators.validateStringInput("Введите описание группы (для пропуска нажмите ENTER)",
                true,
                sc);
        generatedMusicBand.setDescription(description);
    }

    /**
     * Метод, считывающий жанр с коммандной строки и устанавливающий его для новой муыкальной группы
     */
    private void getMusicGenre() {
        MusicGenre genre = Validators.validateInput(arg -> true,
                "Введите жанр музыки из предложенных ниже (для пропуска нажмите ENTER)\n" + MusicGenre.show(),
                "Такого музыкального жанра не существует, повторите ввод",
                "Ошибка ввода",
                string -> MusicGenre.valueOf(string.toUpperCase(Locale.ROOT)),
                true,
                sc);
        generatedMusicBand.setGenre(genre);
    }

    /**
     * Метод, считывающий студию с коммандной строки и устанавливающий ее для новой муыкальной группы
     */
    private void getStudio() {
        Studio studio = Validators.validateInput(arg -> true,
                "Введите адрес студии (для пропуска нажмите ENTER)",
                "Ошибка ввода",
                "Ошибка ввода",
                Studio::new,
                true,
                sc);
        generatedMusicBand.setStudio(studio);
    }

    /**
     * Метод, устанавливающий переменные, считанные в интерактивном режиме
     */
    public void setAnotherVariables() {
        getName();
        getXCoordinate();
        getYCoordinate();
        getNumberOfParticipants();
        getDescription();
        getMusicGenre();
        getStudio();
    }

    /**
     * Метод, возвращающий новую музыкальную группу
     *
     * @return Новая музыкальная группа
     */
    public MusicBand getGeneratedMusicBand() {
        OutputUtil.printSuccessfulMessage("Создана новая музыкальная группа:");
        System.out.println(generatedMusicBand);
        return generatedMusicBand;
    }
}
