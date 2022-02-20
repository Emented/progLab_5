package com.emented.client.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)

@Target(ElementType.METHOD)
public @interface ConsoleCommand {
    String nameOfCommand();
    String args();
    int amountOfArgs() default 0;
    String description();
}
