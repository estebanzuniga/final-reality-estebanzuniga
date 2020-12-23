package com.github.estebanzuniga.finalreality.model.character;

import com.github.estebanzuniga.finalreality.controller.handlers.IEventHandler;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author Esteban Zúñiga Salamanca.
 */
public interface ICharacter {

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   */
  void waitTurn();

  /**
   * Returns this character's name.
   */
  String getName();

  /**
   * Returns the character's life.
   */
  int getLife();

  /**
   * Set a character's life after being attacked.
   * @param life
   *        The character's current life.
   */
  void setLife(int life);

  /**
   * Returns the character's defense.
   */
  int getDefense();

  /**
   * Represents the character's attack.
   * @param character
   *        The attacked character.
   */
  void attack(ICharacter character);

  /**
   * Checks if a character is alive or not.
   * @return
   *        true if the character is alive, false if is dead.
   */
  boolean isAlive();

  /**
   * Returns true if the character is a player character.
   * @return
   *        true if the character is a player character.
   */
  boolean isPlayer();

  /**
   * Returns the return value of isPlayer method.
   * @return
   *        true if the character is a player character.
   */
  boolean isPlayerCharacter();

  /**
   * Add a character to a handler.
   * @param characterIsDeadHandler
   *        the handler.
   */
  void addCharacterIsDeadListener(IEventHandler characterIsDeadHandler);
}
