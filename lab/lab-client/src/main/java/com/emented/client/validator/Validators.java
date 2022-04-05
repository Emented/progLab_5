package com.emented.client.validator;

import com.emented.client.entities.CollectionOfMusicBands;
import com.emented.client.entities.Coordinates;
import com.emented.client.entities.MusicBand;
import com.emented.client.entities.Studio;
import com.emented.client.exceptions.WrongAmountOfArgsException;
import com.emented.client.exceptions.WrongArgException;
import com.emented.client.util.OutputUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;

/**
 * Класс, содержащий в себе методы валидации
 */
public final class Validators {

    private Validators() { }

    public static void validateClass(CollectionOfMusicBands collectionInWork) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        for (MusicBand m : collectionInWork.getMusicBands()) {
            Set<ConstraintViolation<Coordinates>> validatedCoordinates = validator.validate(m.getCoordinates());
            Set<ConstraintViolation<Studio>> validatedStudio = new HashSet<>();
            if (m.getStudio() != null) {
                validatedStudio = validator.validate(m.getStudio());
            }
            Set<ConstraintViolation<MusicBand>> validatedBand = validator.validate(m);
            if (!validatedBand.isEmpty() || !validatedCoordinates.isEmpty() || !validatedStudio.isEmpty()) {
                OutputUtil.printErrorMessage("В исходном файле допущены ошибки");
                validatedBand.stream().map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
                validatedCoordinates.stream().map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
                validatedStudio.stream().map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
                System.exit(1);
            }
        }
        OutputUtil.printSuccessfulMessage("Данные из файла перенесы в коллекцию, приложение успешно запущено");
    }

    public static <T> T validateInput(Predicate<Object> predicate,
                                      String message,
                                      String error,
                                      String wrong,
                                      Function<String, T> function,
                                      Boolean nullable,
                                      Scanner sc) {
        System.out.println(message);
        String input;
        T value;
        do {
            try {
                input = sc.nextLine();
                if ("".equals(input) && Boolean.TRUE.equals(nullable)) {
                    return null;
                } else if ("".equals(input) && !Boolean.TRUE.equals(nullable)) {
                    OutputUtil.printErrorMessage("Данное поле не может быть null, повторите ввод");
                    continue;
                }
                value = function.apply(input);
            } catch (IllegalArgumentException e) {
                OutputUtil.printErrorMessage(error);
                continue;
            } catch (NoSuchElementException e) {
                OutputUtil.printErrorMessage("Введен недопустимый символ");
                System.exit(1);
                return null;
            }
            if (predicate.test(value)) {
                return value;
            } else {
                OutputUtil.printErrorMessage(wrong);
            }
        } while (true);
    }

    public static void validateAmountOfArgs(String[] args, int amountOfArgs) throws WrongAmountOfArgsException {
        if (args.length != amountOfArgs) {
            throw new WrongAmountOfArgsException("Неверное количество аргументов, данная команда требует " + amountOfArgs + " аргументов");
        }
    }

    public static <T> T validateArg(Predicate<Object> predicate,
                                    String wrong,
                                    Function<String, T> function,
                                    String argument) throws WrongArgException, IllegalArgumentException {
        T value = function.apply(argument);
        if (predicate.test(value)) {
            return value;
        } else {
            throw new WrongArgException(wrong);
        }
    }

    public static String validateStringInput(String message,
                                             Boolean nullable,
                                             Scanner sc) {
        System.out.println(message);
        String input;
        String value;
        do {
            try {
                input = sc.nextLine();
                if ("".equals(input) && Boolean.TRUE.equals(nullable)) {
                    return null;
                } else if ("".equals(input) && !Boolean.TRUE.equals(nullable)) {
                    OutputUtil.printErrorMessage("Данное поле не может быть null, повторите ввод");
                    continue;
                }
                value = input;
            } catch (NoSuchElementException e) {
                OutputUtil.printErrorMessage("Введен недопустимый символ");
                System.exit(1);
                return null;
            }
            return value;
        } while (true);
    }
}
