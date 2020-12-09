package com.github.estebanzuniga.finalreality.controller.handlers;

import com.github.estebanzuniga.finalreality.controller.GameController;
import com.github.estebanzuniga.finalreality.model.character.ICharacter;

import java.beans.PropertyChangeEvent;

/**
 * A class that simulates an listener(observer) that notify to controller when a character is dead.
 *
 * @author Esteban Zúñiga Salamanca
 */
public class CharacterIsDeadHandler implements IEventHandler {

    private final GameController controller;

    /**
     * Creates a listener.
     * @param controller
     *        the controller that the listener is observing.
     */
    public CharacterIsDeadHandler(GameController controller) {
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
        controller.isDead((ICharacter) evt.getNewValue());
    }
}
