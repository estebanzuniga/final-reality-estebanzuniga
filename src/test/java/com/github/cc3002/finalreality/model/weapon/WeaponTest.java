package com.github.cc3002.finalreality.model.weapon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.character.player.CharacterClass;
import com.github.estebanzuniga.finalreality.model.character.player.PlayerCharacter;
import com.github.estebanzuniga.finalreality.model.weapon.Weapon;
import com.github.estebanzuniga.finalreality.model.weapon.WeaponType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class WeaponTest {

  private static final String AXE_NAME = "Test Axe";
  private static final String STAFF_NAME = "Test Staff";
  private static final String SWORD_NAME = "Test Sword";
  private static final String BOW_NAME = "Test Bow";
  private static final String KNIFE_NAME = "Test Knife";
  private static final int DAMAGE = 15;
  private static final int SPEED = 10;

  private Weapon testAxe;
  private Weapon testStaff;
  private Weapon testSword;
  private Weapon testBow;
  private Weapon testKnife;

  protected BlockingQueue<ICharacter> turns = new LinkedBlockingQueue<>();

  @BeforeEach
  void setUp() {
    testAxe = new Weapon(AXE_NAME, DAMAGE, SPEED, WeaponType.AXE);
    testStaff = new Weapon(STAFF_NAME, DAMAGE, SPEED, WeaponType.STAFF);
    testSword = new Weapon(SWORD_NAME, DAMAGE, SPEED, WeaponType.SWORD);
    testBow = new Weapon(BOW_NAME, DAMAGE, SPEED, WeaponType.BOW);
    testKnife = new Weapon(KNIFE_NAME, DAMAGE, SPEED, WeaponType.KNIFE);
  }

  @Test
  void constructorTest() {
    var expectedAxe = new Weapon(AXE_NAME, DAMAGE, SPEED, WeaponType.AXE);
    var expectedStaff = new Weapon(STAFF_NAME, DAMAGE, SPEED, WeaponType.STAFF);
    var expectedSword = new Weapon(SWORD_NAME, DAMAGE, SPEED, WeaponType.SWORD);
    var expectedBow = new Weapon(BOW_NAME, DAMAGE, SPEED, WeaponType.BOW);
    var expectedKnife = new Weapon(KNIFE_NAME, DAMAGE, SPEED, WeaponType.KNIFE);

    var expectedCharacter = new PlayerCharacter("Test", turns, CharacterClass.KNIGHT);
    var difNameExpectedAxe = new Weapon("Not Test Axe", DAMAGE, SPEED, WeaponType.AXE);
    var difDamageExpectedAxe = new Weapon(AXE_NAME, DAMAGE+1, SPEED, WeaponType.AXE);
    var difSpeedExpectedAxe = new Weapon(AXE_NAME, DAMAGE, SPEED+1, WeaponType.AXE);
    var difTypeExpectedWeapon = new Weapon(AXE_NAME, DAMAGE, SPEED, WeaponType.SWORD);

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

    assertNotEquals(expectedAxe, expectedCharacter);
    assertNotEquals(expectedAxe.hashCode(), expectedCharacter.hashCode());

    assertNotEquals(expectedAxe, difNameExpectedAxe);
    assertNotEquals(expectedAxe, difDamageExpectedAxe);
    assertNotEquals(expectedAxe, difSpeedExpectedAxe);
    assertNotEquals(expectedAxe, difTypeExpectedWeapon);
  }
}