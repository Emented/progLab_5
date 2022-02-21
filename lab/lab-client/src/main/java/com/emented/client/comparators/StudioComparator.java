package com.emented.client.comparators;

import com.emented.client.entities.MusicBand;

import java.util.Comparator;

/**
 * Класс, предназначенный для определения инструкции для сортировки коллекции
 */
public class StudioComparator implements Comparator<MusicBand> {

    /**
     * Метод, сравнивающий два элемента коллекции
     * @param a Первый элемент коллекции
     * @param b Второй элемент коллекции
     * @return Целочисленное значение
     */
    @Override
    public int compare(MusicBand a, MusicBand b) {
        if (a.getStudio() == null) {
            return -1;
        } else if (b.getStudio() == null) {
            return 1;
        }
        return a.getStudio().compareTo(b.getStudio());
    }
}
