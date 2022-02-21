package com.emented.client.entities;

/**
 * Класс, хранящий координаты музыкальной студии
 */
public class Coordinates {

    /**
     * Константа, отвечающая за максимальную координату по X
     */
    private static final double MAX_X = 947;

    /**
     * Константа, отвечающая за максимальную координату по Y
     */
    private static final Float MAX_Y = 104F;

    /**
     * Поле, хранящее в себе координату по X
     */
    private double x; //Максимальное значение поля: 947

    /**
     * Поле, хранящее в себе координату по Y
     */
    private Float y; //Максимальное значение поля: 104, Поле не может быть null

    /**
     * Метод, устанавливающий координату по X
     * @param x Новая координата по X
     */
    public void setX(String x) {
        double newX = Double.parseDouble(x);
        if (newX >= MAX_X) {
            throw new IllegalArgumentException("Значение координаты по X должно быть меньше 947");
        }
        this.x = newX;
    }

    /**
     * Метод, устанавливающий координату по Y
     * @param y Новая координата по Y
     */
    public void setY(String y) {
        float newY = Float.parseFloat(y);
        if (newY >= MAX_Y) {
            throw new IllegalArgumentException("Значение координаты по Y должно быть меньше 104");
        }
        this.y = newY;
    }

    /**
     * Метод, возвращающий координату по X
     * @return координата X
     */
    public double getX() {
        return this.x;
    }

    /**
     * Метод, возвращающий координату по Y
     * @return координата Y
     */
    public Float getY() {
        return this.y;
    }

    /**
     * Переопределение метода, возвращающего строковое представление класса
     * @return Строковое представление класса
     */
    @Override
    public String toString() {
        return "X = " + x + ", Y = " + y;
    }
}
