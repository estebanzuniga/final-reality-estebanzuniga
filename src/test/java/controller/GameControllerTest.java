package controller;

import com.github.estebanzuniga.finalreality.controller.GameController;
import com.github.estebanzuniga.finalreality.controller.phases.*;
import com.github.estebanzuniga.finalreality.model.character.Enemy;
import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.character.player.IPlayerCharacter;
import com.github.estebanzuniga.finalreality.model.character.player.party.*;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import com.github.estebanzuniga.finalreality.model.weapon.party.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.zip.CheckedOutputStream;

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
        assertEquals(500, Integer.parseInt(controllerTest.getLifeCharacter(expectedCharacter)));
        assertEquals(100, Integer.parseInt(controllerTest.getDefenseCharacter(expectedCharacter)));
    }

    protected void checkEnemyGetters(final Enemy expectedEnemy) {
        assertEquals(GameControllerTest.ENEMY_NAME, controllerTest.getNameCharacter(expectedEnemy));
        assertEquals(400, Integer.parseInt(controllerTest.getLifeCharacter(expectedEnemy)));
        assertEquals(200, Integer.parseInt(controllerTest.getAttackEnemy(expectedEnemy)));
        assertEquals(50, Integer.parseInt(controllerTest.getDefenseCharacter(expectedEnemy)));
    }

    protected void checkEquipWeapon(IPlayerCharacter character, IWeapon weapon) {
        controllerTest.setEquippedWeaponCharacterNull(character);
        assertNull(controllerTest.getEquippedWeaponCharacter(character));
        controllerTest.equipWeapon(character, weapon);
        assertEquals(weapon, controllerTest.getEquippedWeaponCharacter(character));
    }

    protected void checkNotEquipWeapon(IPlayerCharacter character, IWeapon weapon) {
        controllerTest.setEquippedWeaponCharacterNull(character);
        assertNull(controllerTest.getEquippedWeaponCharacter(character));
        controllerTest.equipWeapon(character, weapon);
        assertNull(controllerTest.getEquippedWeaponCharacter(character));
    }

    protected void checkPlayerAttack(final Enemy attacked, final IPlayerCharacter attacker) {
        controllerTest.getEnemies().add(attacked);
        controllerTest.getParty().add(attacker);
        String life = controllerTest.getLifeCharacter(attacked);
        assertFalse(controllerTest.isDead(attacked));
        Enemy attackedEnemy = controllerTest.getEnemies().get(0);
        controllerTest.attack(controllerTest.getPlayer(0), attackedEnemy);
        assertNotEquals(life, controllerTest.getLifeCharacter(attacked));
        while (!controllerTest.isDead(attacked)) {
            controllerTest.attack(attacker, attackedEnemy);
        }
        assertTrue(controllerTest.isDead(attacked));
        controllerTest.setLifeCharacter(attacked, Integer.parseInt(life));
        assertEquals(life, controllerTest.getLifeCharacter(attacked));
    }

    protected void checkEnemyAttack(final IPlayerCharacter attacked, final Enemy attacker) {
        controllerTest.getParty().add(attacked);
        controllerTest.getEnemies().add(attacker);
        String life = controllerTest.getLifeCharacter(attacked);
        assertFalse(controllerTest.isDead(attacked));
        IPlayerCharacter attackedCharacter = controllerTest.getParty().get(0);
        controllerTest.attack(controllerTest.getEnemy(0), attackedCharacter);
        assertNotEquals(life, controllerTest.getLifeCharacter(attacked));
        while (!controllerTest.isDead(attacked)) {
            controllerTest.attack(attacker, attackedCharacter);
        }
        assertTrue(controllerTest.isDead(attacked));
        controllerTest.setLifeCharacter(attacked, Integer.parseInt(life));
        assertEquals(life, controllerTest.getLifeCharacter(attacked));
    }

    public void checkPlayerTurn(IPlayerCharacter playerCharacter, Enemy enemy, IWeapon weapon) throws InvalidMovementException, InterruptedException {
        controllerTest.getAllPlayersList().clear();
        controllerTest.getAllEnemiesList().clear();
        controllerTest.getParty().clear();
        controllerTest.getEnemies().clear();
        controllerTest.setPhase(new CombatPhase());
        controllerTest.getParty().add(playerCharacter);
        controllerTest.getAllPlayersList().add(playerCharacter);
        controllerTest.getEnemies().add(enemy);
        controllerTest.getAllEnemiesList().add(enemy);
        assertEquals(playerCharacter, controllerTest.getAllPlayers(0));
        String life = controllerTest.getLifeCharacter(enemy);
        while (Integer.parseInt(controllerTest.getLifeCharacter(enemy)) > 0) {
            controllerTest.tryToEquip(playerCharacter, weapon);
            controllerTest.tryToAttack(playerCharacter, enemy);
            controllerTest.setPhase(new CombatPhase());
        }
        assertEquals(0, Integer.parseInt(controllerTest.getLifeCharacter(enemy)));
        controllerTest.setLifeCharacter(enemy, Integer.parseInt(life));
        assertEquals(life, controllerTest.getLifeCharacter(enemy));
        assertTrue(controllerTest.playerWon());
    }

    public void checkEnemyTurn(Enemy enemy, IPlayerCharacter playerCharacter) throws InvalidMovementException, InterruptedException {
        controllerTest.getAllEnemiesList().clear();
        controllerTest.getAllPlayersList().clear();
        controllerTest.getEnemies().clear();
        controllerTest.getParty().clear();
        controllerTest.setPhase(new CombatPhase());
        controllerTest.getEnemies().add(enemy);
        controllerTest.getAllEnemiesList().add(enemy);
        controllerTest.getParty().add(playerCharacter);
        controllerTest.getAllPlayersList().add(playerCharacter);
        assertEquals(enemy, controllerTest.getAllEnemies(0));
        String life = controllerTest.getLifeCharacter(playerCharacter);
        while (Integer.parseInt(controllerTest.getLifeCharacter(playerCharacter)) > 0) {
            controllerTest.tryToAttack(enemy, playerCharacter);
            controllerTest.setPhase(new CombatPhase());
        }
        assertEquals(0, Integer.parseInt(controllerTest.getLifeCharacter(playerCharacter)));
        controllerTest.setLifeCharacter(playerCharacter, Integer.parseInt(life));
        assertEquals(life, controllerTest.getLifeCharacter(playerCharacter));
        assertTrue(controllerTest.enemyWon());
    }

    public void checkOtherGetters() {
        controllerTest.setPhase(new InitialPhase());
        assertEquals("Initial phase", controllerTest.getCurrentPhase());
        controllerTest.setPhase(new CombatPhase());
        controllerTest.getInventory().clear();
        controllerTest.completeInventory();
        controllerTest.tryToEquip(testEngineer, controllerTest.getWeapon(0));
        assertEquals(controllerTest.getDamageEquippedWeapon(testEngineer), controllerTest.getDamageWeapon(0));
        assertEquals("Axe", controllerTest.getNameWeapon(controllerTest.getEquippedWeaponCharacter(testEngineer)));
        //assertEquals(151, controllerTest.getDamageEquippedWeapon(testEngineer));
        controllerTest.setEquippedWeaponCharacterNull(testEngineer);
        assertEquals("Combat phase", controllerTest.getCurrentPhase());
        controllerTest.setPhase(new EndTurnPhase());
        assertEquals("End turn phase", controllerTest.getCurrentPhase());
        controllerTest.setPhase(new FinalPhase());
        assertEquals("Final phase", controllerTest.getCurrentPhase());
        controllerTest.setPhase(new InitialPhase());
        controllerTest.setCurrentCharacter(testEnemy);
        assertEquals(testEnemy, controllerTest.getCurrentCharacter());
        controllerTest.setCurrentWeapon(testAxe);
        assertEquals(testAxe, controllerTest.getCurrentWeapon());
        controllerTest.setCurrentOpponentToAttack(testEnemy);
        assertEquals(testEnemy, controllerTest.getCurrentOpponentToAttack());
    }

    public void checkSomeMethods() {
        controllerTest.setPhase(new InitialPhase());
        controllerTest.getParty().add(testEngineer);
        controllerTest.tryToPartyIsComplete();
        assertEquals("Initial phase", controllerTest.getCurrentPhase());
        controllerTest.getParty().add(testKnight);
        controllerTest.tryToPartyIsComplete();
        assertEquals("Initial phase", controllerTest.getCurrentPhase());
        controllerTest.getParty().add(testThief);
        controllerTest.tryToPartyIsComplete();
        assertEquals("Combat phase", controllerTest.getCurrentPhase());

        controllerTest.setPhase(new FinalPhase());
        assertEquals("Final phase", controllerTest.getCurrentPhase());
        controllerTest.tryToNewGame();
        assertTrue(controllerTest.getTurns().isEmpty());
        assertTrue(controllerTest.getParty().isEmpty());
        assertTrue(controllerTest.getEnemies().isEmpty());
        assertTrue(controllerTest.getInventory().isEmpty());
        assertTrue(controllerTest.getAllPlayersList().isEmpty());
        assertTrue(controllerTest.getAllEnemiesList().isEmpty());
        assertEquals(null, controllerTest.getCurrentCharacter());
        assertEquals(null, controllerTest.getCurrentWeapon());
        assertEquals(null, controllerTest.getCurrentOpponentToAttack());

        controllerTest.setPhase(new InitialPhase());

        controllerTest.setEnemies();
        assertEquals(3, controllerTest.getAllEnemiesList().size());
        controllerTest.getAllEnemiesList().clear();
    }
}