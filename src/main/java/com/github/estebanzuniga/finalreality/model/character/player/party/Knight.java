package com.github.estebanzuniga.finalreality.model.character.player.party;

import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.character.player.AbstractPlayerCharacter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds all the information of a knight.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class Knight extends AbstractPlayerCharacter {

    private int life = 500;

    /**
     * Creates a new knight.
     *
     * @param name
     *     the knight's name.
     */
    public Knight(@NotNull final BlockingQueue<ICharacter> turnsQueue,
                  @NotNull String name){
        super(turnsQueue, name);
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
        return 100;
    }

    @Override
    public void attack(ICharacter character) {
        if (character.isAlive()) {
            character.attackedByKnight(this);
        }
        if (character.getLife() <= 0) {
            character.setLife(0);
        }
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
