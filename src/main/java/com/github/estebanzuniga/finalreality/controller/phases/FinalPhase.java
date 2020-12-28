package com.github.estebanzuniga.finalreality.controller.phases;

public class FinalPhase extends Phase {

    public FinalPhase() {
        this.canSetEnemies = false;
        this.canAttack = false;
        this.canExtractACharacter = true;
        this.canEquip = false;
        this.canPlayAgain = true;
    }

    @Override
    public void newGame() {
        controller.newGame();
        toInitialPhase();
    }

    @Override
    public void toInitialPhase() {
        changePhase(new InitialPhase());
    }

    public String toString() {
        return "Final phase";
    }

}
