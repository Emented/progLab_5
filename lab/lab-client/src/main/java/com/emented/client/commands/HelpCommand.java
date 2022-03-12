package com.emented.client.commands;

import com.emented.client.exceptions.WrongAmountOfArgsException;
import com.emented.client.util.OutputUtil;
import com.emented.client.validator.Validators;

import java.util.HashMap;

public class HelpCommand extends AbstractCommand {

    private final HashMap<String, AbstractCommand> availableCommands;

    public HelpCommand(HashMap<String, AbstractCommand> availableCommands) {
        super("help", 0, "вывести справку по доступным командам");
        this.availableCommands = availableCommands;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateAmountOfArgs(commandArgs, getAmountOfArgs());
            OutputUtil.printSuccessfulMessage("Доступные комманды:");
            for (AbstractCommand command : availableCommands.values()) {
                System.out.println(command.toString());
            }
        } catch (WrongAmountOfArgsException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
