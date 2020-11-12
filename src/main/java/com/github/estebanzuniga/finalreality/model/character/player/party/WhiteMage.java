package com.github.estebanzuniga.finalreality.model.character.player.party;

import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds all the information of a white mage.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class WhiteMage extends AbstractMage{

    private int life = 500;
    //protected int mana;

    /**
     * Creates a new white mage.
     *
     * @param name
     *     the white mage's name.
     */
    public WhiteMage(@NotNull final BlockingQueue<ICharacter> turnsQueue,
                     @NotNull String name){
        super(turnsQueue, name);
        //this.mana = mana;
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
            character.attackedByWhiteMage(this);
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
        if (!(o instanceof WhiteMage)) {
            return false;
        }
        final WhiteMage that = (WhiteMage) o;
        return getName().equals(that.getName())
                && getDefense() == that.getDefense();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDefense());
    }

    /*public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }*/
}
