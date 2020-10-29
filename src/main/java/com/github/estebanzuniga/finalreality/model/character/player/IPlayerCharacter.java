package com.github.estebanzuniga.finalreality.model.character.player;

import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;

public interface IPlayerCharacter {

    /**
     * Equip a weapon to a PlayerCharacter.
     *
     * @param weapon
     *     the weapon that will be equipped
     */
    void equip(IWeapon weapon);

    /**
     * Returns this character's equipped weapon.
     */
    IWeapon getEquippedWeapon();
}
