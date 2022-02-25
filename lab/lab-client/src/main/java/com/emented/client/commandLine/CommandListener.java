package com.emented.client.commandLine;

import com.emented.client.annotations.ConsoleCommand;
import com.emented.client.entities.CollectionOfMusicBands;
import com.emented.client.exceptions.LoopPossibilityException;
import com.emented.client.parser.XMLParser;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Locale;
import java.util.NoSuchElementException;

/**
 * Класс, отвечающий за считывание и выполнение команд
 */
public class CommandListener {

    /**
     * Константа, отвечающая за переполнения очереди
     */
    private final int dequeOverflow = 10;

    /**
     * Очередь, хранящая историю команд
     */
    private final ArrayDeque<String> queueOfCommands = new ArrayDeque<>(9);
    /**
     * Структура, хранящая название команды и метод, ее реализующий
     */
    private final Map<String, Method> availableCommands = new HashMap<>();
    /**
     * Коллекция, с которой мы работаем
     */
    private final CollectionOfMusicBands collectionInWork;
    /**
     * Экземпляр класса парсера для записи в файл
     */
    private final XMLParser parser = new XMLParser();

    /**
     * Конструктор, принимающий класс коллекции групп, а так же заполняющий структуру доступных команд, используя
     * рефлексию
     * @param collectionOfMusicBands
     */
    public CommandListener(CollectionOfMusicBands collectionOfMusicBands) {
        this.collectionInWork = collectionOfMusicBands;
        for (Method methodForCommand : CommandListener.class.getDeclaredMethods()) {
            if (methodForCommand.isAnnotationPresent(ConsoleCommand.class)) {
                ConsoleCommand consoleCommand = methodForCommand.getAnnotation(ConsoleCommand.class);
                availableCommands.put(consoleCommand.nameOfCommand(), methodForCommand);
            }
        }
    }

    /**
     * Метод, реализующий команду help
     */
    @ConsoleCommand(nameOfCommand = "help",
            args = "",
            description = "вывести справку по доступным командам")
    private void help() {
        System.out.println("Доступные команды:");
        for (Method method : availableCommands.values()) {
            ConsoleCommand consoleCommand = method.getAnnotation(ConsoleCommand.class);
            System.out.println("Название команды: " + consoleCommand.nameOfCommand() + ", " + "аргументы: "
                    + ((consoleCommand.amountOfArgs() == 0) ? "команда не требует аргументов" : consoleCommand.args())
                    + ", описание: " + consoleCommand.description());
        }
    }

    /**
     * Метод, реализующий команду info
     */
    @ConsoleCommand(nameOfCommand = "info",
            args = "",
            description = "вывести информацию о коллекции")
    private void info() {
        System.out.println(collectionInWork.returnInfo());
    }

    /**
     * Метод, реализующий команду show
     */
    @ConsoleCommand(nameOfCommand = "show", args = "", description = "вывести все элементы коллекции и информацию о них")
    private void show() {
        collectionInWork.show();
    }

    /**
     * Метод, реализующий команду add
     */
    @ConsoleCommand(nameOfCommand = "add",
            amountOfArgs = 1,
            args = "имя, далее будет вызван конструктор группы",
            description = "добавить новый элемент в коллекцию")
    private void add(String name) {
        try {
            MusicBandGenerator generator = new MusicBandGenerator(name);
            generator.setAnotherVariables();
            collectionInWork.addMusicBand(generator.getGeneratedMusicBand());
        } catch (NumberFormatException nfe) {
            System.out.println("\u001B[31m" + "Ошибка при вводе числа" + "\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[m");
        }
    }

    /**
     * Метод, реализующий команду exit
     */
    @ConsoleCommand(nameOfCommand = "exit",
            args = "",
            description = "завершить работу с коллекцией (все ваши изменения будут утеряны)")
    private void exit() {
        System.exit(0);
    }

