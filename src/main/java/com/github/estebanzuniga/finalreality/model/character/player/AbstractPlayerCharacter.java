package com.github.estebanzuniga.finalreality.model.character.player;

import com.github.estebanzuniga.finalreality.controller.handlers.IEventHandler;
import com.github.estebanzuniga.finalreality.model.character.AbstractCharacter;

import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeSupport;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * An abstract class that holds information of a single character controlled by the player.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter implements IPlayerCharacter {

  protected IWeapon equippedWeapon = null;
  private final PropertyChangeSupport playerEndsTurnNotification = new PropertyChangeSupport(this);

  /**
   * Creates a new player character.
   *
   * @param name
   *     the character's name
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   */
  public AbstractPlayerCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                                 @NotNull String name) {
    super(turnsQueue, name);
  }

  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor.schedule(this::addToQueue, equippedWeapon.getWeight() / 10, TimeUnit.SECONDS);
  }

  @Override
  public void attack(ICharacter character) {
    int damage = this.getEquippedWeapon().getDamage() - character.getDefense();
    character.setLife(character.getLife()-damage);
    if (character.getLife() <= 0) {
      character.setLife(0);
    }
    playerEndsTurnNotification.firePropertyChange("PLAYER_ENDS_TURN", null, this);
  }

  @Override
  public IWeapon getEquippedWeapon() {
    return equippedWeapon;
  }

  @Override
  public void setEquippedWeapon(IWeapon weapon) {
    this.equippedWeapon = weapon;
  }

  @Override
  public void setEquippedWeaponNull() {
    this.equippedWeapon = null;
  }

  @Override
  public boolean isPlayer(ICharacter character) {
    return true;
  }

  @Override
  public void addPlayerEndsTurnListener(IEventHandler playerEndsTurnHandler) {
    playerEndsTurnNotification.addPropertyChangeListener(playerEndsTurnHandler);
  }
}
