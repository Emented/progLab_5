package com.emented.client.entities;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@XStreamAlias("musicband")
public class MusicBand {
    /**
     * Поле, отвечающее за счеткик IP
     */
    private static long currentId = 1;
    @NotNull
    @PastOrPresent(message = "Коллекция не может иметь дату создания в будущем времени")
    private final java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @NotNull
    @Positive
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @NotBlank(message = "Имя должно содержать хотя бы 1 символ")
    private String name; //Поле не может быть null, Строка не может быть пустой
    @NotNull
    private Coordinates coordinates; //Поле не может быть null
    @Positive(message = "Количество учатников должно быть больше 0")
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
     * Метод, возвращающий ID
     *
     * @return ID объекта
     */
    public Long getId() {
        return id;
    }

    /**
     * Метод, устанавливающий ID по данному
     *
     * @param id Новый ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Метод, устанавливающий имя объекта
     *
     * @param name Новое имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод, возвращающий соординаты объекта
     *
     * @return Координаты объекта
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Метод, устанавливающий координаты объекта
     *
     * @param coordinates Новые координаты
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Метод, возвращающий число участников
     *
     * @return Число участников
     */
    public long getNumberOfParticipants() {
        return numberOfParticipants;
    }

    /**
     * Метод, устанавливающий число участников
     *
     * @param numberOfParticipants Новое число участников
     */
    public void setNumberOfParticipants(Long numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    /**
     * Метод, устанавливающий описание
     *
     * @param description Новое описание
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Метод, устанавливающий жанр
     *
     * @param genre Новый жанр
     */
    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    /**
     * Метод, возвращающий студию
     *
     * @return Студия
     */
    public Studio getStudio() {
        return studio;
    }

    /**
     * Метод, устанавливающий студию
     *
     * @param studio Новая студия
     */
    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    /**
     * Метод сравнения
     *
     * @param anotherBand Группа для сравнения
     * @return Целочисленное значение
     */
    public int compareTo(MusicBand anotherBand) {
        return Long.compare(this.numberOfParticipants, anotherBand.numberOfParticipants);
    }

    /**
     * Переопределение метода, возвращающего строковое представление класса
     *
     * @return Строковое представление класса
     */
    @Override
    public String toString() {
        return "ID: " + id
                + ", название: " + name
                + ", координаты: " + coordinates
                + ", дата создания: " + creationDate
                + ", число участников: " + numberOfParticipants
                + ", описание: " + ((description == null) ? "отстуствует" : description)
                + ", жанр: " + ((genre == null) ? "не определен" : genre)
                + ", " + ((studio == null) ? "студия отсутствует" : studio);
    }
}