    /**
     * Метод, реализующий команду history
     */
    @ConsoleCommand(nameOfCommand = "history",
            args = "",
            description = "вывести последние 9 команд")
    private void history() {
        for (String name : queueOfCommands) {
            System.out.println(name);
        }
    }

    /**
     * Метод, реализующий команду clear
     */
    @ConsoleCommand(nameOfCommand = "clear",
            args = "",
            description = "очистить коллекцию")
    private void clear() {
        if (collectionInWork.getMusicBands().isEmpty()) {
            System.out.println("\u001B[31m" + "Коллекция уже пуста" + "\u001B[0m");
        } else {
            collectionInWork.clearCollection();
            System.out.println("Коллекция очищена");
        }
    }

    /**
     * Метод, реализующий команду save
     */
    @ConsoleCommand(nameOfCommand = "save",
            args = "",
            description = "сохранить коллекцию в файл")
    private void save() throws IOException {
        parser.writeToXMLofExistingInstance(collectionInWork);
        System.out.println("Коллекция сохранена в файл");
    }

    /**
     * Метод, реализующий команду remove_by_id
     */
    @ConsoleCommand(nameOfCommand = "remove_by_id",
            amountOfArgs = 1,
            args = "id",
            description = "удалить группу из коллекции по ее id")
    private void removeByID(String id) {
        long finalId;
        try {
            finalId = Long.parseLong(id);
        } catch (NumberFormatException n) {
            System.out.println("\u001B[31m" + "Ошибка при вводе числа, попробуйте еще раз" + "\u001B[0m");
            return;
        }
        collectionInWork.removeBandById(finalId);
        System.out.println("Группа с ID: " + finalId + " удалена из коллекции");
    }

    /**
     * Метод, реализующий команду update
     */
    @ConsoleCommand(nameOfCommand = "update",
            amountOfArgs = 2,
            args = "id, имя, далее будет вызван конструктор новой группы",
            description = "обновить значение элемента коллекции, id которого равен заданному")
    private void update(String id, String name) {
        Long finalId;
        try {
            finalId = Long.parseLong(id);
        } catch (NumberFormatException n) {
            System.out.println("\u001B[31m" + "Ошибка при вводе числа, попробуйте еще раз" + "\u001B[0m");
            return;
        }
        try {
            MusicBandGenerator generator = new MusicBandGenerator(name, finalId);
            generator.setAnotherVariables();
            collectionInWork.updateById(finalId, generator.getGeneratedMusicBand());
        } catch (NumberFormatException nfe) {
            System.out.println("\u001B[31m" + "Ошибка при вводе числа" + "\u001B[0m");
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }
    }

    /**
     * Метод, реализующий команду add_if_max
     */
    @ConsoleCommand(nameOfCommand = "add_if_max",
            amountOfArgs = 1,
            args = "имя, далее будет вызван конструктор новой группы",
            description = "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции")
    private void addIfMax(String name) {
        try {
            MusicBandGenerator generator = new MusicBandGenerator(name);
            generator.setAnotherVariables();
            collectionInWork.addIfMax(generator.getGeneratedMusicBand());
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }
    }

    /**
     * Метод, реализующий команду remove_greater
     */
    @ConsoleCommand(nameOfCommand = "remove_greater",
            amountOfArgs = 1,
            args = "имя, далее будет вызван конструктор новой группы",
            description = "удалить из коллекции все элементы, превышающие заданный")
    private void removeGreater(String name) {
        try {
            MusicBandGenerator generator = new MusicBandGenerator(name);
            generator.setAnotherVariables();
            collectionInWork.removeIfGreater(generator.getGeneratedMusicBand());
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }
    }

    /**
     * Метод, реализующий команду remove_any_by_number_of_participants
     */
    @ConsoleCommand(nameOfCommand = "remove_any_by_number_of_participants",
            amountOfArgs = 1,
            args = "число участников",
            description = "удалить из коллекции группу с заданным числом участников")
    private void removeAnyByNumberOfParticipants(String numberOfParticipants) {
        long finalNumber;
        try {
            finalNumber = Long.parseLong(numberOfParticipants);
        } catch (NumberFormatException n) {
            System.out.println("\u001B[31m" + "Ошибка при вводе числа, попробуйте еще раз" + "\u001B[0m");
            return;
        }
        collectionInWork.removeAnyByNumberOfParticipants(finalNumber);
    }

