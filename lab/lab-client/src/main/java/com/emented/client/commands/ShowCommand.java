package com.emented.client.commands;

import com.emented.client.entities.CollectionOfMusicBands;
import com.emented.client.exceptions.WrongAmountOfArgsException;
import com.emented.client.util.OutputUtil;
import com.emented.client.validator.Validators;

public class ShowCommand extends AbstractCommand {

    private final CollectionOfMusicBands collectionInWork;

    public ShowCommand(CollectionOfMusicBands collectionInWork) {
        super("show", 0, "вывести все элементы коллекции и информацию о них");
        this.collectionInWork = collectionInWork;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateAmountOfArgs(commandArgs, getAmountOfArgs());
            collectionInWork.show();
        } catch (WrongAmountOfArgsException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
