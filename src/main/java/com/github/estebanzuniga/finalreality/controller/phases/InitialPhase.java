package com.github.estebanzuniga.finalreality.controller.phases;

/**
 * A class that holds all the information of a InitialPhase.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class InitialPhase extends Phase {

    /**
     * Creates a new InitialPhase, it represents when the user have to pick its characters.
     */
    public InitialPhase() {
        this.inInitialPhase = true;
        this.canEquip = false;
        this.canAttack = false;
        this.canExtractACharacter = false;
        this.canPlayAgain = false;
    }

    @Override
    public void partyIsComplete() {
        controller.partyIsComplete();
    }

    @Override
    public String toString() {
        return "Initial phase";
    }
}