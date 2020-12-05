package controller;

import com.github.estebanzuniga.finalreality.controller.GameController;
import com.github.estebanzuniga.finalreality.model.character.Enemy;
import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.character.player.IPlayerCharacter;
import com.github.estebanzuniga.finalreality.model.character.player.party.*;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import com.github.estebanzuniga.finalreality.model.weapon.party.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {

    protected GameController controllerTest;
    protected BlockingQueue<ICharacter> turns;
    protected List<ICharacter> testCharacters;
    protected static final String BLACK_MAGE_NAME = "Vivi";
    protected static final String KNIGHT_NAME = "Adelbert";
    protected static final String WHITE_MAGE_NAME = "Eiko";
    protected static final String ENGINEER_NAME = "Cid";
    protected static final String THIEF_NAME = "Zidane";
    protected static final String ENEMY_NAME = "Goblin";
    protected static final String OTHER_NAME = "Esteban";
    protected Enemy testEnemy;
    protected Engineer testEngineer;
    protected Knight testKnight;
    protected Thief testThief;
    protected WhiteMage testWhiteMage;
    protected BlackMage testBlackMage;
    protected Axe testAxe;
    protected Bow testBow;
    protected Knife testKnife;
    protected Staff testStaff;
    protected Sword testSword;

    @BeforeEach
    protected void basicSetUp() {
        controllerTest = new GameController();
        turns = new LinkedBlockingQueue<>();
        testCharacters = new ArrayList<>();
        testEnemy = new Enemy(turns, ENEMY_NAME, 10, 400, 200, 50);
        testEngineer = new Engineer(turns, ENGINEER_NAME, 500, 100);
        testKnight = new Knight(turns, KNIGHT_NAME, 500, 100);
        testThief = new Thief(turns, THIEF_NAME, 500, 100);
        testWhiteMage = new WhiteMage(turns, WHITE_MAGE_NAME, 500, 100);
        testBlackMage = new BlackMage(turns, BLACK_MAGE_NAME, 500, 100);
        testAxe = new Axe("TEST_AXE" , 151, 10);
        testBow = new Bow("TEST_BOW", 151, 10);
        testKnife = new Knife("TEST_KNIFE", 151, 10);
        testStaff = new Staff("TEST_STAFF", 151, 10);
        testSword = new Sword("TEST_SWORD", 151, 10);
        controllerTest.getInventory().add(testAxe);
        controllerTest.getInventory().add(testBow);
        controllerTest.getInventory().add(testKnife);
        controllerTest.getInventory().add(testStaff);
        controllerTest.getInventory().add(testSword);
    }

    protected void checkCharacterConstructor(final ICharacter expectedCharacter, final ICharacter testEqualCharacter) {
        assertEquals(expectedCharacter, expectedCharacter);
        assertEquals(expectedCharacter, testEqualCharacter);
        assertEquals(expectedCharacter.hashCode(), testEqualCharacter.hashCode());
    }

    protected void checkWeaponConstructor(final IWeapon expectedWeapon, final IWeapon testEqualWeapon) {
        assertEquals(expectedWeapon, expectedWeapon);
        assertEquals(expectedWeapon, testEqualWeapon);
        assertEquals(expectedWeapon.hashCode(), testEqualWeapon.hashCode());
    }

    protected void checkCharacterGetters(final ICharacter expectedCharacter, String name, int life, int defense) {
        assertEquals(name, controllerTest.getNameCharacter(expectedCharacter));
        assertEquals(life, controllerTest.getLifeCharacter(expectedCharacter));
        assertEquals(defense, controllerTest.getDefenseCharacter(expectedCharacter));
    }

    protected void checkEnemyGetters(final Enemy expectedEnemy, String name, int weight, int life, int attack, int defense) {
        assertEquals(name, controllerTest.getNameCharacter(expectedEnemy));
        assertEquals(weight, controllerTest.getWeightEnemy(expectedEnemy));
        assertEquals(life, controllerTest.getLifeCharacter(expectedEnemy));
        assertEquals(attack, controllerTest.getAttackEnemy(expectedEnemy));
        assertEquals(defense, controllerTest.getDefenseCharacter(expectedEnemy));
    }

    protected void checkEquipWeapon(IPlayerCharacter character, IWeapon weapon, int damage, int weight) {
        controllerTest.setEquippedWeaponCharacterNull(character);
        assertNull(controllerTest.getEquippedWeaponCharacter(character));
        controllerTest.equipWeapon(character, weapon);
        assertEquals(weapon, controllerTest.getEquippedWeaponCharacter(character));
        assertEquals(damage, controllerTest.getDamageCharacter(character));
        assertEquals(weight, controllerTest.getWeightCharacter(character));
    }

    protected void checkNotEquipWeapon(IPlayerCharacter character, IWeapon weapon) {
        controllerTest.setEquippedWeaponCharacterNull(character);
        assertNull(controllerTest.getEquippedWeaponCharacter(character));
        controllerTest.equipWeapon(character, weapon);
        assertNull(controllerTest.getEquippedWeaponCharacter(character));
    }
}
