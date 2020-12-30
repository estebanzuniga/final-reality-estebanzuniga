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
    public void attack(ICharacter attacker, ICharacter attacked) {
        controller.attack(attacker, attacked);
        toEndTurnPhase();
    }

    @Override
    public void toEndTurnPhase() {
        changePhase(new EndTurnPhase());
    }

    @Override
    public String toString() {
        return "Attack phase";
    }
}
