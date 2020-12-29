package com.github.estebanzuniga.finalreality.model.character.player.party;

import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds all the information of a black mage.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class BlackMage extends AbstractMage {

    private int life;
    private final int defense;

    /**
     * Creates a new black mage.
     *
     * @param turnsQueue the queue with the characters waiting for their turn.
     * @param name       the black mage's name.
     * @param life       the black mage's life.
     * @param defense    the black mage's defense.
     */
    public BlackMage(@NotNull final BlockingQueue<ICharacter> turnsQueue,
                     @NotNull String name, int life, int defense) {
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
    public void equip(IWeapon weapon) {
        weapon.equippedByBlackMage(this);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlackMage)) {
            return false;
        }
        final BlackMage that = (BlackMage) o;
        return getName().equals(that.getName()) &&
                getLife() == (that.getLife()) &&
                getDefense() == that.getDefense();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLife(), getDefense());
    }
}
