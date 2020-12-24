package com.github.estebanzuniga.finalreality.controller.phases;

public class EndTurnPhase extends Phase {

    public EndTurnPhase() {
        this.canSetEnemies = false;
        this.canEquip = false;
        this.canAttack = false;
        this.canExtractACharacter = true;
    }

    @Override
    public void extractCharacter() {
        controller.extractCharacter();
        while (controller.getTurns().isEmpty()) {}
        toAttackPhase();
    }

    @Override
    public void toAttackPhase() {
        changePhase(new AttackPhase());
    }

    public String toString() {
        return "Main phase";
    }
}
