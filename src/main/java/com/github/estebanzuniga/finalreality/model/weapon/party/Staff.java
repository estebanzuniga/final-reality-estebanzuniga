package com.github.estebanzuniga.finalreality.model.weapon.party;

import com.github.estebanzuniga.finalreality.model.character.player.party.Engineer;
import com.github.estebanzuniga.finalreality.model.character.player.party.Knight;
import com.github.estebanzuniga.finalreality.model.weapon.AbstractWeapon;

import java.util.Objects;

/**
 * A class that holds all the information of a staff.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class Staff extends AbstractWeapon {

    private final int damage;
    private final int weight;

    /**
     * Creates a new staff.
     * @param damage
     *        the staff's damage.
     * @param weight
     *        the staff's weight.
     */
    public Staff(int damage, int weight){
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
    public void equippedByKnight(Knight knight) {
    }

    @Override
    public void equippedByEngineer(Engineer engineer) {
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Staff)) {
            return false;
        }
        final Staff weapon = (Staff) o;
        return getDamage() == weapon.getDamage() &&
                getWeight() == weapon.getWeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDamage(), getWeight());
    }
}
