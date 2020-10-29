package com.github.estebanzuniga.finalreality.model.weapon.party;

import com.github.estebanzuniga.finalreality.model.weapon.AbstractWeapon;
import com.github.estebanzuniga.finalreality.model.weapon.WeaponType;

/**
 * A class that holds all the information of a sword.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class Sword extends AbstractWeapon {

    private final int damage = 150;
    private final int weight = 15;

    /**
     * Creates a new sword.
     * @param damage
     *        The weapon's damage.
     * @param weight
     *        The weapon's weight.
     */
    public Sword(final int damage, final int weight) {
        super(damage, weight, WeaponType.STAFF);
    }
}
