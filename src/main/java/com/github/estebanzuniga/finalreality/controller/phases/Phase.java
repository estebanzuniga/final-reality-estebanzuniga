package com.github.estebanzuniga.finalreality.controller.phases;

import com.github.estebanzuniga.finalreality.controller.GameController;
import org.jetbrains.annotations.NotNull;

public class Phase {
    protected GameController controller;

    public void setController(final @NotNull GameController controller) {
        this.controller = controller;
    }

    protected void changePhase(Phase phase) {
        controller.setPhase(phase);
    }

    public void endTurn() throws InvalidMovementException {
        throw new InvalidMovementException("Can't end turn on " + this.toString() + ".");
    }

    public void toSelectingPartyPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() + " to Selecting party phase");
    }

    public void toSelectingWeaponPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() + " to Selecting weapon phase");
    }

    public void toSelectingAttackTargetPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() + " to Selecting attack target phase");
    }

    public void toEnemyAttackingPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() + " to Enemy attacking phase");
    }

}

