package com.github.estebanzuniga.finalreality.model.weapon;

import java.util.Objects;

/**
 * An abstract class that holds the common behaviour of all the weapons in the game.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public abstract class AbstractWeapon implements IWeapon{

  private final int damage;
  private final int weight;
  private final WeaponType type;

  public AbstractWeapon(final int damage, final int weight, final WeaponType type) {
    this.damage = damage;
    this.weight = weight;
    this.type = type;
  }

  @Override
  public int getDamage() {
    return damage;
  }

  @Override
  public int getWeight() {
    return weight;
  }

  @Override
  public WeaponType getType() {
    return type;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AbstractWeapon)) {
      return false;
    }
    final AbstractWeapon weapon = (AbstractWeapon) o;
    return getDamage() == weapon.getDamage() &&
        getWeight() == weapon.getWeight() &&
        getType() == weapon.getType();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getDamage(), getWeight(), getType());
  }
}
