package com.github.estebanzuniga.finalreality.model.character.player.party;

import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds all the information of a thief.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class Thief extends AbstractPlayerCharacter {

    private int life;
    private final int defense;

    /**
     * Creates a new thief.
     * @param turnsQueue
     *        the queue with the characters waiting for their turn.
     * @param name
     *        the thief's name.
     * @param life
     *        the thief's life.
     * @param defense
     *        the thief's defense.
     */
    public Thief(@NotNull final BlockingQueue<ICharacter> turnsQueue,
                 @NotNull String name, int life, int defense){
        super(turnsQueue, name);
        this.life = life;
        this.defense = defense;
    }

    @Override
    public int getLife() {
        return life;
    }

    @Override
    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public void attack(ICharacter character) {
        if (character.isAlive()) {
            character.attackedByThief(this);
            if (character.getLife() <= 0) {
                character.setLife(0);
            }
        }
    }

    @Override
    public void equip(IWeapon weapon) {
        weapon.equippedByThief(this);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Thief)) {
            return false;
        }
        final Thief that = (Thief) o;
        return getName().equals(that.getName())
                && getDefense() == that.getDefense();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDefense());
    }
}
