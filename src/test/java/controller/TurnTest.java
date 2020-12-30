package controller;

import com.github.estebanzuniga.finalreality.controller.phases.InvalidMovementException;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TurnTest extends GameControllerTest {

    @BeforeEach
    void setup() {
        super.basicSetUp();
    }

    @Test
    void checkPlayerTurnTest() throws InvalidMovementException, InterruptedException {
        for (IWeapon weapon : testEngineerWeaponList) {
            checkPlayerTurn(testEngineer, testEnemy, weapon);
        }
        for (IWeapon weapon : testKnightWeaponList) {
            checkPlayerTurn(testKnight, testEnemy, weapon);
        }
        for (IWeapon weapon : testThiefWeaponList) {
            checkPlayerTurn(testThief, testEnemy, weapon);
        }
        for (IWeapon weapon : testWhiteMageWeaponList) {
            checkPlayerTurn(testWhiteMage, testEnemy, weapon);
        }
        for (IWeapon weapon : testBlackMageWeaponList) {
            checkPlayerTurn(testBlackMage, testEnemy, weapon);
        }
    }

    @Test
    void checkEnemyTurnTest() throws InvalidMovementException, InterruptedException {
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

    @Test
    public void someMethodsTest() {
        checkSomeMethods();
    }

}
