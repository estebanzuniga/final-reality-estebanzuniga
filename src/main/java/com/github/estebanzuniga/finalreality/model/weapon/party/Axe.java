package com.github.estebanzuniga.finalreality.model.weapon.party;

import com.github.estebanzuniga.finalreality.model.weapon.AbstractWeapon;
import com.github.estebanzuniga.finalreality.model.weapon.WeaponType;

/**
 * A class that holds all the information of an axe.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class Axe extends AbstractWeapon {

    private final int damage = 150;
    private final int weight = 15;

    /**
     * Creates a new axe.
     * @param damage
     *        The weapon's damage.
     * @param weight
     *        The weapon's weight.
     */
    public Axe(final int damage, final int weight) {
        super(damage, weight, WeaponType.STAFF);
    }
}