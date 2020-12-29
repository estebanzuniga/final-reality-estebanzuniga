package com.github.estebanzuniga.finalreality.controller.phases;

public class InitialPhase extends Phase {

    public InitialPhase() {
        this.inInitialPhase = true;
        this.canEquip = false;
        this.canAttack = false;
        this.canExtractACharacter = false;
        this.canPlayAgain = false;
    }

    @Override
    public void partyIsComplete() {
        controller.partyIsComplete();
    }

    public String toString() {
        return "Initial phase";
    }
}
