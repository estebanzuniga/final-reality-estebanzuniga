package com.github.estebanzuniga.finalreality.model.weapon;

import com.github.estebanzuniga.finalreality.model.character.player.party.*;

/**
 * An abstract class that holds the common behaviour of all the weapons in the game.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public abstract class AbstractWeapon implements IWeapon{

    /**
     * Creates a new abstract weapon.
     */
    public AbstractWeapon() {
    }

    @Override
    public void equippedByEngineer(Engineer engineer) {
        engineer.setEquippedWeapon(this);
    }

    @Override
    public void equippedByThief(Thief thief) {
        thief.setEquippedWeapon(this);
    }

    @Override
    public void equippedByKnight(Knight knight) {
        knight.setEquippedWeapon(this);
    }

    @Override
    public void equippedByWhiteMage(WhiteMage whiteMage) {
        whiteMage.setEquippedWeapon(this);
    }

    @Override
    public void equippedByBlackMage(BlackMage blackMage) {
        blackMage.setEquippedWeapon(this);
    }
}
