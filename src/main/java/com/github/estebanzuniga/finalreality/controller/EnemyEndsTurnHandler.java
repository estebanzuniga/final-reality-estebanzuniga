package com.github.estebanzuniga.finalreality.controller;

import com.github.estebanzuniga.finalreality.model.character.ICharacter;

import java.beans.PropertyChangeEvent;

public class EnemyEndsTurnHandler implements IEventHandler {

    private final GameController controller;

    public EnemyEndsTurnHandler(GameController controller) {
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
        controller.onCharacterPlayed((ICharacter) evt.getNewValue());
    }
}
