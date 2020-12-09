package controller;

import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AttackTest extends GameControllerTest {

    @BeforeEach
    void setup() {
        super.basicSetUp();
    }

    @Test
    void checkPlayerAttackTest() {
        for (IWeapon weapon : testEngineerWeaponList) {
            controllerTest.equipWeapon(testEngineer, weapon);
            checkPlayerAttack(testEnemy, testEngineer);
        }
        for (IWeapon weapon : testKnightWeaponList) {
            controllerTest.equipWeapon(testKnight, weapon);
            checkPlayerAttack(testEnemy, testKnight);
        }
        for (IWeapon weapon : testThiefWeaponList) {
            controllerTest.equipWeapon(testThief, weapon);
            checkPlayerAttack(testEnemy, testThief);
        }
        for (IWeapon weapon : testWhiteMageWeaponList) {
            controllerTest.equipWeapon(testWhiteMage, weapon);
            checkPlayerAttack(testEnemy, testWhiteMage);
        }
        for (IWeapon weapon : testBlackMageWeaponList) {
            controllerTest.equipWeapon(testBlackMage, weapon);
            checkPlayerAttack(testEnemy, testBlackMage);
        }
    }

    @Test
    void checkEnemyAttackTest() {
        for (IWeapon weapon : testEngineerWeaponList) {
            controllerTest.equipWeapon(testEngineer, weapon);
            checkEnemyAttack(testEngineer, testEnemy);
        }
        for (IWeapon weapon : testKnightWeaponList) {
            controllerTest.equipWeapon(testKnight, weapon);
            checkEnemyAttack(testKnight, testEnemy);
        }
        for (IWeapon weapon : testThiefWeaponList) {
            controllerTest.equipWeapon(testThief, weapon);
            checkEnemyAttack(testThief, testEnemy);
        }
        for (IWeapon weapon : testWhiteMageWeaponList) {
            controllerTest.equipWeapon(testWhiteMage, weapon);
            checkEnemyAttack(testWhiteMage, testEnemy);
        }
        for (IWeapon weapon : testBlackMageWeaponList) {
            controllerTest.equipWeapon(testBlackMage, weapon);
            checkEnemyAttack(testBlackMage, testEnemy);
        }
    }
}
