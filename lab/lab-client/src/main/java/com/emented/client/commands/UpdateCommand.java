package com.emented.client.commands;

import com.emented.client.util.MusicBandGenerator;
import com.emented.client.entities.CollectionOfMusicBands;
import com.emented.client.exceptions.IDNotFoundException;
import com.emented.client.exceptions.WrongAmountOfArgsException;
import com.emented.client.exceptions.WrongArgException;
import com.emented.client.util.OutputUtil;
import com.emented.client.validator.Validators;

public class UpdateCommand extends AbstractCommand {


    private final CollectionOfMusicBands collectionInWork;

    public UpdateCommand(CollectionOfMusicBands collectionOfMusicBands) {
        super("update", 1,
                "обновить значение элемента коллекции, id которого равен заданному",
                "id элемента коллекции");
        this.collectionInWork = collectionOfMusicBands;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateAmountOfArgs(commandArgs, getAmountOfArgs());
            long id = Validators.validateArg(arg -> ((long) arg) > 0,
                    "ID должен быть больше 0",
                    Long::parseLong,
                    commandArgs[0]);
            collectionInWork.checkId(id);
            MusicBandGenerator generator = new MusicBandGenerator(id);
            generator.setAnotherVariables();
            collectionInWork.updateById(id, generator.getGeneratedMusicBand());
        } catch (WrongAmountOfArgsException | WrongArgException | IDNotFoundException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        } catch (IllegalArgumentException e) {
            OutputUtil.printErrorMessage("Не верный тип данных аргумента");
        }
    }
}
