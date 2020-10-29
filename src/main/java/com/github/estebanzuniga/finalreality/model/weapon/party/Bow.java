package com.github.estebanzuniga.finalreality.model.weapon.party;

import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;

import java.util.Objects;

/**
 * A class that holds all the information of a bow.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class Bow implements IWeapon {

    private final int damage;
    private final int weight;

    /**
     * Creates a new bow.
     */
    public Bow(int damage, int weight){
        this.damage = damage;
        this.weight = weight;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bow)) {
            return false;
        }
        final Bow weapon = (Bow) o;
        return getDamage() == weapon.getDamage() &&
                getWeight() == weapon.getWeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDamage(), getWeight());
    }
}
