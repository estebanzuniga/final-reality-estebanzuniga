package com.github.estebanzuniga.finalreality.controller.phases;

/**
 * A class that appears when the user tries to use a method in another phase.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class InvalidMovementException extends Exception {

    public InvalidMovementException(final String message) {
        super(message);
    }
}