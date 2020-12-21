package com.github.estebanzuniga.finalreality.controller.phases;

import com.github.estebanzuniga.finalreality.model.character.player.IPlayerCharacter;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;

public class SelectingWeaponPhase extends Phase {

    public SelectingWeaponPhase() {
        this.canSetEnemies = false;
        this.canAttack = false;
        this.canExtractACharacter = false;
        this.canEquip = true;
    }

    @Override
    public void equipWeapon(IPlayerCharacter character, IWeapon weapon) {
        controller.equipWeapon(character, weapon);
        toSelectingAttackTargetPhase();
    }

    @Override
    public void toSelectingAttackTargetPhase() {
        changePhase(new SelectingAttackTargetPhase());
    }

    public String toString() {
        return "Selecting weapon phase";
    }
}
