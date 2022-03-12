package com.emented.client.commands;

import com.emented.client.entities.CollectionOfMusicBands;
import com.emented.client.exceptions.WrongAmountOfArgsException;
import com.emented.client.util.OutputUtil;
import com.emented.client.validator.Validators;

public class ClearCommand extends AbstractCommand {

    private final CollectionOfMusicBands collectionInWork;

    public ClearCommand(CollectionOfMusicBands collectionInWork) {
        super("clear", 0, "очистить коллекцию");
        this.collectionInWork = collectionInWork;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateAmountOfArgs(commandArgs, getAmountOfArgs());
            if (collectionInWork.getMusicBands().isEmpty()) {
                OutputUtil.printErrorMessage("Коллекция уже пуста");
            } else {
                collectionInWork.clearCollection();
                OutputUtil.printSuccessfulMessage("Коллекция очищена");
            }
        } catch (WrongAmountOfArgsException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
