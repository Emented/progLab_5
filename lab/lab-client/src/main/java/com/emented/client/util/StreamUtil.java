package com.emented.client.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Класс, отвечающий за перевод потока ввода с строку/структуру строк
 */
public class StreamUtil {

    /**
     * Метод, конвертирующий поток из файла в строку
     * @param file Поток ввода из файла
     * @return Строку, которая составлена из информации в файле
     * @throws IOException Возможна ошибка доступа либо отсутствия файла по данному адресу
     */
    public String streamToString(FileInputStream file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file));
        StringBuilder fString = new StringBuilder();
        bufferedReader.readLine();
        String line = bufferedReader.readLine();
        while (line != null) {
            fString.append(line.trim());
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return fString.toString();
    }

    /**
     * Метод, конвертирующий поток из файла в структуру строк
     * @param file Поток ввода из файла
     * @return Структура строк, составленная на основе информации из файла
     * @throws IOException Возможна ошибка доступа либо отсутствия файла по данному адресу
     */
    public ArrayList<String> streamToArrayOfCommands(FileInputStream file) throws IOException {
        ArrayList<String> commands = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(file));
        String line = br.readLine();
        while (line != null) {
            commands.add(line);
            line = br.readLine();
        }
        br.close();
        return commands;
    }
}
