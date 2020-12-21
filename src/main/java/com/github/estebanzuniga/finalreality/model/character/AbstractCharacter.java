package com.github.estebanzuniga.finalreality.model.character;

import java.beans.PropertyChangeSupport;
import java.util.concurrent.*;

import com.github.estebanzuniga.finalreality.controller.handlers.IEventHandler;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Esteban Zúñiga Salamanca.
 */
public abstract class AbstractCharacter implements ICharacter {

  protected BlockingQueue<ICharacter> turnsQueue;
  protected final String name;
  protected ScheduledExecutorService scheduledExecutor;
  private final PropertyChangeSupport characterIsDeadNotification = new PropertyChangeSupport(this);


  /**
   * Creates a new abstract character.
   *
   * @param name
   *     the character's name
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   */
  protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                              @NotNull String name) {
    this.turnsQueue = turnsQueue;
    this.name = name;
  }

  /**
   * Adds this character to the turns queue.
   */
  public void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean isAlive() {
    if (this.getLife() <= 0) {
      characterIsDeadNotification.firePropertyChange("CHARACTER_IS_DEAD", null, this);
      return false;
    }
    return true;
  }

  public boolean isPlayerCharacter(ICharacter character) {
    return isPlayer(character);
  }

  @Override
  public void addCharacterIsDeadListener(IEventHandler characterIsDeadHandler) {
    characterIsDeadNotification.addPropertyChangeListener(characterIsDeadHandler);
  }
}