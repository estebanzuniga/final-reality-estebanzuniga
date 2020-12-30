package com.github.estebanzuniga.finalreality.controller.phases;

/**
 * A class that holds all the information of an EndTurnPhase.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class EndTurnPhase extends Phase {

    /**
     * Creates a new EndTurnPhase, it represents when the user or an enemy ends it turn.
     */
    public EndTurnPhase() {
        canChangeToInitialPhase = false;
        canChangeToCombatPhase = true;
        canChangeToEndTurnPhase = false;
        canChangeToFinalPhase = true;

        this.inInitialPhase = false;
        this.canEquip = false;
        this.canAttack = false;
        this.canExtractACharacter = true;
        this.canPlayAgain = false;
    }

    @Override
    public void extractCharacter() throws InvalidMovementException {
        super.extractCharacter();
        if (controller.enemyWon() || controller.playerWon()) {
            toFinalPhase();
        }
        else {
            while (controller.getTurns().isEmpty()) { }
            try {
                toCombatPhase();
            } catch (InvalidTransitionException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void toCombatPhase() throws InvalidTransitionException {
        //changePhase(new CombatPhase());
        super.toCombatPhase();
    }

    @Override
    public void toFinalPhase() {
        changePhase(new FinalPhase());
    }

    /**
     * Return the name of a phase.
     * @return
     *        the name of a phase.
     */
    public String toString() {
        return "End turn phase";
    }
}
