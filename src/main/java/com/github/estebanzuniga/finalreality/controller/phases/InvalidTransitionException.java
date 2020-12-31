package com.github.estebanzuniga.finalreality.controller.phases;

/**
 * A class that appears when the user tries to perform an invalid
 * transition from a phase to another one.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class InvalidTransitionException extends Exception {

    public InvalidTransitionException(String message) {
        super(message);
    }
}