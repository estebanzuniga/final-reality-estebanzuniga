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

    public AbstractMage(@NotNull final BlockingQueue<ICharacter> turnsQueue,
                        @NotNull String name){
        super(turnsQueue, name);
    }

    //Se escribieron algunas líneas en las clases hijas para cuando se implementen las magias
    //Se creó la variable mana y los métodos getMana y setMana

    //Próximamente se crearán métodos en esta clase.

}
