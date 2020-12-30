package com.github.estebanzuniga.finalreality.controller.phases;

import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.character.player.IPlayerCharacter;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;

/**
 * A class that holds all the information of a CombatPhase.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class CombatPhase extends Phase {

    /**
     * Creates a new CombatPhase, it represents when the user and the enemies are facing.
     */
    public CombatPhase() {
        canChangeToInitialPhase = false;
        canChangeToCombatPhase = false;
        canChangeToEndTurnPhase = true;
        canChangeToFinalPhase = false;

        this.inInitialPhase = false;
        this.canAttack = true;
        this.canEquip = true;
        this.canExtractACharacter = false;
        this.canPlayAgain = false;
    }

    @Override
    public void equipWeapon(IPlayerCharacter character, IWeapon weapon) {
        controller.equipWeapon(character, weapon);
    }

    @Override
    public void attack(ICharacter attacker, ICharacter attacked) throws InvalidMovementException {
        super.attack(attacker, attacked);
        try {
            toEndTurnPhase();
        } catch (InvalidTransitionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toEndTurnPhase() throws InvalidTransitionException {
        //changePhase(new EndTurnPhase());
        super.toEndTurnPhase();
    }

    /**
     * Return the name of a phase.
     * @return
     *        the name of a phase.
     */
    public String toString() {
        return "Combat phase";
    }
}
