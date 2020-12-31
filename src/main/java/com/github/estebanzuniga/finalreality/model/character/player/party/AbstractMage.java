package com.github.estebanzuniga.finalreality.model.character.player.party;

import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.character.player.AbstractPlayerCharacter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * An abstract class that holds the common behaviour of both mages of the game.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public abstract class AbstractMage extends AbstractPlayerCharacter {

    /**
     * Creates a new mage.
     *
     * @param name
     *     the mage's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */
    public AbstractMage(@NotNull final BlockingQueue<ICharacter> turnsQueue,
                        @NotNull String name){
        super(turnsQueue, name);
    }
}
