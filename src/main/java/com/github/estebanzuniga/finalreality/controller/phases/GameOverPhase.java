package com.github.estebanzuniga.finalreality.controller.phases;

public class GameOverPhase extends Phase {

    public GameOverPhase() {
        this.canSetEnemies = false;
        this.canAttack = false;
        this.canExtractACharacter = true;
        this.canEquip = false;
    }

    @Override
    public void toInitialPhase() {
        changePhase(new InitialPhase());
    }

    public String toString() {
        return "Game Over phase";
    }

}
