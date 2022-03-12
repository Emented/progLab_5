package com.emented.client.commands;

import com.emented.client.exceptions.WrongAmountOfArgsException;
import com.emented.client.util.OutputUtil;
import com.emented.client.validator.Validators;

public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit", 0, "завершить работу с коллекцией (все ваши изменения будут утеряны)");
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateAmountOfArgs(commandArgs, getAmountOfArgs());
            System.out.println("Принудительный выход из программы");
            System.exit(0);
        } catch (WrongAmountOfArgsException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
