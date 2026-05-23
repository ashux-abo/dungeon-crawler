package com.rpg.engine;

import com.rpg.entities.*;
import com.rpg.world.*;

import java.util.Scanner;

public class GameEngine {
    private Player player;
    private Room currentRoom;
    private boolean isRunning;
    private Scanner scanner;

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

        //Spawn room: north goes to hallway
        spawnRoom.setExits(darkHallway, null, null, null);

        //Hallway: south goes to spawn and west goes to treasure room
        darkHallway.setExits(null, spawnRoom, null, treasureRoom);

        //Treasure room: east goest to hallway
        treasureRoom.setExits(null, null, darkHallway, null);
        treasureRoom.setEnemy(new Goblin("Peasant Goblin"));

        this.currentRoom = spawnRoom;
    }

    public void start(){
        System.out.println("Engine successfully started. Adventure awaits!\n");

        while (isRunning){
            //display current environment
            currentRoom.describe();;
            System.out.println("\nWhat would you like to do? ");

            //capture user input
            String input = scanner.nextLine().toLowerCase().trim();

            //process the action
            processCommand(input);

            //enforce state boundaries
            if(!player.isAlive()){
                System.out.println("\n You have presihed in the dark.");
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
            this.currentRoom = nextRoom;
        }
    }
}
