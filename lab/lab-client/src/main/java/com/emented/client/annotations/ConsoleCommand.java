package com.emented.client.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация, указывающая на метод для конкретной консольной команды
 */
@Retention(RetentionPolicy.RUNTIME)

@Target(ElementType.METHOD)
public @interface ConsoleCommand {

    /**
     * Имя команды
     */
    String nameOfCommand();

    /**
     * Аргументы команды
     */
    String args();

    /**
     * Количество аргументво команды
     */
    int amountOfArgs() default 0;

    /**
     * Описание команды
     */
    String description();
}
