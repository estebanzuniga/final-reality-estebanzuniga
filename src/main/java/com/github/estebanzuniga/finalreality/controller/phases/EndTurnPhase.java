package com.github.estebanzuniga.finalreality.controller.phases;

public class EndTurnPhase extends Phase {

    public EndTurnPhase() {
        this.inInitialPhase = false;
        this.canEquip = false;
        this.canAttack = false;
        this.canExtractACharacter = true;
        this.canPlayAgain = false;
    }

    @Override
    public void extractCharacter() {
        controller.extractCharacter();
        if (controller.enemyWon() || controller.playerWon()) {
            toFinalPhase();
        }
        else {
            while (controller.getTurns().isEmpty()) { }
            toAttackPhase();
        }
    }

    @Override
    public void toAttackPhase() {
        changePhase(new AttackPhase());
    }

    @Override
        public void toFinalPhase() {
        changePhase(new FinalPhase());
    }

    public String toString() {
        return "End turn phase";
    }
}
