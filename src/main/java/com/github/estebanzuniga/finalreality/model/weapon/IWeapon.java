package com.github.estebanzuniga.finalreality.model.weapon;

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
     * Returns the weapon's type.
     */
    WeaponType getType();
}
