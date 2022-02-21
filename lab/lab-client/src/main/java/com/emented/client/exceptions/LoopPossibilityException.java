package com.emented.client.exceptions;

/**
 * Unchecked исключение, отвечающее за возможность зациливания при выполенении скрипта
 */
public class LoopPossibilityException extends RuntimeException {

    /**
     * Конструктор исключения
     * @param message Сообщение, описывающее исключение
     */
    public LoopPossibilityException(String message) {
        super(message);
    }
}
