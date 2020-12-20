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
    protected List<IWeapon> testEngineerWeaponList;
    protected List<IWeapon> testEngineerNotWeaponList;
    protected List<IWeapon> testKnightWeaponList;
    protected List<IWeapon> testKnightNotWeaponList;
    protected List<IWeapon> testThiefWeaponList;
    protected List<IWeapon> testThiefNotWeaponList;
    protected List<IWeapon> testWhiteMageWeaponList;
    protected List<IWeapon> testWhiteMageNotWeaponList;
    protected List<IWeapon> testBlackMageWeaponList;
    protected List<IWeapon> testBlackMageNotWeaponList;

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

        testEngineerWeaponList = new ArrayList<>();
        testEngineerWeaponList.add(testAxe);
        testEngineerWeaponList.add(testBow);
        testEngineerNotWeaponList = new ArrayList<>();
        testEngineerNotWeaponList.add(testKnife);
        testEngineerNotWeaponList.add(testStaff);
        testEngineerNotWeaponList.add(testSword);

        testKnightWeaponList = new ArrayList<>();
        testKnightWeaponList.add(testSword);
        testKnightWeaponList.add(testAxe);
        testKnightWeaponList.add(testKnife);
        testKnightNotWeaponList = new ArrayList<>();
        testKnightNotWeaponList.add(testBow);
        testKnightNotWeaponList.add(testStaff);

        testThiefWeaponList = new ArrayList<>();
        testThiefWeaponList.add(testSword);
        testThiefWeaponList.add(testStaff);
        testThiefWeaponList.add(testBow);
        testThiefNotWeaponList = new ArrayList<>();
        testThiefNotWeaponList.add(testAxe);
        testThiefNotWeaponList.add(testKnife);

        testWhiteMageWeaponList = new ArrayList<>();
        testWhiteMageWeaponList.add(testStaff);
        testWhiteMageNotWeaponList = new ArrayList<>();
        testWhiteMageNotWeaponList.add(testAxe);
        testWhiteMageNotWeaponList.add(testBow);
        testWhiteMageNotWeaponList.add(testKnife);
        testWhiteMageNotWeaponList.add(testSword);

        testBlackMageWeaponList = new ArrayList<>();
        testBlackMageWeaponList.add(testKnife);
        testBlackMageWeaponList.add(testStaff);
        testBlackMageNotWeaponList = new ArrayList<>();
        testBlackMageNotWeaponList.add(testAxe);
        testBlackMageNotWeaponList.add(testBow);
        testBlackMageNotWeaponList.add(testSword);
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

    protected void checkCharacterGetters(final ICharacter expectedCharacter, String name) {
        assertEquals(name, controllerTest.getNameCharacter(expectedCharacter));
        assertEquals(500, controllerTest.getLifeCharacter(expectedCharacter));
        assertEquals(100, controllerTest.getDefenseCharacter(expectedCharacter));
    }

    protected void checkEnemyGetters(final Enemy expectedEnemy) {
        assertEquals(GameControllerTest.ENEMY_NAME, controllerTest.getNameCharacter(expectedEnemy));
        assertEquals(10, controllerTest.getWeightEnemy(expectedEnemy));
        assertEquals(400, controllerTest.getLifeCharacter(expectedEnemy));
        assertEquals(200, controllerTest.getAttackEnemy(expectedEnemy));
        assertEquals(50, controllerTest.getDefenseCharacter(expectedEnemy));
    }

    protected void checkEquipWeapon(IPlayerCharacter character, IWeapon weapon) {
        controllerTest.setEquippedWeaponCharacterNull(character);
        assertNull(controllerTest.getEquippedWeaponCharacter(character));
        controllerTest.equipWeapon(character, weapon);
        assertEquals(weapon, controllerTest.getEquippedWeaponCharacter(character));
        assertEquals(151, controllerTest.getDamageCharacter(character));
        assertEquals(10, controllerTest.getWeightCharacter(character));
    }

    protected void checkNotEquipWeapon(IPlayerCharacter character, IWeapon weapon) {
        controllerTest.setEquippedWeaponCharacterNull(character);
        assertNull(controllerTest.getEquippedWeaponCharacter(character));
        controllerTest.equipWeapon(character, weapon);
        assertNull(controllerTest.getEquippedWeaponCharacter(character));
    }

    protected void checkPlayerAttack(final Enemy attacked, final IPlayerCharacter attacker) {
        controllerTest.addEnemy(attacked);
        controllerTest.addPlayer(attacker);
        int life = controllerTest.getLifeCharacter(attacked);
        assertFalse(controllerTest.isDead(attacked));
        controllerTest.playerAttack(controllerTest.getPlayer(0),0);
        assertNotEquals(life, controllerTest.getLifeCharacter(attacked));
        while (!controllerTest.isDead(attacked)) {
            controllerTest.playerAttack(attacker,0);
        }
        assertTrue(controllerTest.isDead(attacked));
        controllerTest.setLifeCharacter(attacked, life);
        assertEquals(life, controllerTest.getLifeCharacter(attacked));
    }

    protected void checkEnemyAttack(final IPlayerCharacter attacked, final Enemy attacker) {
        controllerTest.addPlayer(attacked);
        controllerTest.addEnemy(attacker);
        int life = controllerTest.getLifeCharacter(attacked);
        assertFalse(controllerTest.isDead(attacked));
        controllerTest.enemyAttack(controllerTest.getEnemy(0),0);
        assertNotEquals(life, controllerTest.getLifeCharacter(attacked));
        while (!controllerTest.isDead(attacked)) {
            controllerTest.enemyAttack(attacker,0);
        }
        assertTrue(controllerTest.isDead(attacked));
        controllerTest.setLifeCharacter(attacked, life);
        assertEquals(life, controllerTest.getLifeCharacter(attacked));
    }

    public void checkPlayerTurn(IPlayerCharacter playerCharacter, Enemy enemy, IWeapon weapon) {
        controllerTest.addPlayer(playerCharacter);
        controllerTest.addEnemy(enemy);
        int life = controllerTest.getLifeCharacter(enemy);
        while (controllerTest.getLifeCharacter(enemy) > 0) {
            controllerTest.playerTurn(controllerTest.getPlayer(0), 0, weapon);
        }
        controllerTest.setLifeCharacter(enemy, life);
        assertEquals(life, controllerTest.getLifeCharacter(enemy));
        assertTrue(controllerTest.playerWon());
    }

    public void checkEnemyTurn(Enemy enemy, IPlayerCharacter playerCharacter) {
        controllerTest.addEnemy(enemy);
        controllerTest.addPlayer(playerCharacter);
        int life = controllerTest.getLifeCharacter(playerCharacter);
        while (controllerTest.getLifeCharacter(playerCharacter) > 0) {
            controllerTest.enemyTurn(controllerTest.getEnemy(0), 0);
        }
        controllerTest.setLifeCharacter(playerCharacter, life);
        assertEquals(life, controllerTest.getLifeCharacter(playerCharacter));
        assertTrue(controllerTest.enemyWon());
    }
}
