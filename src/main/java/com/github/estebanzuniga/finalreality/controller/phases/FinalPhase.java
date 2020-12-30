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
        canChangeToInitialPhase = true;
        canChangeToCombatPhase = false;
        canChangeToEndTurnPhase = false;
        canChangeToFinalPhase = false;

        this.inInitialPhase = false;
        this.canAttack = false;
        this.canExtractACharacter = false;
        this.canEquip = false;
        this.canPlayAgain = true;
    }

    @Override
    public void newGame() throws InvalidMovementException {
        super.newGame();
        try {
            toInitialPhase();
        } catch (InvalidTransitionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toInitialPhase() throws InvalidTransitionException {
        //changePhase(new InitialPhase());
        super.toInitialPhase();
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
