package com.github.estebanzuniga.finalreality.model.weapon;

import com.github.estebanzuniga.finalreality.model.character.player.party.*;

/**
 * This represents a weapon from the game.
 * A weapon is equipped by a character controlled by the player..
 *
 * @author Esteban Zúñiga Salamanca.
 */
public interface IWeapon {

    /**
     * Returns the weapon's damage.
     */
    int getDamage();

    /**
     * Returns the weapon's weight.
     * */
    int getWeight();

    /**
     * Represents the equipment of an engineer.
     * @param engineer
     *        the engineer who is equipping the weapon.
     */
    void equippedByEngineer(Engineer engineer);

    /**
     * Represents the equipment of a thief.
     * @param thief
     *        the thief who is equipping the weapon.
     */
    void equippedByThief(Thief thief);

    /**
     * Represents the equipment of a knight.
     * @param knight
     *        the knight who is equipping the weapon.
     */
    void equippedByKnight(Knight knight);

    /**
     * Represents the equipment of a white mage.
     * @param whiteMage
     *        the white mage who is equipping the weapon.
     */
    void equippedByWhiteMage(WhiteMage whiteMage);

    /**
     * Represents the equipment of a black mage.
     * @param blackMage
     *        the black mage who is equipping the weapon.
     */
    void equippedByBlackMage(BlackMage blackMage);
}
