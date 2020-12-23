package com.github.estebanzuniga.finalreality.controller.phases;

public class SettingEnemiesPhase extends Phase {

    public SettingEnemiesPhase() {
        this.canSetEnemies = true;
        this.canAttack = false;
        this.canExtractACharacter = false;
        this.canEquip = false;
    }

    @Override
    public void setEnemies(){
        controller.setEnemies();
        toMainPhase();
    }

    @Override
    public void toMainPhase() {
        changePhase(new MainPhase());
    }

    public String toString() {
        return "Setting enemies phase";
    }
}
