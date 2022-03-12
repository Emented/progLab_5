package com.emented.client.commands;

import com.emented.client.entities.CollectionOfMusicBands;
import com.emented.client.exceptions.WrongAmountOfArgsException;
import com.emented.client.util.OutputUtil;
import com.emented.client.validator.Validators;

public class InfoCommand extends AbstractCommand {

    private final CollectionOfMusicBands collectionInWork;

    public InfoCommand(CollectionOfMusicBands collectionInWork) {
        super("info", 0, "вывести информацию о коллекции");
        this.collectionInWork = collectionInWork;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateAmountOfArgs(commandArgs, getAmountOfArgs());
            OutputUtil.printSuccessfulMessage("Информация о коллекции:");
            System.out.println(collectionInWork.returnInfo());
        } catch (WrongAmountOfArgsException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
