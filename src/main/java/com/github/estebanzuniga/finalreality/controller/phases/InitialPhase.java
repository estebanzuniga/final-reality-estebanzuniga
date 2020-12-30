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
        canChangeToInitialPhase = false;
        canChangeToCombatPhase = true;
        canChangeToEndTurnPhase = false;
        canChangeToFinalPhase = false;

        this.inInitialPhase = true;
        this.canEquip = false;
        this.canAttack = false;
        this.canExtractACharacter = false;
        this.canPlayAgain = false;
    }

    @Override
    public void partyIsComplete() throws InvalidMovementException {
        super.partyIsComplete();
    }

    /**
     * Return the name of a phase.
     * @return
     *        the name of a phase.
     */
    public String toString() {
        return "Initial phase";
    }
}