package com.github.estebanzuniga.finalreality.controller;

import com.github.estebanzuniga.finalreality.model.character.Enemy;
import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.character.player.IPlayerCharacter;
import com.github.estebanzuniga.finalreality.model.character.player.party.*;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import com.github.estebanzuniga.finalreality.model.weapon.party.*;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GameController {

    private final IEventHandler playerHandler = new PlayerEndsTurnHandler(this);
    private final IEventHandler enemyHandler = new EnemyEndsTurnHandler(this);

    private final PropertyChangeSupport attackNotification = new PropertyChangeSupport(this);

    private BlockingQueue<ICharacter> turns = new LinkedBlockingQueue<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<IPlayerCharacter> party = new ArrayList<>();
    private ArrayList<IWeapon> inventory = new ArrayList<>();

    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;
    private Enemy enemy4;
    private IPlayerCharacter character1;
    private IPlayerCharacter character2;
    private IPlayerCharacter character3;
    private IPlayerCharacter character4;

    Random rng = new Random();

    public GameController() {}

    /*public void game() {
        for (var character : playerCharacters) {
            character.waitTurn();
        }
        for (var enemy : enemies) {
            enemy.waitTurn();
        }
        ICharacter head = turns.poll();
        head.attack();
        endTurn(head);
     }*/

    public void setParty() {
        for (int i=0; i<4; i++) {
            //Crear los 4 personajes
        }
        party.add(character1);
        party.add(character2);
        party.add(character3);
        party.add(character4);
    }

    public void setEnemies() {
        enemy1 = createEnemy("Enemy1", rng.nextInt(10)+1, rng.nextInt(100)+400,
                    rng.nextInt(100)+100, rng.nextInt(30)+30);
        enemy2 = createEnemy("Enemy2", rng.nextInt(10)+1, rng.nextInt(100)+400,
                rng.nextInt(100)+100, rng.nextInt(30)+30);
        enemy3 = createEnemy("Enemy3", rng.nextInt(10)+1, rng.nextInt(100)+400,
                rng.nextInt(100)+100, rng.nextInt(30)+30);
        enemy4 = createEnemy("Enemy4", rng.nextInt(10)+1, rng.nextInt(100)+400,
                rng.nextInt(100)+100, rng.nextInt(30)+30);
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);
        enemies.add(enemy4);
    }

    public void endTurn(ICharacter character) {
        turns.add(character);
    }

    public boolean playerWin() {
        return enemies.isEmpty();
    }

    public boolean enemyWin() {
        return party.isEmpty();
    }

    public boolean isAlive(ICharacter character) {
        return character.isAlive();
    }

    public void onCharacterPlayed(ICharacter character) {
        System.out.println(character.getName() + "attacked.");
    }

    public Enemy createEnemy(String name, int weight, int life, int attack, int defense) {
        Enemy enemy = new Enemy(turns, name, weight, life, attack, defense);
        enemies.add(enemy);

        return enemy;
    }

    public void enemyAttack(ICharacter attacker) {
        attacker.attack(party.get(rng.nextInt(party.size())));
        attackNotification.firePropertyChange("Enemy attacked", null, attacker);
    }

    public IPlayerCharacter createEngineer(String name, int life, int defense) {
        IPlayerCharacter engineer = new Engineer(turns, name, life, defense);
        party.add(engineer);

        return engineer;
    }

    public IPlayerCharacter createKnight(String name, int life, int defense) {
        IPlayerCharacter knight = new Knight(turns, name, life, defense);
        party.add(knight);

        return knight;
    }

    public IPlayerCharacter createThief(String name, int life, int defense) {
        IPlayerCharacter thief = new Thief(turns, name, life, defense);
        party.add(thief);

        return thief;
    }

    public IPlayerCharacter createWhiteMage(String name, int life, int defense) {
        IPlayerCharacter whiteMage = new WhiteMage(turns, name, life, defense);
        party.add(whiteMage);

        return whiteMage;
    }

    public IPlayerCharacter createBlackMage(String name, int life, int defense) {
        IPlayerCharacter blackMage = new BlackMage(turns, name, life, defense);
        party.add(blackMage);

        return blackMage;
    }

    public void playerAttack(ICharacter attacker) {
        attacker.attack(enemies.get(rng.nextInt(enemies.size())));
        attackNotification.firePropertyChange("Player attacked", null, attacker);
    }


    /**
     * GETTERS
     */

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


    /**
     * Weapon
     */

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

    /*public void showCharacterStats(IPlayerCharacter character) {
        String name = character.getName();
        int life = character.getLife();
        int defense = character.getDefense();
        IWeapon weapon = character.getEquippedWeapon();
        int damage = weapon.getDamage();
        int weight = weapon.getWeight();
    }

    public void showEnemyStats(IPlayerCharacter character) {
        String name = character.getName();
        int weight = weapon.getWeight();
        int life = character.getLife();
        int damage = weapon.getAttack();
        int defense = character.getDefense();
    }*/