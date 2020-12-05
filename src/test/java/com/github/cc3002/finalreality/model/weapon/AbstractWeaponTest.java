package com.github.cc3002.finalreality.model.weapon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import com.github.estebanzuniga.finalreality.model.weapon.party.*;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractWeaponTest {

  protected static final int DAMAGE = 15;
  protected static final int WEIGHT = 10;

  protected String AXE_NAME = "TEST_AXE";
  protected String BOW_NAME = "TEST_BOW";
  protected String KNIFE_NAME = "TEST_KNIFE";
  protected String STAFF_NAME = "TEST_STAFF";
  protected String SWORD_NAME = "TEST_SWORD";
  protected String OTHER_NAME = "TEST_NAME";
  protected Axe testAxe;
  protected Bow testBow;
  protected Knife testKnife;
  protected Staff testStaff;
  protected Sword testSword;

  @BeforeEach
  protected void basicSetUp() {
    testAxe = new Axe(AXE_NAME, DAMAGE, WEIGHT);
    testBow = new Bow(BOW_NAME, DAMAGE, WEIGHT);
    testKnife = new Knife(KNIFE_NAME, DAMAGE, WEIGHT);
    testStaff = new Staff(STAFF_NAME, DAMAGE, WEIGHT);
    testSword = new Sword(SWORD_NAME, DAMAGE, WEIGHT);
  }

  protected void checkConstruction(IWeapon testWeapon, IWeapon expectedWeapon, IWeapon difNameExpectedWeapon,
                                   IWeapon difDamageExpectedWeapon, IWeapon difWeightExpectedWeapon,
                                   IWeapon other1, IWeapon other2, IWeapon other3, IWeapon other4) {

    assertEquals(testWeapon, testWeapon);
    assertEquals(expectedWeapon, testWeapon);
    assertEquals(expectedWeapon.hashCode(), testWeapon.hashCode());

    assertNotEquals(expectedWeapon, difNameExpectedWeapon);
    assertNotEquals(expectedWeapon, difDamageExpectedWeapon);
    assertNotEquals(expectedWeapon, difWeightExpectedWeapon);

    assertNotEquals(expectedWeapon, other1);
    assertNotEquals(expectedWeapon, other2);
    assertNotEquals(expectedWeapon, other3);
    assertNotEquals(expectedWeapon, other4);
  }
}