package com.emented.client.commands;

import com.emented.client.entities.CollectionOfMusicBands;
import com.emented.client.exceptions.WrongAmountOfArgsException;
import com.emented.client.exceptions.WrongArgException;
import com.emented.client.util.OutputUtil;
import com.emented.client.validator.Validators;

public class CountLessThatNumberOfParticipantsCommand extends AbstractCommand {

    private final CollectionOfMusicBands collectionInWork;

    public CountLessThatNumberOfParticipantsCommand(CollectionOfMusicBands collectionOfMusicBands) {
        super("count_less_than_number_of_participants",
                1,
                "вывести количество групп, количество участников которых меньше заданного",
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
            System.out.println("Групп с количеством участников меньше чем " + numberOfParticipants + ": "
                    + collectionInWork.countLessThanNumberOfParticipants(numberOfParticipants));
        } catch (WrongAmountOfArgsException | WrongArgException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        } catch (IllegalArgumentException e) {
            OutputUtil.printErrorMessage("Не верный тип данных аргумента");
        }
    }
}
