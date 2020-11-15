package com.github.estebanzuniga.finalreality.model.weapon.party;

import com.github.estebanzuniga.finalreality.model.character.player.party.BlackMage;
import com.github.estebanzuniga.finalreality.model.character.player.party.Thief;
import com.github.estebanzuniga.finalreality.model.character.player.party.WhiteMage;
import com.github.estebanzuniga.finalreality.model.weapon.AbstractWeapon;

import java.util.Objects;

/**
 * A class that holds all the information of an axe.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class Axe extends AbstractWeapon {

    private final int damage;
    private final int weight;

    /**
     * Creates a new axe.
     * @param damage
     *        the axe's damage.
     * @param weight
     *        the axe's weight.
     */
    public Axe(int damage, int weight){
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
    public void equippedByThief(Thief thief) {
    }

    @Override
    public void equippedByWhiteMage(WhiteMage whiteMage) {
    }

    @Override
    public void equippedByBlackMage(BlackMage blackMage) {
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Axe)) {
            return false;
        }
        final Axe weapon = (Axe) o;
        return getDamage() == weapon.getDamage() &&
                getWeight() == weapon.getWeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDamage(), getWeight());
    }
}