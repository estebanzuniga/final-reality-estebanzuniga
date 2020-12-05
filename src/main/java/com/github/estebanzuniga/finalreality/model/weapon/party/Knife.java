package com.github.estebanzuniga.finalreality.model.weapon.party;

import com.github.estebanzuniga.finalreality.model.character.player.party.Engineer;
import com.github.estebanzuniga.finalreality.model.character.player.party.Thief;
import com.github.estebanzuniga.finalreality.model.character.player.party.WhiteMage;
import com.github.estebanzuniga.finalreality.model.weapon.AbstractWeapon;

import java.util.Objects;

/**
 * A class that holds all the information of a knife.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class Knife extends AbstractWeapon {

    private final String name;
    private final int damage;
    private final int weight;

    /**
     * Creates a new knife.
     * @param name
     *        the knife's name.
     * @param damage
     *        the knife's damage.
     * @param weight
     *        the knife's weight.
     */
    public Knife(String name, int damage, int weight){
        this.name = name;
        this.damage = damage;
        this.weight = weight;
    }

    @Override
    public String getName() {
        return name;
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
    public void equippedByEngineer(Engineer engineer) {}

    @Override
    public void equippedByThief(Thief thief) {}

    @Override
    public void equippedByWhiteMage(WhiteMage whiteMage) {}

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Knife)) {
            return false;
        }
        final Knife weapon = (Knife) o;
        return getName().equals(weapon.getName()) &&
                getDamage() == weapon.getDamage() &&
                getWeight() == weapon.getWeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDamage(), getWeight());
    }
}
