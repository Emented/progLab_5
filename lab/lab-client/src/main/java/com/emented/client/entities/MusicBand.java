package com.emented.client.entities;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.time.LocalDate;
import java.util.Locale;

@XStreamAlias("musicband")
public class MusicBand {

    private static long currentId = 1;
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private final java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long numberOfParticipants; //Значение поля должно быть больше 0
    private String description; //Поле может быть null
    private MusicGenre genre; //Поле может быть null
    private Studio studio; //Поле может быть null

    public MusicBand() {
        setId();
        creationDate = LocalDate.now();
    }

    public void setId() {
        this.id = currentId++;
    }

    public void setId(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID должен быть больше 0");
        }
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if ("".equals(name) || name == null) {
            throw new IllegalArgumentException("Введено некоректное имя");
        }
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Координаты не могут быть NULL");
        }
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public long getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(String numberOfParticipants) {
        if (Long.parseLong(numberOfParticipants) <= 0) {
            throw new IllegalArgumentException("Количество участников должно быть больше 0");
        }
        this.numberOfParticipants = Long.parseLong(numberOfParticipants);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        String newGenre = genre.toUpperCase(Locale.ROOT);
        if ("NULL".equals(newGenre)) {
            this.genre = null;
        } else {
            this.genre = MusicGenre.valueOf(newGenre);
        }
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public int compareTo(MusicBand anotherBand) {
        return Long.compare(this.numberOfParticipants, anotherBand.numberOfParticipants);
    }

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
