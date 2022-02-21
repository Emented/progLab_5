package com.emented.client.entities;

/**
 * Класс, хранящий информацию о студии
 */
public class Studio {

    /**
     * Поле, хранящее адрес студии (не может быть null)
     */
    private String adress; //Поле не может быть null

    /**
     * Метод, устанавливающий адрес
     * @param adress Новый адрес
     */
    public void setAdress(String adress) {
        if ("".equals(adress)) {
            throw new IllegalArgumentException("Адрес студии не может быть пустым!");
        }
        if (adress == null) {
            throw new IllegalArgumentException("У студии должен быть адрес!");
        }

        this.adress = adress;
    }

    /**
     * Метод, возвращающий адрес
     * @return Адрес
     */
    public String getAdress() {
        return adress;
    }

    /**
     * Метод, сравнивающий две студии
     * @param anotherStudio Студия для сравнения
     * @return Целое число
     */
    public int compareTo(Studio anotherStudio) {
        return this.adress.compareTo(anotherStudio.getAdress());
    }

    /**
     * Переопределение метода, возвращающего строковое представление класса
     * @return Строковое представление класса
     */
    @Override
    public String toString() {
        return "адрес студии: " + adress;
    }
}
