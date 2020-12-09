package controller;

import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TurnTest extends GameControllerTest {

    @BeforeEach
    void setup() {
        super.basicSetUp();
    }

    @Test
    void checkPlayerTurnTest() {
        for (IWeapon weapon : testEngineerWeaponList) {
            controllerTest.equipWeapon(testEngineer, weapon);
            checkPlayerTurn(testEngineer, testEnemy);
        }
        for (IWeapon weapon : testKnightWeaponList) {
            controllerTest.equipWeapon(testKnight, weapon);
            checkPlayerTurn(testKnight, testEnemy);
        }
        for (IWeapon weapon : testThiefWeaponList) {
            controllerTest.equipWeapon(testThief, weapon);
            checkPlayerTurn(testThief, testEnemy);
        }
        for (IWeapon weapon : testWhiteMageWeaponList) {
            controllerTest.equipWeapon(testWhiteMage, weapon);
            checkPlayerTurn(testWhiteMage, testEnemy);
        }
        for (IWeapon weapon : testBlackMageWeaponList) {
            controllerTest.equipWeapon(testBlackMage, weapon);
            checkPlayerTurn(testBlackMage, testEnemy);
        }
    }

    @Test
    void checkEnemyTurnTest() {
        for (IWeapon weapon : testEngineerWeaponList) {
            controllerTest.equipWeapon(testEngineer, weapon);
            checkEnemyTurn(testEnemy, testEngineer);
        }
        for (IWeapon weapon : testKnightWeaponList) {
            controllerTest.equipWeapon(testKnight, weapon);
            checkEnemyTurn(testEnemy, testKnight);
        }
        for (IWeapon weapon : testThiefWeaponList) {
            controllerTest.equipWeapon(testThief, weapon);
            checkEnemyTurn(testEnemy, testThief);
        }
        for (IWeapon weapon : testWhiteMageWeaponList) {
            controllerTest.equipWeapon(testWhiteMage, weapon);
            checkEnemyTurn(testEnemy, testWhiteMage);
        }
        for (IWeapon weapon : testBlackMageWeaponList) {
            controllerTest.equipWeapon(testBlackMage, weapon);
            checkEnemyTurn(testEnemy, testBlackMage);
        }
    }
}
