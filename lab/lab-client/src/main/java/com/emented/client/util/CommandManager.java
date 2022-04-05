package com.emented.client.util;

import com.emented.client.commands.AbstractCommand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

/**
 * Класс, создающий экземпляры комманд и отвечающий за их выполнение
 */
public class CommandManager {
    public static final HashMap<String, AbstractCommand> AVAILABLE_COMMANDS = new HashMap<>();
    public static CommandHistory historyOfCommands = new CommandHistory();

    public CommandManager(AbstractCommand helpCommand,
                          AbstractCommand infoCommand,
                          AbstractCommand showCommand,
                          AbstractCommand addCommand,
                          AbstractCommand updateCommand,
                          AbstractCommand removeByIdCommand,
                          AbstractCommand clearCommand,
                          AbstractCommand saveCommand,
                          AbstractCommand exitCommand,
                          AbstractCommand addIfMaxCommand,
                          AbstractCommand removeGreaterCommand,
                          AbstractCommand historyCommand,
                          AbstractCommand removeAnyByNumberOfParticipantsCommand,
                          AbstractCommand minByStudioCommand,
                          AbstractCommand countLessThanNumberOfParticipantsCommand,
                          AbstractCommand executeScriptCommand) {

        AVAILABLE_COMMANDS.put(helpCommand.getName(), helpCommand);
        AVAILABLE_COMMANDS.put(infoCommand.getName(), infoCommand);
        AVAILABLE_COMMANDS.put(showCommand.getName(), showCommand);
        AVAILABLE_COMMANDS.put(addCommand.getName(), addCommand);
        AVAILABLE_COMMANDS.put(updateCommand.getName(), updateCommand);
        AVAILABLE_COMMANDS.put(removeByIdCommand.getName(), removeGreaterCommand);
        AVAILABLE_COMMANDS.put(clearCommand.getName(), clearCommand);
        AVAILABLE_COMMANDS.put(saveCommand.getName(), saveCommand);
        AVAILABLE_COMMANDS.put(exitCommand.getName(), exitCommand);
        AVAILABLE_COMMANDS.put(addIfMaxCommand.getName(), addIfMaxCommand);
        AVAILABLE_COMMANDS.put(removeGreaterCommand.getName(), removeGreaterCommand);
        AVAILABLE_COMMANDS.put(historyCommand.getName(), historyCommand);
        AVAILABLE_COMMANDS.put(removeAnyByNumberOfParticipantsCommand.getName(), removeAnyByNumberOfParticipantsCommand);
        AVAILABLE_COMMANDS.put(minByStudioCommand.getName(), minByStudioCommand);
        AVAILABLE_COMMANDS.put(countLessThanNumberOfParticipantsCommand.getName(), countLessThanNumberOfParticipantsCommand);
        AVAILABLE_COMMANDS.put(executeScriptCommand.getName(), executeScriptCommand);
    }

    public void performCommand(String command) {
        String[] splitedString = command.split(" ");
        String commandName = splitedString[0].toLowerCase(Locale.ROOT);
        String[] commandsArgs = Arrays.copyOfRange(splitedString, 1, splitedString.length);
        if (AVAILABLE_COMMANDS.containsKey(commandName)) {
            AbstractCommand executingCommand = AVAILABLE_COMMANDS.get(commandName);
            executingCommand.executeCommand(commandsArgs);
            historyOfCommands.pushCommand(commandName);
        } else {
            OutputUtil.printErrorMessage("Такой команды не существует, для справки введите команду help");
        }
    }
}
