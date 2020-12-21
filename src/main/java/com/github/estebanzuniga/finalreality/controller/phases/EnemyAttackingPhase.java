package com.github.estebanzuniga.finalreality.controller.phases;

import com.github.estebanzuniga.finalreality.model.character.ICharacter;

public class EnemyAttackingPhase extends Phase {

    public EnemyAttackingPhase() {
        this.canSetEnemies = false;
        this.canAttack = true;
        this.canExtractACharacter = false;
        this.canEquip = false;
    }

    @Override
    public void attack(ICharacter attacker, ICharacter attacked) {
        attacker.attack(attacked);
        if (controller.isDead(attacked)) {
            controller.getParty().remove(attacked);
        }
        toMainPhase();
    }

    @Override
    public void toMainPhase() {
        changePhase(new MainPhase());
    }

    public String toString() {
        return "Enemy attacking phase";
    }
}
