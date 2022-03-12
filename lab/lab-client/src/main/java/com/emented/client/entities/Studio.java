package com.emented.client.entities;

import javax.validation.constraints.NotBlank;

/**
 * Класс, хранящий информацию о студии
 */
public class Studio {

    /**
     * Поле, хранящее адрес студии (не может быть null)
     */
    @NotBlank(message = "Адрес должен содержать хотя бы 1 символ")
    private String adress; //Поле не может быть null

    public Studio(String adress) {
        this.adress = adress;
    }

    /**
     * Метод, возвращающий адрес
     *
     * @return Адрес
     */
    public String getAdress() {
        return adress;
    }

    /**
     * Метод, устанавливающий адрес
     *
     * @param adress Новый адрес
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * Метод, сравнивающий две студии
     *
     * @param anotherStudio Студия для сравнения
     * @return Целое число
     */
    public int compareTo(Studio anotherStudio) {
        return this.adress.compareTo(anotherStudio.getAdress());
    }

    /**
     * Переопределение метода, возвращающего строковое представление класса
     *
     * @return Строковое представление класса
     */
    @Override
    public String toString() {
        return "адрес студии: " + adress;
    }
}
