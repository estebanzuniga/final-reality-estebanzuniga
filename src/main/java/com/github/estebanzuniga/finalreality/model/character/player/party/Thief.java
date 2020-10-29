package com.github.estebanzuniga.finalreality.model.character.player.party;

import com.github.estebanzuniga.finalreality.model.character.CharacterClass;
import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.character.player.AbstractPlayerCharacter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds all the information of a thief.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class Thief extends AbstractPlayerCharacter {

    private int life = 500;
    private final int defense = 100;

    /**
     * Creates a new thief.
     *
     * @param name
     *     the thief's name.
     */
    public Thief(@NotNull String name, @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, CharacterClass.THIEF);
        this.turnsQueue = turnsQueue;
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
        character.attackedByThief(this);
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
        return Objects.hash(getCharacterClass());
    }
}
