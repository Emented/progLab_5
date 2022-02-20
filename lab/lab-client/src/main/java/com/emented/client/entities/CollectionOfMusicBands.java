package com.emented.client.entities;

import com.emented.client.comparators.StudioComparator;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;


@XStreamAlias("set")
public class CollectionOfMusicBands {

    private String fileName;
    private LocalDate dateOfInitialization;

    @XStreamImplicit
    private HashSet<MusicBand> musicBands;

    public CollectionOfMusicBands(String fileName) {
        dateOfInitialization = LocalDate.now();
        this.fileName = fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDate getDateOfInitialization() {
        return dateOfInitialization;
    }

    public void setDateOfInitialization(LocalDate dateOfInitialization) {
        this.dateOfInitialization = dateOfInitialization;
    }

    public String getFileName() {
        return fileName;
    }

    public void addMusicBand(MusicBand band) {
        musicBands.add(band);
    }

    public HashSet<MusicBand> getMusicBands() {
        return musicBands;
    }

    public void removeBandById(Long id) {
        for (MusicBand band : musicBands) {
            if (Objects.equals(band.getId(), id)) {
                musicBands.remove(band);
                return;
            }
        }
        throw new IllegalArgumentException("Группы с таким ID не существует");
    }

    public void updateById(Long id, MusicBand musicBand) {
        for (MusicBand band : musicBands) {
            if (Objects.equals(band.getId(), id)) {
                musicBands.remove(band);
                musicBands.add(musicBand);
                return;
            }
        }
        throw new IllegalArgumentException("Группы с таким ID не существует");
    }

    public void addIfMax(MusicBand musicBand) {
        for (MusicBand band : musicBands) {
            if (band.compareTo(musicBand) >= 0) {
                throw new IllegalArgumentException("Введенная музыкальная группа не максимальна");
            }
        }
        addMusicBand(musicBand);
    }

    public void removeIfGreater(MusicBand musicBand) {
        HashSet<MusicBand> copy = new HashSet<>(musicBands);
        for (MusicBand band : copy) {
            if (band.compareTo(musicBand) > 0) {
                System.out.println("Удалена музыкальная группа с id: " + band.getId());
                musicBands.remove(band);
            }
        }
    }

    public void removeAnyByNumberOfParticipants(Long numberOfParticipants) {
        if (numberOfParticipants <= 0) {
            throw new IllegalArgumentException("Количество учатников должно быть больше 0");
        }
        for (MusicBand band : musicBands) {
            if (band.getNumberOfParticipants() == numberOfParticipants) {
                System.out.println("Удалена музыкальная группа с id: " + band.getId());
                musicBands.remove(band);
                return;
            }
        }
        throw new IllegalArgumentException("Группы с таким ID не существует");
    }

    public MusicBand returnMinByStudio() {
        List<MusicBand> sortedBands = new ArrayList<>(musicBands);
        sortedBands.sort(new StudioComparator());
        return sortedBands.get(0);
    }

    public int countLessThanNumberOfParticipants(Long numberOfParticipants) {
        int counter = 0;
        List<MusicBand> sortedBands = new ArrayList<>(musicBands);
        sortedBands.sort(Comparator.comparingLong(MusicBand::getNumberOfParticipants));
        for (MusicBand band : sortedBands) {
            if (band.getNumberOfParticipants() < numberOfParticipants) {
                counter++;
            }
        }
        return counter;
    }

    public void clearCollection() {
        musicBands.clear();
    }

    public String returnInfo() {
        final int size = 6;
        return "Тип коллекции: " + musicBands.getClass().toString().substring(size) + ", тип хранимых элементов: "
                + MusicBand.class.toString().substring(size) +  ", дата инициализации: " + dateOfInitialization
                + ", количество элементов: " + musicBands.size() + ", файл коллекции: " + fileName;
    }

    public void show() {
        if (musicBands.isEmpty()) {
            System.out.println("Коллекция пуста");
        } else {
            for (MusicBand band : musicBands) {
                System.out.println(band);
            }
        }
    }
}

