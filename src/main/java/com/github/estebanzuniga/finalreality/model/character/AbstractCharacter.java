package com.github.estebanzuniga.finalreality.model.character;

import com.github.estebanzuniga.finalreality.model.character.player.party.*;

import java.util.concurrent.*;

import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Esteban Zúñiga Salamanca.
 */
public abstract class AbstractCharacter implements ICharacter {

  protected BlockingQueue<ICharacter> turnsQueue = new LinkedBlockingQueue<>();
  protected final String name;
  protected ScheduledExecutorService scheduledExecutor;
  protected IWeapon equippedWeapon = null;

  protected AbstractCharacter(@NotNull String name) {
    this.name = name;
  }

  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    if (this instanceof Enemy) {
      var enemy = (Enemy) this;
      scheduledExecutor.schedule(this::addToQueue, enemy.getWeight() / 10, TimeUnit.SECONDS);
    } else {
      scheduledExecutor.schedule(this::addToQueue, equippedWeapon.getWeight() / 10, TimeUnit.SECONDS);
    }
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
  public void attackedByEnemy(Enemy enemy) {
    int damage = enemy.getAttack() - this.getDefense();
    this.setLife(this.getLife()-damage);
  }

  @Override
  public void attackedByEngineer(Engineer engineer) {
    int damage = engineer.getEquippedWeapon().getDamage() - this.getDefense();
    this.setLife(this.getLife()-damage);
  }

  @Override
  public void attackedByThief(Thief thief) {
    int damage = thief.getEquippedWeapon().getDamage() - this.getDefense();
    this.setLife(this.getLife()-damage);
  }

  @Override
  public void attackedByKnight(Knight knight) {
    int damage = knight.getEquippedWeapon().getDamage() - this.getDefense();
    this.setLife(this.getLife()-damage);
  }

  @Override
  public void attackedByWhiteMage(WhiteMage whiteMage) {
    int damage = whiteMage.getEquippedWeapon().getDamage() - this.getDefense();
    this.setLife(this.getLife()-damage);
  }

  @Override
  public void attackedByBlackMage(BlackMage blackMage) {
    int damage = blackMage.getEquippedWeapon().getDamage() - this.getDefense();
    this.setLife(this.getLife()-damage);
  }
}
