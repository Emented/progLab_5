package com.emented.client.commands;

import com.emented.client.entities.CollectionOfMusicBands;
import com.emented.client.exceptions.CollectionIsEmptyException;
import com.emented.client.exceptions.GroupNotFoundException;
import com.emented.client.exceptions.WrongAmountOfArgsException;
import com.emented.client.exceptions.WrongArgException;
import com.emented.client.util.OutputUtil;
import com.emented.client.validator.Validators;

public class RemoveAnyByNumberOfParticipantsCommand extends AbstractCommand {

    private final CollectionOfMusicBands collectionInWork;

    public RemoveAnyByNumberOfParticipantsCommand(CollectionOfMusicBands collectionOfMusicBands) {
        super("remove_any_by_number_of_participants",
                1,
                "удалить из коллекции группу с заданным числом участников",
                "количество участников");
        this.collectionInWork = collectionOfMusicBands;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateAmountOfArgs(commandArgs, getAmountOfArgs());
            long numberOfParticipants = Validators.validateArg(arg -> ((long) arg) > 0,
                    "Число участников должно быть больше 0",
                    Long::parseLong,
                    commandArgs[0]);
            collectionInWork.removeAnyByNumberOfParticipants(numberOfParticipants);
        } catch (WrongAmountOfArgsException | WrongArgException | GroupNotFoundException | CollectionIsEmptyException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        } catch (IllegalArgumentException e) {
            OutputUtil.printErrorMessage("Не верный тип данных аргумента");
        }
    }
}
