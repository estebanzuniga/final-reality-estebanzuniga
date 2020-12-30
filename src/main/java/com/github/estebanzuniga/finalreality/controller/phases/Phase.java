package com.github.estebanzuniga.finalreality.controller.phases;

import com.github.estebanzuniga.finalreality.controller.GameController;
import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.character.player.IPlayerCharacter;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds information of a phase.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class Phase {

    protected GameController controller;
    protected boolean inInitialPhase = false;
    protected boolean canExtractACharacter = false;
    protected boolean canEquip = false;
    protected boolean canAttack = false;
    protected boolean canPlayAgain = false;

    /**
     * Sets the variable controller as the current controller.
     * @param controller
     *       the current controller.
     */
    public void setController(final @NotNull GameController controller) {
        this.controller = controller;
    }

    /**
     * Changes from one phase to another one.
     * @param phase
     *       the new phase.
     */
    protected void changePhase(Phase phase) {
        controller.setPhase(phase);
    }

    /**
     * Calls controller partyIsComplete method.
     * @throws InvalidMovementException
     *        when inInitialPhase is false
     */
    public void partyIsComplete() throws InvalidMovementException {
        if (!inInitialPhase) {
            throw new InvalidMovementException("You can´t set party now.");
        }
        controller.partyIsComplete();
    }

    /**
     * Calls controller equipWeapon method.
     * @param character
     *       the player character.
     * @param weapon
     *       the weapon.
     * @throws InvalidMovementException
     *        when canEquip is false.
     */
    public void equipWeapon(IPlayerCharacter character, IWeapon weapon) throws InvalidMovementException {
        if (!canEquip) {
            throw new InvalidMovementException("You can´t equip a weapon now.");
        }
        controller.equipWeapon(character, weapon);
    }

    /**
     * Calls controller attack method.
     * @param attacker
     *       the character that is attacking.
     * @param attacked
     *       the attacked character.
     * @throws InvalidMovementException
     *        when canAttack is false.
     */
    public void attack(ICharacter attacker, ICharacter attacked) throws InvalidMovementException {
        if (!canAttack) {
            throw new InvalidMovementException("You can't attack now.");
        }
        controller.attack(attacker, attacked);
    }

    /**
     * Calls controller extractCharacter method.
     * @throws InvalidMovementException
     *        when canExtractACharacter is false.
     */
    public void extractCharacter() throws InvalidMovementException {
        if (!canExtractACharacter) {
            throw new InvalidMovementException("You can´t extract a character now.");
        }
        controller.extractCharacter();
    }

    /**
     * Calls controller newGame method.
     * @throws InvalidMovementException
     *        when canPlayAgain is false.
     */
    public void newGame() throws InvalidMovementException {
        if (!canPlayAgain) {
            throw new InvalidMovementException("You can´t play again now");
        }
        controller.newGame();
    }

    /**
     * Changes the current phase to initial phase.
     * @throws InvalidTransitionException
     *        when can not change to a specific phase.
     */
    public void toInitialPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can´t change form " + this.toString() + " to Initial phase");
    }

    /**
     * Changes the current phase to combat phase.
     * @throws InvalidTransitionException
     *        when can not change to a specific phase.
     */
    public void toCombatPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() + " to Attack phase");
    }

    /**
     * Changes the current phase to end turn phase.
     * @throws InvalidTransitionException
     *        when can not change to a specific phase.
     */
    public void toEndTurnPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can´t change from " + this.toString() + " to End turn phase");
    }

    /**
     * Changes the current phase to final phase.
     * @throws InvalidTransitionException
     *        when can not change to a specific phase.
     */
    public void toFinalPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() + " to Final phase");
    }

    /**
     * Return the name of a phase.
     * @return
     *        the name of a phase.
     */
    public String toString() {
        return "Name phase";
    }
}

