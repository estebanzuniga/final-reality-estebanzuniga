package controller;

import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EquipmentTest extends GameControllerTest {

    private List<IWeapon> testEngineerWeaponList;
    private List<IWeapon> testEngineerNotWeaponList;
    private List<IWeapon> testKnightWeaponList;
    private List<IWeapon> testKnightNotWeaponList;
    private List<IWeapon> testThiefWeaponList;
    private List<IWeapon> testThiefNotWeaponList;
    private List<IWeapon> testWhiteMageWeaponList;
    private List<IWeapon> testWhiteMageNotWeaponList;
    private List<IWeapon> testBlackMageWeaponList;
    private List<IWeapon> testBlackMageNotWeaponList;

    @BeforeEach
    void setup() {
        super.basicSetUp();

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

    @Test
    public void equipTest() {
        for (IWeapon weapon : testEngineerWeaponList) {
            checkEquipWeapon(testEngineer, weapon, 151, 10);
        }
        for (IWeapon weapon : testKnightWeaponList) {
            checkEquipWeapon(testKnight, weapon, 151, 10);
        }
        for (IWeapon weapon : testThiefWeaponList) {
            checkEquipWeapon(testThief, weapon, 151, 10);
        }
        for (IWeapon weapon : testWhiteMageWeaponList) {
            checkEquipWeapon(testWhiteMage, weapon, 151, 10);
        }
        for (IWeapon weapon : testBlackMageWeaponList) {
            checkEquipWeapon(testBlackMage, weapon, 151, 10);
        }
    }

    @Test
    public void notEquipTest() {
        for (IWeapon weapon : testEngineerNotWeaponList) {
            checkNotEquipWeapon(testEngineer, weapon);
        }
        for (IWeapon weapon : testKnightNotWeaponList) {
            checkNotEquipWeapon(testKnight, weapon);
        }
        for (IWeapon weapon : testThiefNotWeaponList) {
            checkNotEquipWeapon(testThief, weapon);
        }
        for (IWeapon weapon : testWhiteMageNotWeaponList) {
            checkNotEquipWeapon(testWhiteMage, weapon);
        }
        for (IWeapon weapon : testBlackMageNotWeaponList) {
            checkNotEquipWeapon(testBlackMage, weapon);
        }
    }



    /*public IWeapon getEquippedWeaponCharacter(IPlayerCharacter character) {
        return character.getEquippedWeapon();
    }

    public int getDamageCharacter(IPlayerCharacter character) {
        return character.getEquippedWeapon().getDamage();
    }

    public int getWeightCharacter(IPlayerCharacter character) {
        return character.getEquippedWeapon().getWeight();
    }*/


}
