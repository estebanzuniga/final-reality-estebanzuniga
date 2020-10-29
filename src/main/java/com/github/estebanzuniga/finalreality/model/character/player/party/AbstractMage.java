package com.github.estebanzuniga.finalreality.model.character.player.party;

import com.github.estebanzuniga.finalreality.model.character.player.AbstractPlayerCharacter;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of both mages of the game.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public abstract class AbstractMage extends AbstractPlayerCharacter {

    protected int mana;

    public AbstractMage(@NotNull String name) {
        super(name);
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
