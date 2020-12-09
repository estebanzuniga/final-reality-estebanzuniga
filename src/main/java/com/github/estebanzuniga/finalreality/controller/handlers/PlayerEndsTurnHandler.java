package com.github.estebanzuniga.finalreality.controller.handlers;

import com.github.estebanzuniga.finalreality.controller.GameController;
import com.github.estebanzuniga.finalreality.model.character.player.IPlayerCharacter;

import java.beans.PropertyChangeEvent;

/**
 * A class that simulates an listener(observer) that notify to controller when a player ends its turn.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class PlayerEndsTurnHandler implements IEventHandler {

    private final GameController controller;

    /**
     * Creates a listener.
     * @param controller
     *        the controller that the listener is observing.
     */
    public PlayerEndsTurnHandler(GameController controller) {
        this.controller = controller;
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.playerAttack((IPlayerCharacter) evt.getNewValue(), (int) evt.getNewValue());
    }
}
