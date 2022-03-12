package com.emented.client.commands;

import com.emented.client.util.MusicBandGenerator;
import com.emented.client.entities.CollectionOfMusicBands;
import com.emented.client.exceptions.CollectionIsEmptyException;
import com.emented.client.exceptions.WrongAmountOfArgsException;
import com.emented.client.util.OutputUtil;
import com.emented.client.validator.Validators;

public class RemoveGreaterCommand extends AbstractCommand {

    private final CollectionOfMusicBands collectionInWork;

    public RemoveGreaterCommand(CollectionOfMusicBands collectionOfMusicBands) {
        super("remove_greater", 0, "удалить из коллекции все элементы, превышающие заданный");
        this.collectionInWork = collectionOfMusicBands;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateAmountOfArgs(commandArgs, getAmountOfArgs());
            MusicBandGenerator generator = new MusicBandGenerator();
            generator.setAnotherVariables();
            collectionInWork.removeIfGreater(generator.getGeneratedMusicBand());
        } catch (WrongAmountOfArgsException | CollectionIsEmptyException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
