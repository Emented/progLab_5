package com.emented.client.commands;

public abstract class AbstractCommand {

    private final String name;
    private final int amountOfArgs;
    private final String description;
    private final String descriptionOfArgs;

    public AbstractCommand(String name, int amountOfArgs, String description, String descriptionOfArgs) {
        this.name = name;
        this.amountOfArgs = amountOfArgs;
        this.description = description;
        this.descriptionOfArgs = descriptionOfArgs;
    }

    public AbstractCommand(String name, int amountOfArgs, String description) {
        this.name = name;
        this.amountOfArgs = amountOfArgs;
        this.description = description;
        this.descriptionOfArgs = "";
    }

    public abstract void executeCommand(String[] commandArgs);

    public String getName() {
        return name;
    }

    public int getAmountOfArgs() {
        return amountOfArgs;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionOfArgs() {
        return descriptionOfArgs;
    }

    @Override
    public String toString() {
        return "Название команды: " + name + ", " + "аргументы: "
                + ((amountOfArgs == 0) ? "команда не требует аргументов" : descriptionOfArgs)
                + ", описание: " + description;
    }
}
