package controller;

import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import com.github.estebanzuniga.finalreality.model.weapon.party.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class EquipTest extends GameControllerTest {

    private ArrayList<IWeapon> notInventoryWeaponList;

    @BeforeEach
    void setup() {
        super.basicSetUp();

        notInventoryWeaponList = new ArrayList<>();
        Axe testNotInventoryAxe = new Axe("TEST_NOT_INVENTORY_AXE", 151, 10);
        Bow testNotInventoryBow = new Bow("TEST_NOT_INVENTORY_BOW", 151, 10);
        Knife testNotInventoryKnife = new Knife("TEST_NOT_INVENTORY_KNIFE", 151, 10);
        Staff testNotInventoryStaff = new Staff("TEST_NOT_INVENTORY_STAFF", 151, 10);
        Sword testNotInventorySword = new Sword("TEST_NOT_INVENTORY_SWORD", 151, 10);
        notInventoryWeaponList.add(testNotInventoryAxe);
        notInventoryWeaponList.add(testNotInventoryBow);
        notInventoryWeaponList.add(testNotInventoryKnife);
        notInventoryWeaponList.add(testNotInventoryStaff);
        notInventoryWeaponList.add(testNotInventorySword);
    }

    @Test
    public void equipTest() {
        for (IWeapon weapon : testEngineerWeaponList) {
            checkEquipWeapon(testEngineer, weapon);
        }
        for (IWeapon weapon : testKnightWeaponList) {
            checkEquipWeapon(testKnight, weapon);
        }
        for (IWeapon weapon : testThiefWeaponList) {
            checkEquipWeapon(testThief, weapon);
        }
        for (IWeapon weapon : testWhiteMageWeaponList) {
            checkEquipWeapon(testWhiteMage, weapon);
        }
        for (IWeapon weapon : testBlackMageWeaponList) {
            checkEquipWeapon(testBlackMage, weapon);
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
        for (IWeapon weapon : notInventoryWeaponList){
            checkNotEquipWeapon(testEngineer, weapon);
            checkNotEquipWeapon(testKnight, weapon);
            checkNotEquipWeapon(testThief, weapon);
            checkNotEquipWeapon(testWhiteMage, weapon);
            checkNotEquipWeapon(testBlackMage, weapon);
        }
    }
}
