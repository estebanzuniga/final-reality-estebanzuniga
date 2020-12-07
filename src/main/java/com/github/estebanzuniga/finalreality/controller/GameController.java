package com.github.estebanzuniga.finalreality.controller;

import com.github.estebanzuniga.finalreality.controller.handlers.CharacterIsDeadHandler;
import com.github.estebanzuniga.finalreality.controller.handlers.EnemyEndsTurnHandler;
import com.github.estebanzuniga.finalreality.controller.handlers.IEventHandler;
import com.github.estebanzuniga.finalreality.controller.handlers.PlayerEndsTurnHandler;
import com.github.estebanzuniga.finalreality.model.character.Enemy;
import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.character.player.IPlayerCharacter;
import com.github.estebanzuniga.finalreality.model.character.player.party.*;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import com.github.estebanzuniga.finalreality.model.weapon.party.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GameController {

    private final IEventHandler playerEndsTurnHandler = new PlayerEndsTurnHandler(this);
    private final IEventHandler enemyEndsTurnHandler = new EnemyEndsTurnHandler(this);
    private final IEventHandler characterIsDeadHandler = new CharacterIsDeadHandler(this);

    private final BlockingQueue<ICharacter> turns = new LinkedBlockingQueue<>();
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private final ArrayList<IPlayerCharacter> party = new ArrayList<>();
    private final ArrayList<IWeapon> inventory = new ArrayList<>();
    Random rng = new Random();

    public GameController() {}

    public void playerTurn(IPlayerCharacter character, int indexAttack) {
        playerAttack(character, indexAttack);
        endTurn(character);
    }

    public void enemyTurn(Enemy enemy) {
        enemyAttack(enemy);
        endTurn(enemy);
    }

    public void endTurn(ICharacter character) {
        turns.remove(character);
        turns.add(character);
        if (playerWon() || enemyWon()) {
            endGame();
        }
    }

    public void endGame() {
        //
    }

    public boolean playerWon() {
        return enemies.isEmpty();
    }

    public boolean enemyWon() {
        return party.isEmpty();
    }

    public boolean isDead(ICharacter character) {
        return !character.isAlive();
    }

    public void enemyAttack(Enemy attacker) {
        attacker.attack(party.get(rng.nextInt(party.size())));
    }

    public void playerAttack(IPlayerCharacter attacker, int indexEnemy) {
        attacker.attack(enemies.get(indexEnemy));
    }

    public Enemy createEnemy(String name, int weight, int life, int attack, int defense) {
        Enemy enemy = new Enemy(turns, name, weight, life, attack, defense);
        enemies.add(enemy);
        enemy.addCharacterIsDeadListener(characterIsDeadHandler);
        enemy.addEnemyEndsTurnListener(enemyEndsTurnHandler);
        return enemy;
    }

    public IPlayerCharacter createEngineer(String name, int life, int defense) {
        IPlayerCharacter engineer = new Engineer(turns, name, life, defense);
        party.add(engineer);
        engineer.addCharacterIsDeadListener(characterIsDeadHandler);
        engineer.addPlayerEndsTurnListener(playerEndsTurnHandler);
        return engineer;
    }

    public IPlayerCharacter createKnight(String name, int life, int defense) {
        IPlayerCharacter knight = new Knight(turns, name, life, defense);
        knight.addCharacterIsDeadListener(characterIsDeadHandler);
        knight.addPlayerEndsTurnListener(playerEndsTurnHandler);
        party.add(knight);

        return knight;
    }

    public IPlayerCharacter createThief(String name, int life, int defense) {
        IPlayerCharacter thief = new Thief(turns, name, life, defense);
        thief.addCharacterIsDeadListener(characterIsDeadHandler);
        thief.addPlayerEndsTurnListener(playerEndsTurnHandler);
        party.add(thief);

        return thief;
    }

    public IPlayerCharacter createWhiteMage(String name, int life, int defense) {
        IPlayerCharacter whiteMage = new WhiteMage(turns, name, life, defense);
        whiteMage.addCharacterIsDeadListener(characterIsDeadHandler);
        whiteMage.addPlayerEndsTurnListener(playerEndsTurnHandler);
        party.add(whiteMage);

        return whiteMage;
    }

    public IPlayerCharacter createBlackMage(String name, int life, int defense) {
        IPlayerCharacter blackMage = new BlackMage(turns, name, life, defense);
        blackMage.addCharacterIsDeadListener(characterIsDeadHandler);
        blackMage.addPlayerEndsTurnListener(playerEndsTurnHandler);
        party.add(blackMage);

        return blackMage;
    }


    public String getNameCharacter(ICharacter character) {
        return character.getName();
    }

    public int getLifeCharacter(ICharacter character) {
        return character.getLife();
    }

    public int getDefenseCharacter(ICharacter character) {
        return character.getDefense();
    }

    public int getWeightEnemy(Enemy enemy) {
        return enemy.getWeight();
    }

    public int getAttackEnemy(Enemy enemy) {
        return enemy.getAttack();
    }

    public IWeapon getEquippedWeaponCharacter(IPlayerCharacter character) {
        return character.getEquippedWeapon();
    }

    public void setEquippedWeaponCharacterNull(IPlayerCharacter character) {
        character.setEquippedWeaponNull();
    }

    public int getDamageCharacter(IPlayerCharacter character) {
        return character.getEquippedWeapon().getDamage();
    }

    public int getWeightCharacter(IPlayerCharacter character) {
        return character.getEquippedWeapon().getWeight();
    }


    public ArrayList<IWeapon> getInventory() {
        return inventory;
    }

    public void equipWeapon(IPlayerCharacter character, IWeapon weapon) {
        if (inventory.contains(weapon)) {
            character.equip(weapon);
        }
    }

    public IWeapon createAxe(String name, int damage, int weight) {
        IWeapon axe = new Axe(name, damage, weight);
        inventory.add(axe);
        return axe;
    }

    public IWeapon createBow(String name, int damage, int weight) {
        IWeapon bow = new Bow(name, damage, weight);
        inventory.add(bow);
        return bow;
    }

    public IWeapon createKnife(String name, int damage, int weight) {
        IWeapon knife = new Knife(name, damage, weight);
        inventory.add(knife);
        return knife;
    }

    public IWeapon createStaff(String name, int damage, int weight) {
        IWeapon staff = new Staff(name, damage, weight);
        inventory.add(staff);
        return staff;
    }

    public IWeapon createSword(String name, int damage, int weight) {
        IWeapon sword = new Sword(name, damage, weight);
        inventory.add(sword);
        return sword;
    }
}