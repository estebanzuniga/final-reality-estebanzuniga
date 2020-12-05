package com.github.estebanzuniga.finalreality.model.character.player;

import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;

/**
 * This represents a character that can be controlled by the player.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public interface IPlayerCharacter extends ICharacter {

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

    /**
     * Sets equippedWeapon as weapon
     *
     * @param weapon
     *        The weapon that will be equipped to the character
     */
    void setEquippedWeapon(IWeapon weapon);

    /**
     * Sets equippedWeapon as null
     */
    void setEquippedWeaponNull();
}
