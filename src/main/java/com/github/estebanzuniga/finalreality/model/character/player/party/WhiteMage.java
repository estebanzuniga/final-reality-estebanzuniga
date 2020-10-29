package com.github.estebanzuniga.finalreality.model.character.player.party;

import com.github.estebanzuniga.finalreality.model.character.CharacterClass;
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
    private final int defense = 100;

    /**
     * Creates a new white mage.
     *
     * @param name
     *     the white mage's name.
     */
    public WhiteMage(@NotNull String name, @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, CharacterClass.WHITE_MAGE);
        this.turnsQueue = turnsQueue;
        mana = 200;
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
        character.attackedByWhiteMage(this);
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
        return Objects.hash(getCharacterClass());
    }

}
