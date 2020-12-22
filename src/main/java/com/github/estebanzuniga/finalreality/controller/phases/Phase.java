package com.github.estebanzuniga.finalreality.controller.phases;

import com.github.estebanzuniga.finalreality.controller.GameController;
import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.character.player.IPlayerCharacter;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

public class Phase {

    protected GameController controller;

    protected boolean canSetEnemies = false;
    protected boolean canAttack = false;
    protected boolean canExtractACharacter = false;
    protected boolean canEquip = false;

    public void setController(final @NotNull GameController controller) {
        this.controller = controller;
    }

    protected void changePhase(Phase phase) {
        controller.setPhase(phase);
    }

    public void attack(ICharacter attacker, ICharacter attacked) throws InvalidMovementException {
        if (!canAttack) {
            throw new InvalidMovementException("You can't attack now.");
        }
        controller.attack(attacker, attacked);
    }

    public ICharacter extractCharacter() throws InvalidMovementException {
        if (!canExtractACharacter) {
            throw new InvalidMovementException("You can´t extract a character now.");
        }
        return controller.extractCharacter();
    }

    public void equipWeapon(IPlayerCharacter character, IWeapon weapon) throws InvalidMovementException {
        if (!canEquip) {
            throw new InvalidMovementException("You can´t equip a weapon now.");
        }
        controller.equipWeapon(character, weapon);
    }

    public void setEnemies() throws InvalidMovementException {
        if (!canSetEnemies) {
            throw new InvalidMovementException("You can´t set party now.");
        }
        controller.setEnemies();
    }



    public void endTurn() throws InvalidMovementException {
        throw new InvalidMovementException("Can't end turn on " + this.toString() + ".");
    }





    public void toMainPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can´t change from " + this.toString() + " to Main phase");
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

