package com.emented.client.commands;

import com.emented.client.commandLine.CommandListener;
import com.emented.client.commandLine.ScriptReader;
import com.emented.client.exceptions.LoopPossibilityException;
import com.emented.client.exceptions.WrongAmountOfArgsException;
import com.emented.client.util.OutputUtil;
import com.emented.client.validator.Validators;

import java.io.IOException;
import java.util.ArrayList;

public class ExecuteScriptCommand extends AbstractCommand {

    private final ScriptReader reader;

    public ExecuteScriptCommand(ScriptReader reader) {
        super("execute_script", 1, "считать и исполнить скрипт из указанного файла", "имя файла");
        this.reader = reader;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateAmountOfArgs(commandArgs, getAmountOfArgs());
            reader.readCommandsFromFile(commandArgs[0]);
            ArrayList<String> commands = reader.getCommandsFromFile();
            if (commands.contains("execute_script " + commandArgs[0])) {
                throw new LoopPossibilityException("Внутри скрипта найден его вызов, возможно зацикливание");
            }
            for (String command : commands) {
                OutputUtil.printSuccessfulMessage(command);
                CommandListener.manager.performCommand(command);
            }
        } catch (WrongAmountOfArgsException | IOException | LoopPossibilityException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
