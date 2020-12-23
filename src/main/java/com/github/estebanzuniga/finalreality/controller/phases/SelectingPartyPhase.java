package com.github.estebanzuniga.finalreality.controller.phases;

public class SelectingPartyPhase extends Phase {

    public SelectingPartyPhase() {
        this.canAttack = false;
        this.canEquip = false;
        this.canExtractACharacter = false;
        this.canSetEnemies = false;
    }

    public String toString() {
        return "Selecting party phase";
    }
}
