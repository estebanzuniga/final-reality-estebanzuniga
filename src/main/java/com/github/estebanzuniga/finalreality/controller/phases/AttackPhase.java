package com.github.estebanzuniga.finalreality.controller.phases;

import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.character.player.IPlayerCharacter;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;

public class AttackPhase extends Phase {

    public AttackPhase() {
        this.canSetEnemies = false;
        this.canAttack = true;
        this.canEquip = true;
        this.canExtractACharacter = false;
    }

    @Override
    public void equipWeapon(IPlayerCharacter character, IWeapon weapon) {
        controller.equipWeapon(character, weapon);
    }

    @Override
    public void attack(ICharacter attacker, ICharacter attacked) {
        attacker.attack(attacked);
        if (controller.isPlayer(attacker) && controller.isDead(attacked)) {
            controller.getEnemies().remove(attacked);
        }
        else {
            controller.getParty().remove(attacked);
        }
        toEndTurnPhase();
    }

    @Override
    public void toEndTurnPhase() {
        changePhase(new EndTurnPhase());
    }

    public String toString() {
        return "Attack phase";
    }
}
