package com.emented.client.commands;

import com.emented.client.util.MusicBandGenerator;
import com.emented.client.entities.CollectionOfMusicBands;
import com.emented.client.exceptions.GroupNotMaxException;
import com.emented.client.exceptions.WrongAmountOfArgsException;
import com.emented.client.util.OutputUtil;
import com.emented.client.validator.Validators;

public class AddIfMaxCommand extends AbstractCommand {
    private final CollectionOfMusicBands collectionInWork;

    public AddIfMaxCommand(CollectionOfMusicBands collectionInWork) {
        super("add_if_max", 0, "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.collectionInWork = collectionInWork;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateAmountOfArgs(commandArgs, getAmountOfArgs());
            MusicBandGenerator generator = new MusicBandGenerator();
            generator.setAnotherVariables();
            collectionInWork.addIfMax(generator.getGeneratedMusicBand());
        } catch (WrongAmountOfArgsException | IllegalArgumentException | GroupNotMaxException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
