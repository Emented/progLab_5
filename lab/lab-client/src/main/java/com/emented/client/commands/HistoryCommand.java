package com.emented.client.commands;

import com.emented.client.exceptions.WrongAmountOfArgsException;
import com.emented.client.util.OutputUtil;
import com.emented.client.validator.Validators;

import java.util.ArrayDeque;

public class HistoryCommand extends AbstractCommand {

    private final ArrayDeque<String> queueOfCommands;

    public HistoryCommand(ArrayDeque<String> queueOfCommands) {
        super("history", 0, "вывести последние 9 команд");
        this.queueOfCommands = queueOfCommands;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateAmountOfArgs(commandArgs, getAmountOfArgs());
            for (String name : queueOfCommands) {
                System.out.println(name);
            }
        } catch (WrongAmountOfArgsException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
