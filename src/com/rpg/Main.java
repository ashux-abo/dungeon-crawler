package com.rpg;

import com.rpg.entities.Player;

public class Main {
  public static void main(String[] args) {
    System.out.println("=".repeat(33));
    System.out.println("  WELCOME TO THE CLI DUNGEON  ");
    System.out.println("=".repeat(33));
    System.out.println("Initializing system components...");

    System.out.println("\nSetup successfull! Ready to build");

    Player player = new Player("Hero");
    player.displayStatus();

    player.takeDamage(25);

    player.addGold(50);
    player.gainXp(120);

    player.displayStatus();
  }
}
