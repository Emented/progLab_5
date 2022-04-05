package com.emented.client.commandLine;

import com.emented.client.commands.*;
import com.emented.client.entities.CollectionOfMusicBands;
import com.emented.client.parser.XMLParser;
import com.emented.client.util.CommandManager;
import com.emented.client.util.OutputUtil;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс, отвечающий за считывание и выполнение команд
 */
public class CommandListener {
    public static CommandManager manager;

    private static boolean performanceStatus = true;

    public static void toggleStatus() {
        performanceStatus = !performanceStatus;
    }

    /**
     * Конструктор, принимающий класс коллекции групп, а так же заполняющий структуру доступных команд, используя
     * рефлексию
     */
    public CommandListener(CollectionOfMusicBands collectionOfMusicBands) {
        XMLParser parser = new XMLParser();
        ScriptReader reader = new ScriptReader();
        manager = new CommandManager(new HelpCommand(CommandManager.AVAILABLE_COMMANDS),
                new InfoCommand(collectionOfMusicBands),
                new ShowCommand(collectionOfMusicBands),
                new AddCommand(collectionOfMusicBands),
                new UpdateCommand(collectionOfMusicBands),
                new RemoveByIdCommand(collectionOfMusicBands),
                new ClearCommand(collectionOfMusicBands),
                new SaveCommand(collectionOfMusicBands, parser),
                new ExitCommand(),
                new AddIfMaxCommand(collectionOfMusicBands),
                new RemoveGreaterCommand(collectionOfMusicBands),
                new HistoryCommand(CommandManager.historyOfCommands.getHistory()),
                new RemoveAnyByNumberOfParticipantsCommand(collectionOfMusicBands),
                new MinByStudioCommand(collectionOfMusicBands),
                new CountLessThatNumberOfParticipantsCommand(collectionOfMusicBands),
                new ExecuteScriptCommand(reader));
    }

    /**
     * Метод, считываюший команды с консоли и вызывающий их выполнение
     */
    public void readCommandsFromConsole() {
        Scanner sc = new Scanner(System.in);
        while (performanceStatus) {
            try {
                OutputUtil.printSuccessfulMessageOneStrip("Введите команду: ");
                String line = sc.nextLine();
                manager.performCommand(line);
            } catch (NoSuchElementException e) {
                OutputUtil.printErrorMessage("Введен недопустимый символ");
                System.exit(0);
            }
        }
    }
}
