package controller;

import com.github.estebanzuniga.finalreality.model.character.Enemy;
import com.github.estebanzuniga.finalreality.model.character.player.IPlayerCharacter;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConstructorTest extends GameControllerTest {

    protected Enemy testEqualEnemy;
    protected IPlayerCharacter testEqualEngineer;
    protected IPlayerCharacter testEqualKnight;
    protected IPlayerCharacter testEqualThief;
    protected IPlayerCharacter testEqualWhiteMage;
    protected IPlayerCharacter testEqualBlackMage;
    protected IWeapon testEqualAxe;
    protected IWeapon testEqualBow;
    protected IWeapon testEqualKnife;
    protected IWeapon testEqualStaff;
    protected IWeapon testEqualSword;

    @BeforeEach
    void setup() {
        super.basicSetUp();
        testEqualEnemy = controllerTest.createEnemy(ENEMY_NAME, 10, 400, 200, 50);
        testEqualEngineer = controllerTest.createEngineer(ENGINEER_NAME, 500, 100);
        testEqualKnight = controllerTest.createKnight(KNIGHT_NAME, 500, 100);
        testEqualThief = controllerTest.createThief(THIEF_NAME, 500, 100);
        testEqualWhiteMage = controllerTest.createWhiteMage(WHITE_MAGE_NAME, 500, 100);
        testEqualBlackMage = controllerTest.createBlackMage(BLACK_MAGE_NAME, 500, 100);
        testEqualAxe = controllerTest.createAxe("TEST_AXE" , 151, 10);
        testEqualBow = controllerTest.createBow("TEST_BOW", 151, 10);
        testEqualKnife = controllerTest.createKnife("TEST_KNIFE", 151, 10);
        testEqualStaff = controllerTest.createStaff("TEST_STAFF", 151, 10);
        testEqualSword = controllerTest.createSword("TEST_SWORD", 151, 10);
    }

    @Test
    public void characterConstructorTest() {
        checkCharacterConstructor(testEnemy, testEqualEnemy);
        checkCharacterConstructor(testEngineer, testEqualEngineer);
        checkCharacterConstructor(testKnight, testEqualKnight);
        checkCharacterConstructor(testThief, testEqualThief);
        checkCharacterConstructor(testWhiteMage, testEqualWhiteMage);
        checkCharacterConstructor(testBlackMage, testEqualBlackMage);
    }

    @Test
    public void weaponConstructorTest() {
        checkWeaponConstructor(testAxe, testEqualAxe);
        checkWeaponConstructor(testBow, testEqualBow);
        checkWeaponConstructor(testKnife, testEqualKnife);
        checkWeaponConstructor(testStaff, testEqualStaff);
        checkWeaponConstructor(testSword, testEqualSword);
    }
}
