package com.github.estebanzuniga.finalreality.model.character.player;

import com.github.estebanzuniga.finalreality.model.character.AbstractCharacter;

import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds information of a single character controlled by the player.
 *
 * @author Ignacio Slater Muñoz.
 * @author Esteban Zúñiga Salamanca.
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter implements IPlayerCharacter {

  public AbstractPlayerCharacter(@NotNull String name) {
    super(name);
  }

  @Override
  public void equip(IWeapon weapon) {
    this.equippedWeapon = weapon;
  }

  @Override
  public IWeapon getEquippedWeapon() {
    return equippedWeapon;
  }
}
