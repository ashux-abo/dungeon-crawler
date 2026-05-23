package com.rpg.world;

import com.rpg.entities.Goblin;

public class Room {
    private String name;
    private String description;

    //Graph edges
    private Room north;
    private Room south;
    private Room east;
    private Room west;

    private Goblin enemy;

    public Room(String name, String description){
        this.name = name;
        this.description = description;
    }

    //helper for connecting the room together
    public void setExits(Room n, Room s, Room e, Room w){
        this.north = n;
        this.south = s;
        this.east = e;
        this.west = w;
    }

    public void describe(){
        System.out.println("\n===== " + name.toUpperCase() + " =====");
        System.out.println(description);

        System.out.print("Exits available: ");
        if(north != null) System.out.print("[North] ");
        if(south != null) System.out.print("[South] ");
        if(east != null) System.out.print("[East] ");
        if(west != null) System.out.print("[West] ");
        System.out.println();

        if(enemy != null && enemy.isAlive()){
            System.out.println("A wild " + enemy.getName() + " is blocking your path!");
        }
    }
    public Room getNorth() {return north; }
    public Room getSouth() {return  south;}
    public Room getEast() {return east;}
    public Room getWest() {return west;}

    public Goblin getEnemy(){return enemy;}
    public void setEnemy(Goblin enemy) {this.enemy = enemy;}
    public String getName() {return name;}
}
