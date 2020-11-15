package com.github.estebanzuniga.finalreality.model.character.player.party;

import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds all the information of a knight.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class Knight extends AbstractPlayerCharacter {

    private int life;
    private final int defense;

    /**
     * Creates a new knight.
     * @param turnsQueue
     *        the queue with the characters waiting for their turn.
     * @param name
     *        the knight's name.
     * @param life
     *        the knight's life.
     * @param defense
     *        the knight's defense.
     */
    public Knight(@NotNull final BlockingQueue<ICharacter> turnsQueue,
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
            character.attackedByKnight(this);
            if (character.getLife() <= 0) {
                character.setLife(0);
            }
        }
    }

    @Override
    public void equip(IWeapon weapon) {
        weapon.equippedByKnight(this);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Knight)) {
            return false;
        }
        final Knight that = (Knight) o;
        return getName().equals(that.getName())
                && getDefense() == that.getDefense();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDefense());
    }
}
