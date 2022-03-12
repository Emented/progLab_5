package com.emented.client.entities;

import com.emented.client.comparators.StudioComparator;
import com.emented.client.exceptions.CollectionIsEmptyException;
import com.emented.client.exceptions.GroupNotFoundException;
import com.emented.client.exceptions.GroupNotMaxException;
import com.emented.client.exceptions.IDNotFoundException;
import com.emented.client.util.OutputUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.time.LocalDate;
import java.util.*;

/**
 * Класс, хранящий в себе коллекцию музыкальных групп, а так же реализующий методы работы с ней
 */
@XStreamAlias("set")
public class CollectionOfMusicBands {

    /**
     * Поле, хранящее имя файла
     */
    private String fileName;

    /**
     * Поле, хранящее дату инициализации
     */
    private LocalDate dateOfInitialization;

    /**
     * Поле, хранящее коллекцию элементов
     */
    @XStreamImplicit
    private HashSet<MusicBand> musicBands;

    /**
     * Конструктор класса
     *
     * @param fileName Имя файла
     */
    public CollectionOfMusicBands(String fileName) {
        dateOfInitialization = LocalDate.now();
        this.fileName = fileName;
    }

    /**
     * Метод, возвращающий дату инициалзации
     *
     * @return Дата инициализации
     */
    public LocalDate getDateOfInitialization() {
        return dateOfInitialization;
    }

    /**
     * Метод, устанавливающий дату инициализации
     *
     * @param dateOfInitialization Дата инициализации
     */
    public void setDateOfInitialization(LocalDate dateOfInitialization) {
        this.dateOfInitialization = dateOfInitialization;
    }

    /**
     * Метод, возвращающий имя файла коллекции
     *
     * @return Имя файла
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Метод, устанавливающий имя файла
     *
     * @param fileName Новое имя файла
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Метод, добавлющий в коллекцию новый элемент
     *
     * @param band Новый элемент для добавления
     */
    public void addMusicBand(MusicBand band) {
        musicBands.add(band);
    }

    /**
     * Метод, возвращающий коллекцию
     *
     * @return Коллекция групп
     */
    public HashSet<MusicBand> getMusicBands() {
        return musicBands;
    }

    /**
     * Метод, удаляющий группу по ID
     *
     * @param id ID группы для удаления
     */
    public void removeBandById(Long id) throws IDNotFoundException {
        for (MusicBand band : musicBands) {
            if (Objects.equals(band.getId(), id)) {
                musicBands.remove(band);
                return;
            }
        }
        throw new IDNotFoundException("Группы с таким ID не существует");
    }

    /**
     * Метод обновляющий элемент коллекции по ID
     *
     * @param id        ID существующего элемента
     * @param musicBand Новый элемент коллекции
     */
    public void updateById(Long id, MusicBand musicBand) throws IDNotFoundException {
        for (MusicBand band : musicBands) {
            if (Objects.equals(band.getId(), id)) {
                musicBands.remove(band);
                musicBands.add(musicBand);
                return;
            }
        }
        throw new IDNotFoundException("Группы с таким ID не существует");
    }

    public void checkId(Long id) throws IDNotFoundException {
        boolean flag = false;
        for (MusicBand band : musicBands) {
            if (Objects.equals(band.getId(), id)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new IDNotFoundException("Группы с таким ID не существует");
        }
    }

    /**
     * Метод, добавляющий новый элемент в коллекцию, если он больше всех существующих
     *
     * @param musicBand Новый элемент
     */
    public void addIfMax(MusicBand musicBand) throws GroupNotMaxException {
        for (MusicBand band : musicBands) {
            if (band.compareTo(musicBand) >= 0) {
                throw new GroupNotMaxException("Введенная музыкальная группа не максимальна");
            }
        }
        addMusicBand(musicBand);
    }

    /**
     * Метод, удаляющий все элементы коллекции, большие данной
     *
     * @param musicBand Элемент для сравнения
     */
    public void removeIfGreater(MusicBand musicBand) throws CollectionIsEmptyException {
        if (!musicBands.isEmpty()) {
            HashSet<MusicBand> copy = new HashSet<>(musicBands);
            for (MusicBand band : copy) {
                if (band.compareTo(musicBand) > 0) {
                    OutputUtil.printSuccessfulMessage("Удалена музыкальная группа с id: " + band.getId());
                    musicBands.remove(band);
                }
            }
        } else {
            throw new CollectionIsEmptyException("Коллекция пуста");
        }
    }

    /**
     * Метод, удаляющий из коллекции элемент с эквивалентным заданному числом участников
     *
     * @param numberOfParticipants Число участников для сравнения
     */
    public void removeAnyByNumberOfParticipants(Long numberOfParticipants) throws GroupNotFoundException, CollectionIsEmptyException {
        if (!musicBands.isEmpty()) {
            for (MusicBand band : musicBands) {
                if (band.getNumberOfParticipants() == numberOfParticipants) {
                    OutputUtil.printSuccessfulMessage("Удалена музыкальная группа с id: " + band.getId());
                    musicBands.remove(band);
                    return;
                }
            }
            throw new GroupNotFoundException("Группы с таким числом участников не существует");
        } else {
            throw new CollectionIsEmptyException("Коллекция пуста");
        }
    }

    /**
     * Метод для возврата элемента коллекции с минимальным значением студии
     *
     * @return Элемент коллекции с минимальным значением студии
     */
    public MusicBand returnMinByStudio() throws CollectionIsEmptyException {
        if (!musicBands.isEmpty()) {
            List<MusicBand> sortedBands = new ArrayList<>(musicBands);
            sortedBands.sort(new StudioComparator());
            return sortedBands.get(0);
        } else {
            throw new CollectionIsEmptyException("Коллекция пуста");
        }
    }

    /**
     * Метод для подсчета элементов коллекции, количество участников которых меньше заданного
     *
     * @param numberOfParticipants Число участников для сравнения
     * @return Число элементов с меньшим числом участников
     */
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

    /**
     * Метод очистки коллекции
     */
    public void clearCollection() {
        musicBands.clear();
    }

    /**
     * Метод, возвращющий информацию о коллекции
     *
     * @return Строку, содержащую информацию о коллекции
     */
    public String returnInfo() {
        final int size = 6;
        return "Тип коллекции: " + musicBands.getClass().toString().substring(size) + ", тип хранимых элементов: "
                + MusicBand.class.toString().substring(size) + ", дата инициализации: " + dateOfInitialization
                + ", количество элементов: " + musicBands.size() + ", файл коллекции: " + fileName;
    }

    /**
     * Вывести все элементы коллекции в их строковом представлении
     */
    public void show() {
        if (musicBands.isEmpty()) {
            OutputUtil.printSuccessfulMessage("Коллекция пуста");
        } else {
            OutputUtil.printSuccessfulMessage("Элемент(ы) коллекции:");
            for (MusicBand band : musicBands) {
                System.out.println(band);
            }
        }
    }
}

