package com.github.estebanzuniga.finalreality.controller.phases;

import com.github.estebanzuniga.finalreality.controller.GameController;
import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.character.player.IPlayerCharacter;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

public class Phase {

    protected GameController controller;

    protected boolean canSetEnemies = false;
    protected boolean canExtractACharacter = false;
    protected boolean canEquip = false;
    protected boolean canAttack = false;
    protected boolean canPlayAgain = false;

    public void setController(final @NotNull GameController controller) {
        this.controller = controller;
    }

    protected void changePhase(Phase phase) {
        controller.setPhase(phase);
    }



    public void setEnemies() throws InvalidMovementException {
        if (!canSetEnemies) {
            throw new InvalidMovementException("You can´t set party now.");
        }
        controller.setEnemies();
    }

    public void equipWeapon(IPlayerCharacter character, IWeapon weapon) throws InvalidMovementException {
        if (!canEquip) {
            throw new InvalidMovementException("You can´t equip a weapon now.");
        }
        controller.equipWeapon(character, weapon);
    }

    public void attack(ICharacter attacker, ICharacter attacked) throws InvalidMovementException {
        if (!canAttack) {
            throw new InvalidMovementException("You can't attack now.");
        }
        controller.attack(attacker, attacked);
    }

    public void extractCharacter() throws InvalidMovementException {
        if (!canExtractACharacter) {
            throw new InvalidMovementException("You can´t extract a character now.");
        }
        controller.extractCharacter();
    }

    public void newGame(boolean trueOrFalse) throws InvalidMovementException {
        if (!canPlayAgain) {
            throw new InvalidMovementException("You can´t play again now");
        }
        controller.newGame(true);
    }

    public void toInitialPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can´t change form " + this.toString() + " to Initial phase");
    }

    public void toAttackPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() + " to Attack phase");
    }

    public void toEndTurnPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can´t change from " + this.toString() + " to End turn phase");
    }

    public void toFinalPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() + " to Final phase");
    }

}

