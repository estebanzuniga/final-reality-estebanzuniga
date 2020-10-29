package com.github.cc3002.finalreality.model.weapon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.github.estebanzuniga.finalreality.model.weapon.AbstractWeapon;
import com.github.estebanzuniga.finalreality.model.weapon.party.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeaponTest {

  private static final int DAMAGE = 15;
  private static final int SPEED = 10;

  private AbstractWeapon testAxe;
  private AbstractWeapon testStaff;
  private AbstractWeapon testSword;
  private AbstractWeapon testBow;
  private AbstractWeapon testKnife;

  @BeforeEach
  void setUp() {
    testAxe = new Axe(DAMAGE, SPEED);
    testStaff = new Staff(DAMAGE, SPEED);
    testSword = new Sword(DAMAGE, SPEED);
    testBow = new Bow(DAMAGE, SPEED);
    testKnife = new Knife(DAMAGE, SPEED);
  }

  @Test
  void constructorTest() {
    var expectedAxe = new Axe(DAMAGE, SPEED);
    var expectedStaff = new Staff(DAMAGE, SPEED);
    var expectedSword = new Sword(DAMAGE, SPEED);
    var expectedBow = new Bow(DAMAGE, SPEED);
    var expectedKnife = new Knife(DAMAGE, SPEED);

    var difDamageExpectedAxe = new Axe(DAMAGE+1, SPEED);
    var difSpeedExpectedAxe = new Axe(DAMAGE, SPEED+1);

    assertEquals(expectedAxe, testAxe);
    assertEquals(expectedAxe.hashCode(), testAxe.hashCode());
    assertEquals(expectedStaff, testStaff);
    assertEquals(expectedStaff.hashCode(), testStaff.hashCode());
    assertEquals(expectedSword, testSword);
    assertEquals(expectedSword.hashCode(), testSword.hashCode());
    assertEquals(expectedBow, testBow);
    assertEquals(expectedBow.hashCode(), testBow.hashCode());
    assertEquals(expectedKnife, testKnife);
    assertEquals(expectedKnife.hashCode(), testKnife.hashCode());

    assertNotEquals(expectedAxe, difDamageExpectedAxe);
    assertNotEquals(expectedAxe, difSpeedExpectedAxe);
  }
}