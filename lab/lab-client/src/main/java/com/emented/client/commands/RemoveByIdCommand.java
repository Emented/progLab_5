package com.emented.client.commands;

import com.emented.client.entities.CollectionOfMusicBands;
import com.emented.client.exceptions.IDNotFoundException;
import com.emented.client.exceptions.WrongAmountOfArgsException;
import com.emented.client.exceptions.WrongArgException;
import com.emented.client.util.OutputUtil;
import com.emented.client.validator.Validators;

public class RemoveByIdCommand extends AbstractCommand {

    private final CollectionOfMusicBands collectionInWork;

    public RemoveByIdCommand(CollectionOfMusicBands collectionInWork) {
        super("remove_by_id", 1, "удалить группу из коллекции по ее id", "id");
        this.collectionInWork = collectionInWork;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateAmountOfArgs(commandArgs, getAmountOfArgs());
            long id = Validators.validateArg(arg -> ((long) arg) > 0,
                    "ID должен быть больше 0",
                    Long::parseLong,
                    commandArgs[0]);
            collectionInWork.removeBandById(id);
            System.out.println("Группа с ID: " + id + " удалена из коллекции");
        } catch (IllegalArgumentException e) {
            OutputUtil.printErrorMessage("Не верный тип данных аргумента");
        } catch (WrongArgException | WrongAmountOfArgsException | IDNotFoundException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        }
    }
}
