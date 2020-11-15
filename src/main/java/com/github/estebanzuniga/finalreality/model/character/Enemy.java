package com.github.estebanzuniga.finalreality.model.character;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Esteban Zúñiga Salamanca.
 */
public class Enemy extends AbstractCharacter {

  private int life;
  private final int defense;
  private final int attack;
  private final int weight;

  /**
   * Creates a new enemy with a name, a weight and the queue with the characters ready to play.
   * @param turnsQueue
   *        the queue with the characters waiting for their turn.
   * @param name
   *        the enemy's name.
   * @param weight
   *        the enemy's weight.
   * @param life
   *        the enemy's life.
   * @param attack
   *        the enemy's attack.
   * @param defense
   *        the enemy's defense.
   */
  public Enemy(@NotNull BlockingQueue<ICharacter> turnsQueue,
               @NotNull final String name, final int weight, int life, int attack, int defense) {
    super(turnsQueue, name);
    this.weight = weight;
    this.life = life;
    this.attack = attack;
    this.defense = defense;
  }

  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor.schedule(this::addToQueue, this.getWeight() / 10, TimeUnit.SECONDS);
  }

  @Override
  public int getLife() {
    return life;
  }

  @Override
  public void setLife(int life) {
    this.life = life;
  }

  @Override
  public int getDefense() {
    return defense;
  }

  /**
   * Returns the attack of this enemy.
   */
  public int getAttack() {
    return attack;
  }

  /**
   * Returns the weight of this enemy.
   */
  public int getWeight() {
    return weight;
  }

  @Override
  public void attack(ICharacter character) {
    if (character.isAlive()) {
      character.attackedByEnemy(this);
      if (character.getLife() <= 0) {
        character.setLife(0);
      }
    }
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Enemy)) {
      return false;
    }
    final Enemy enemy = (Enemy) o;
    return getWeight() == enemy.getWeight() &&
            getName().equals(enemy.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getWeight());
  }
}
