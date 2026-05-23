package com.rpg.engine;

import com.rpg.entities.*;
import com.rpg.items.Item;
import com.rpg.world.*;

import java.util.Scanner;

public class GameEngine {
    private Player player;
    private Room currentRoom;
    private Room previousRoom;
    private boolean isRunning;
    private Scanner scanner;
    private Entity enemyExist;

    public GameEngine(String player){
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
        this.player = new Player(player);
        initializeMap();
    }

    private void initializeMap(){
        Room spawnRoom = new Room("Dungeon Entrance", "Cold wind howls from the surface. The cobblestones are damp.");
        Room darkHallway = new Room("Eerie Hallway", "A narrow, long hallway flickering with dying torches.");
        Room treasureRoom = new Room("Treasure Vault", "Gold coins litter the floor. A massive stone chest sits in the center.");
        Room alchemistLab = new Room("Alchemist Lab", "Shattered glass vats line the walls. A pungent green fog hugs the floor.");
        Room barracks = new Room("Guard Barracks", "Decayed wooden bunk beds are scattered around. It looks empty and safe.");
        //Spawn room: north goes to hallway
        spawnRoom.setExits(darkHallway, null, null, null);

        //Hallway: south goes to spawn and west goes to treasure room
        darkHallway.setExits(treasureRoom, spawnRoom, barracks, alchemistLab);

        //Treasure room: east goest to hallway
        treasureRoom.setExits(null, darkHallway, null, null);

        alchemistLab.setExits(null, null, darkHallway,null);

        barracks.setExits(null, null, null, darkHallway);

        treasureRoom.setEnemy(new Goblin("Peasant Goblin"));
        alchemistLab.setEnemy(new Skeleton("Rattling Skeleton"));

        barracks.setRoomLoot(new Item("Elixir of life", "An ancient golden brew. Restores 60 HP.", 60));

        this.currentRoom = spawnRoom;
        this.previousRoom = spawnRoom;
    }

    public void start(){
        System.out.println("Engine successfully started. Adventure awaits!\n");

        while (isRunning){
            //display current environment
            currentRoom.describe();
            if (currentRoom.getEnemy() != null){
                enemyExist = currentRoom.getEnemy();
                if(enemyExist.isAlive()){
                    System.out.println("DEFEAT THE ENEMY");
                    handleCombatSequence(enemyExist);

                    if(!player.isAlive()){
                        System.out.println("\nYou have been perished in the dark.");
                        isRunning = false;
                        break;
                    }

                    continue;
                }
            }
            System.out.println("\nWhat would you like to do? ");

            //capture user input
            String input = scanner.nextLine().toLowerCase().trim();

            //process the action
            processCommand(input);

            //enforce state boundaries
            if(!player.isAlive()){
                System.out.println("\n You have perished in the dark.");
                isRunning = false;
            }
        }
        System.out.println("Thank you for playing CLI Dungeon Crawler");
        scanner.close();
    }

    //text parser
    private void processCommand(String input){
        String[] words = input.split(" ");
        String verb = words[0];

        switch (verb){
            case "quit":
                break;
            case "exit":
                this.isRunning = false;
                break;
            case "status":
                player.displayStatus();
                break;
            case "move":
                if(words.length < 2){
                    System.out.println("Where you want to move? (e.g, 'move north', 'move south')");
                }else{
                    handleMovement(words[1]);
                }
                break;
            default:
                System.out.println("Unknown action. Try commands like: 'move north', 'status', or 'quit'.");
                break;
        }
    }

    //graph navigation logic
    private void handleMovement(String direction){
        Room nextRoom = null;

        if(direction.equals("north")) nextRoom = currentRoom.getNorth();
        else if(direction.equals("east")) nextRoom = currentRoom.getEast();
        else if (direction.equals("west")) nextRoom = currentRoom.getWest();
        else if (direction.equals("south")) nextRoom = currentRoom.getSouth();

        if(nextRoom == null){
            System.out.println("\nYou bumped into a solid stone wall. There is no path that way");
        } else {
            System.out.println("\nYou walk " + direction + "...");
            this.previousRoom = this.currentRoom;
            this.currentRoom = nextRoom;
        }
    }

    //handleCombat
    private void handleCombatSequence(Entity enemy){
       System.out.println("=".repeat(50));
       System.out.println(" ".repeat(10)
               + " PLAYER: "
               + player.getName() + ": "
               + player.getHp() + "/"
               + player.getMaxHp() + " HP");
       System.out.println(" ".repeat(10)
               + " ENEMY: "
               + enemy.getName()
               + ": "
               + enemy.getHp()
               + "/" + enemy.getMaxHp() + " HP"
               );
        System.out.println("=".repeat(50));

        System.out.println("\n[ATTACK] | [RUN] | [INVENTORY]");
        System.out.print("What action do you want to perform? (e.g., 'attack', 'inventory') ");
        String action = scanner.nextLine().toLowerCase().trim();
        processAction(action);
    }

    //TO DO: perform a way to use the item in combat with a chance of the enemy attack the player
    public void handleItemUse(int useItem){

    }

    //parse the user action
    public void processAction(String action){
        String[] words = action.split(" ");
        String verb = words[0];

        switch (verb){
            case "attack":
                if(!player.isAlive()){
                    break;
                }
                player.attack(enemyExist);

                if(enemyExist.isAlive()){
                    enemyExist.attack(player);
                }else{
                    System.out.println("You defeated the " + enemyExist.getName() + "!");
                    player.gainXp(30);
                    player.addGold(20);
                }
                break;
            case "inventory":
                player.displayInventory();

                break;
            case "run":
                if(Math.random() < 0.50){
                    System.out.println("Escape Successfully!");
                    if(this.previousRoom != null){
                        this.currentRoom = this.previousRoom;
                    }
                }else{
                    System.out.println("Escape Failed!");
                    enemyExist.attack(player);
                }
                break;
            default:
                System.out.println("Unknown action. Try commands like: 'attack', 'inventory', or 'run'.");
                break;
        }
    }
}
