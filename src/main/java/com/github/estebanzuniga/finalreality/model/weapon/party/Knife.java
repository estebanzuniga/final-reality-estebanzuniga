package com.github.estebanzuniga.finalreality.model.weapon.party;

import com.github.estebanzuniga.finalreality.model.weapon.AbstractWeapon;
import com.github.estebanzuniga.finalreality.model.weapon.WeaponType;

/**
 * A class that holds all the information of a knife.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class Knife extends AbstractWeapon {

    private final int damage = 150;
    private final int weight = 15;

    /**
     * Creates a new knife.
     * @param damage
     *        The weapon's damage.
     * @param weight
     *        The weapon's weight.
     */
    public Knife(final int damage, final int weight) {
        super(damage, weight, WeaponType.STAFF);
    }
}
