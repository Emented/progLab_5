package com.emented.client.entities;

public class Studio {
    private String adress; //Поле не может быть null

    public void setAdress(String adress) {
        if ("".equals(adress)) {
            throw new IllegalArgumentException("Адрес студии не может быть пустым!");
        }
        if (adress == null) {
            throw new IllegalArgumentException("У студии должен быть адрес!");
        }

        this.adress = adress;
    }

    public String getAdress() {
        return adress;
    }

    public int compareTo(Studio anotherStudio) {
        return this.adress.compareTo(anotherStudio.getAdress());
    }

    @Override
    public String toString() {
        return "адрес студии: " + adress;
    }
}