    /**
     * Метод, реализующий команду min_by_studio
     */
    @ConsoleCommand(nameOfCommand = "min_by_studio",
            args = "",
            description = "вывести любой объект из коллекции, значение поля studio которого является минимальным")
    private void minByStudio() {
        System.out.println(collectionInWork.returnMinByStudio());
    }

    /**
     * Метод, реализующий команду count_less_than_number_of_participants
     */
    @ConsoleCommand(nameOfCommand = "count_less_than_number_of_participants",
            amountOfArgs = 1,
            args = "количество участников",
            description = "вывести количество групп, количество участников которых меньше заданного")
    private void countLessThanNumberOfParticipants(String numberOfParticipants) {
        long finalNumber;
        try {
            finalNumber = Long.parseLong(numberOfParticipants);
        } catch (NumberFormatException n) {
            System.out.println("\u001B[31m" + "Ошибка при вводе числа, попробуйте еще раз" + "\u001B[0m");
            return;
        }
        System.out.println("Групп с количеством участников меньше чем " + finalNumber + ": "
                + collectionInWork.countLessThanNumberOfParticipants(finalNumber));
    }

    /**
     * Метод, реализующий команду execute_script
     */
    @ConsoleCommand(nameOfCommand = "execute_script",
            amountOfArgs = 1,
            args = "имя файла",
            description = "считать и исполнить скрипт из указанного файла")
    private void executeScript(String fileName) {
        ScriptReader sr = new ScriptReader();
        sr.readCommandsFromFile(fileName);
        ArrayList<String> commands = sr.getCommandsFromFile();
        if (commands.contains("execute_script " + fileName)) {
            throw new LoopPossibilityException("\u001B[31m" + "Внутри скрипта найден его вызов, возможно зацикливание" + "\u001B[0m");
        }
        for (String command : commands) {
            System.out.println(command);
            performCommand(command);
            System.out.println("\n");
        }
    }

    /**
     * Метод, считываюший команды с консоли и вызывающий их выполнение
     */
    public void readCommandsFromConsole() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String line = sc.nextLine();
                performCommand(line);
            } catch (NoSuchElementException e) {
                System.out.println("\u001B[31m" + "Введен недопустимый символ" + "\u001B[0m");
                System.exit(0);
            }
        }
    }

    /**
     * Метод, выполняющий команду
     * @param command Команда для выполнения
     */
    private void performCommand(String command) {
        String[] splitedString = command.split(" ");
        String commandName = splitedString[0].toLowerCase(Locale.ROOT);
        String[] commandsArgs = Arrays.copyOfRange(splitedString, 1, splitedString.length);
        try {
            if (availableCommands.containsKey(commandName)) {
                Method methodOfCommand = availableCommands.get(commandName);
                ConsoleCommand consoleCommand = methodOfCommand.getAnnotation(ConsoleCommand.class);
                if (commandsArgs.length != consoleCommand.amountOfArgs()) {
                    System.out.println("\u001B[31m" + "Неверное количество аргументов, команда " + commandName + " требует "
                            + consoleCommand.amountOfArgs() + " аргументов" + "\u001B[0m");
                } else {
                    methodOfCommand.invoke(this, commandsArgs);
                    queueOfCommands.addFirst(commandName);
                    if (queueOfCommands.size() == dequeOverflow) {
                        queueOfCommands.pollLast();
                    }
                }
            } else {
                System.out.println("\u001B[31m" + "Такой команды не существует, для справки введите команду help" + "\u001B[0m");
            }
        } catch (Exception exception) {
            System.out.println("\u001B[31m" + exception.getCause().getMessage() + "\u001B[0m");
        }
    }
}
