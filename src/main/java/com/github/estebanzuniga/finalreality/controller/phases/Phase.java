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
     */
    public void partyIsComplete() { }

    /**
     * Calls controller equipWeapon method.
     * @param character
     *       the player character.
     * @param weapon
     *       the weapon.
     */
    public void equipWeapon(IPlayerCharacter character, IWeapon weapon) { }

    /**
     * Calls controller attack method.
     * @param attacker
     *       the character that is attacking.
     * @param attacked
     *       the attacked character.
     */
    public void attack(ICharacter attacker, ICharacter attacked) { }

    /**
     * Calls controller extractCharacter method.
     */
    public void extractCharacter() { }

    /**
     * Calls controller newGame method.
     */
    public void newGame() { }

    /**
     * Changes the current phase to initial phase.
     * @throws InvalidTransitionException
     *        when can not change to a specific phase.
     */
    public void toInitialPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can not change from " + this.toString() + " to Initial phase");
    }

    /**
     * Changes the current phase to combat phase.
     * @throws InvalidTransitionException
     *        when can not change to a specific phase.
     */
    public void toCombatPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can not change from " + this.toString() + " to Combat phase");
    }

    /**
     * Changes the current phase to end turn phase.
     * @throws InvalidTransitionException
     *        when can not change to a specific phase.
     */
    public void toEndTurnPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can not change from " + this.toString() + " to End turn phase");
    }

    /**
     * Changes the current phase to final phase.
     * @throws InvalidTransitionException
     *        when can not change to a specific phase.
     */
    public void toFinalPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can not change from " + this.toString() + " to Final phase");
    }
}

