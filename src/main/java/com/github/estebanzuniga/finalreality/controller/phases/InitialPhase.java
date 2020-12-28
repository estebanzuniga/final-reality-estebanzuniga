package com.github.estebanzuniga.finalreality.controller.phases;

public class InitialPhase extends Phase {

    public InitialPhase() {
        this.canSetEnemies = true;
        this.canEquip = false;
        this.canAttack = false;
        this.canExtractACharacter = false;
        this.canPlayAgain = false;
    }

    @Override
    public void setEnemies(){
        controller.setEnemies();
        toAttackPhase();
    }

    @Override
    public void toAttackPhase() {
        changePhase(new AttackPhase());
    }

    public String toString() {
        return "Initial phase";
    }
}
