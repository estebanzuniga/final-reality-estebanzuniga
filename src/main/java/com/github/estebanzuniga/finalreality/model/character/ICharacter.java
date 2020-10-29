package com.github.estebanzuniga.finalreality.model.character;

import com.github.estebanzuniga.finalreality.model.character.player.party.*;

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
   * Represents the attack of an enemy.
   * @param enemy
   *        The attacking enemy.
   */
  void attackedByEnemy(Enemy enemy);

  /**
   * Represents the attack of an engineer.
   * @param engineer
   *        The attacking engineer.
   */
  void attackedByEngineer(Engineer engineer);

  /**
   * Represents the attack of a thief.
   * @param thief
   *        The attacking thief.
   */
  void attackedByThief(Thief thief);

  /**
   * Represents the attack of a knight.
   * @param knight
   *        The attacking knight.
   */
  void attackedByKnight(Knight knight);

  /**
   * Represents the attack of a white mage.
   * @param whiteMage
   *        The attacking white mage.
   */
  void attackedByWhiteMage(WhiteMage whiteMage);

  /**
   * Represents the attack of a black mage.
   * @param blackMage
   *        The attacking black mage.
   */
  void attackedByBlackMage(BlackMage blackMage);
}
