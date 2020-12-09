package com.github.estebanzuniga.finalreality.model.character.player.party;

import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds all the information of a white mage.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class WhiteMage extends AbstractMage{

    private int life;
    private final int defense;
    //protected int mana;

    /**
     * Creates a new white mage.
     * @param turnsQueue
     *        the queue with the characters waiting for their turn.
     * @param name
     *        the white mage's name.
     * @param life
     *        the white mage's life.
     * @param defense
     *        the white mage's defense.
     */
    public WhiteMage(@NotNull final BlockingQueue<ICharacter> turnsQueue,
                     @NotNull String name, int life, int defense){
        super(turnsQueue, name);
        this.life = life;
        this.defense = defense;
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
        return defense;
    }

    @Override
    public void equip(IWeapon weapon) {
        weapon.equippedByWhiteMage(this);
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
        return getName().equals(that.getName()) &&
                getLife() == (that.getLife()) &&
                getDefense() == that.getDefense();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLife(), getDefense());
    }

    /*public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }*/
}
