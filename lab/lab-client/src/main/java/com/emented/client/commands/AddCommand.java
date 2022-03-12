package com.emented.client.commands;

import com.emented.client.util.MusicBandGenerator;
import com.emented.client.entities.CollectionOfMusicBands;
import com.emented.client.exceptions.WrongAmountOfArgsException;
import com.emented.client.util.OutputUtil;
import com.emented.client.validator.Validators;

public class AddCommand extends AbstractCommand {

    private final CollectionOfMusicBands collectionInWork;

    public AddCommand(CollectionOfMusicBands collectionInWork) {
        super("add", 0, "добавить новый элемент в коллекцию");
        this.collectionInWork = collectionInWork;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateAmountOfArgs(commandArgs, getAmountOfArgs());
            MusicBandGenerator generator = new MusicBandGenerator();
            generator.setAnotherVariables();
            collectionInWork.addMusicBand(generator.getGeneratedMusicBand());
        } catch (WrongAmountOfArgsException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
