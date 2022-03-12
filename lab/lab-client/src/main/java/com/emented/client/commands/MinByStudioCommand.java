package com.emented.client.commands;

import com.emented.client.entities.CollectionOfMusicBands;
import com.emented.client.exceptions.CollectionIsEmptyException;
import com.emented.client.exceptions.WrongAmountOfArgsException;
import com.emented.client.util.OutputUtil;
import com.emented.client.validator.Validators;

public class MinByStudioCommand extends AbstractCommand {

    private final CollectionOfMusicBands collectionInWork;

    public MinByStudioCommand(CollectionOfMusicBands collectionOfMusicBands) {
        super("min_by_studio", 0, "вывести любой объект из коллекции, значение поля studio которого является минимальным");
        this.collectionInWork = collectionOfMusicBands;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateAmountOfArgs(commandArgs, getAmountOfArgs());
            System.out.println(collectionInWork.returnMinByStudio());
        } catch (WrongAmountOfArgsException | CollectionIsEmptyException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
