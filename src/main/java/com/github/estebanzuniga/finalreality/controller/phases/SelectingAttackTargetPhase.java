package com.github.estebanzuniga.finalreality.controller.phases;

import com.github.estebanzuniga.finalreality.model.character.ICharacter;

public class SelectingAttackTargetPhase extends Phase {

    public SelectingAttackTargetPhase() {
        this.canSetEnemies = false;
        this.canAttack = true;
        this.canExtractACharacter = false;
        this.canEquip = false;
    }

    @Override
    public void attack(ICharacter attacker, ICharacter attacked) {
        attacker.attack(attacked);
        if (controller.isDead(attacked)) {
            controller.getEnemies().remove(attacked);
        }
        toMainPhase();
    }

    @Override
    public void toMainPhase() {
        changePhase(new MainPhase());
    }

    public String toString() {
        return "Selecting attack target phase";
    }
}
