package com.emented.client.entities;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.time.LocalDate;
import java.util.Locale;

@XStreamAlias("musicband")
public class MusicBand {
    /**
     * Поле, отвечающее за счеткик IP
     */
    private static long currentId = 1;
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private final java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long numberOfParticipants; //Значение поля должно быть больше 0
    private String description; //Поле может быть null
    private MusicGenre genre; //Поле может быть null
    private Studio studio; //Поле может быть null

    /**
     * Конструктор, автоматически выставляющий ID и дату инициализации
     */
    public MusicBand() {
        setId();
        creationDate = LocalDate.now();
    }

    /**
     * Метод, устанавливающий ID автоматически
     */
    public void setId() {
        this.id = currentId++;
    }

    /**
     * Метод, устанавливающий ID по данному
     * @param id Новый ID
     */
    public void setId(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID должен быть больше 0");
        }
        this.id = id;
    }

    /**
     * Метод, возвращающий ID
     * @return ID объекта
     */
    public Long getId() {
        return id;
    }

    /**
     * Метод, устанавливающий имя объекта
     * @param name Новое имя
     */
    public void setName(String name) {
        if ("".equals(name) || name == null) {
            throw new IllegalArgumentException("Введено некоректное имя");
        }
        this.name = name;
    }

    /**
     * Метод, возвращающий соординаты объекта
     * @return Координаты объекта
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Метод, устанавливающий координаты объекта
     * @param coordinates Новые координаты
     */
    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Координаты не могут быть NULL");
        }
        this.coordinates = coordinates;
    }

    /**
     * Метод, возвращающий число участников
     * @return Число участников
     */
    public long getNumberOfParticipants() {
        return numberOfParticipants;
    }

    /**
     * Метод, устанавливающий число участников
     * @param numberOfParticipants Новое число участников
     */
    public void setNumberOfParticipants(String numberOfParticipants) {
        if (Long.parseLong(numberOfParticipants) <= 0) {
            throw new IllegalArgumentException("Количество участников должно быть больше 0");
        }
        this.numberOfParticipants = Long.parseLong(numberOfParticipants);
    }

    /**
     * Метод, устанавливающий описание
     * @param description Новое описание
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Метод, устанавливающий жанр
     * @param genre Новый жанр
     */
    public void setGenre(String genre) {
        String newGenre = genre.toUpperCase(Locale.ROOT);
        if ("NULL".equals(newGenre)) {
            this.genre = null;
        } else {
            this.genre = MusicGenre.valueOf(newGenre);
        }
    }

    /**
     * Метод, возвращающий студию
     * @return Студия
     */
    public Studio getStudio() {
        return studio;
    }

    /**
     * Метод, устанавливающий студию
     * @param studio Новая студия
     */
    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    /**
     * Метод сравнения
     * @param anotherBand Группа для сравнения
     * @return Целочисленное значение
     */
    public int compareTo(MusicBand anotherBand) {
        return Long.compare(this.numberOfParticipants, anotherBand.numberOfParticipants);
    }

    /**
     * Переопределение метода, возвращающего строковое представление класса
     * @return Строковое представление класса
     */
    @Override
    public String toString() {
        return  "ID: " + id
                + ", название: " + name
                + ", координаты: " + coordinates
                + ", дата создания: " + creationDate
                + ", число участников: " + numberOfParticipants
                + ", описание: " + ((description == null) ? "отстуствует" : description)
                + ", жанр: " + ((genre == null) ? "не определен" : genre)
                + ", " + ((studio == null) ? "студия отсутствует" : studio);
    }
}
