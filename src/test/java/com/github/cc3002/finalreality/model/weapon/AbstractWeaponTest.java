package com.github.cc3002.finalreality.model.weapon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import com.github.estebanzuniga.finalreality.model.weapon.party.*;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractWeaponTest {

  protected static final int DAMAGE = 15;
  protected static final int SPEED = 10;

  protected Axe testAxe;
  protected Bow testBow;
  protected Knife testKnife;
  protected Staff testStaff;
  protected Sword testSword;

  @BeforeEach
  protected void basicSetUp() {
    testAxe = new Axe(DAMAGE, SPEED);
    testStaff = new Staff(DAMAGE, SPEED);
    testSword = new Sword(DAMAGE, SPEED);
    testBow = new Bow(DAMAGE, SPEED);
    testKnife = new Knife(DAMAGE, SPEED);
  }

  protected void checkConstruction(IWeapon testWeapon, IWeapon expectedWeapon,
                                   IWeapon difDamageExpectedWeapon, IWeapon difSpeedExpectedWeapon,
                                   IWeapon other1, IWeapon other2, IWeapon other3, IWeapon other4) {

    assertEquals(testWeapon, testWeapon);
    assertEquals(expectedWeapon, testWeapon);
    assertEquals(expectedWeapon.hashCode(), testWeapon.hashCode());

    assertNotEquals(expectedWeapon, difDamageExpectedWeapon);
    assertNotEquals(expectedWeapon, difSpeedExpectedWeapon);

    assertNotEquals(expectedWeapon, other1);
    assertNotEquals(expectedWeapon, other2);
    assertNotEquals(expectedWeapon, other3);
    assertNotEquals(expectedWeapon, other4);
  }
}