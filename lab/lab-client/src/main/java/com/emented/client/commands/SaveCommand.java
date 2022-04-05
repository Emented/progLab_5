package com.emented.client.commands;

import com.emented.client.entities.CollectionOfMusicBands;
import com.emented.client.exceptions.WrongAmountOfArgsException;
import com.emented.client.parser.XMLParser;
import com.emented.client.util.OutputUtil;
import com.emented.client.validator.Validators;

import java.io.IOException;

public class SaveCommand extends AbstractCommand {

    private final CollectionOfMusicBands collectionInWork;
    private final XMLParser parser;

    public SaveCommand(CollectionOfMusicBands collectionInWork, XMLParser parser) {
        super("save", 0, "сохранить коллекцию в файл");
        this.collectionInWork = collectionInWork;
        this.parser = parser;
    }

    @Override
    public void executeCommand(String[] commandArgs) {
        try {
            Validators.validateAmountOfArgs(commandArgs, getAmountOfArgs());
            parser.writeToXMLofExistingInstance(collectionInWork);
            OutputUtil.printSuccessfulMessage("Коллекция сохранена в файл");
        } catch (WrongAmountOfArgsException e) {
            OutputUtil.printErrorMessage(e.getMessage());
        } catch (IOException e) {
            OutputUtil.printErrorMessage("Отсутствуют права на запись в файл, либо путь к файлу изменился");
        }
    }
}
