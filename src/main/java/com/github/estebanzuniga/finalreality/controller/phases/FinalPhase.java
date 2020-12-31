package com.github.estebanzuniga.finalreality.controller.phases;

/**
 * A class that holds all the information of a FinalPhase.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class FinalPhase extends Phase {

    /**
     * Creates a new FinalPhase, it represents when the combat finishes.
     */
    public FinalPhase() {

        this.inInitialPhase = false;
        this.canAttack = false;
        this.canExtractACharacter = false;
        this.canEquip = false;
        this.canPlayAgain = true;
    }

    @Override
    public void newGame() {
        controller.newGame();
        toInitialPhase();
    }

    @Override
    public void toInitialPhase() {
        changePhase(new InitialPhase());
    }

    /**
     * Return the name of a phase.
     * @return
     *        the name of a phase.
     */
    public String toString() {
        return "Final phase";
    }
}
