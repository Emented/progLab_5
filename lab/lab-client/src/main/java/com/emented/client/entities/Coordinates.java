package com.emented.client.entities;

public class Coordinates {

    private static final double MAX_X = 947;
    private static final Float MAX_Y = 104F;

    private double x; //Максимальное значение поля: 947
    private Float y; //Максимальное значение поля: 104, Поле не может быть null

    public void setX(String x) {
        double newX = Double.parseDouble(x);
        if (newX >= MAX_X) {
            throw new IllegalArgumentException("Значение координаты по X должно быть меньше 947");
        }
        this.x = newX;
    }

    public void setY(String y) {
        float newY = Float.parseFloat(y);
        if (newY >= MAX_Y) {
            throw new IllegalArgumentException("Значение координаты по Y должно быть меньше 104");
        }
        this.y = newY;
    }

    public double getX() {
        return this.x;
    }

    public Float getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "X = " + x + ", Y = " + y;
    }
}
