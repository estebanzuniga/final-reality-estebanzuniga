package com.github.estebanzuniga.finalreality.controller.phases;

import com.github.estebanzuniga.finalreality.model.character.ICharacter;

public class MainPhase extends Phase {

    public MainPhase() {
        this.canSetEnemies = false;
        this.canAttack = false;
        this.canExtractACharacter = true;
        this.canEquip = false;
    }

    @Override
    public ICharacter extractCharacter() {
        return controller.extractCharacter();
    }

    @Override
    public void toSelectingWeaponPhase() {
        changePhase(new SelectingWeaponPhase());
    }

    @Override
    public void toEnemyAttackingPhase() {
        changePhase(new EnemyAttackingPhase());
    }

    public String toString() {
        return "Main phase";
    }
}
