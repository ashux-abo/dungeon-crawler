package com.rpg;

import com.rpg.engine.GameEngine;
import com.rpg.entities.Player;

import java.util.Scanner;

public class Main {
  static void main() {

    System.out.println("=".repeat(33));
    System.out.println("  WELCOME TO THE CLI DUNGEON  ");
    System.out.println("=".repeat(33));

    initializePlayer();
  }

  private static void initializePlayer(){
    Scanner sc = new Scanner(System.in);
    System.out.print(" Enter Your Name: ");
    String initializeName = sc.next().toUpperCase().trim();
    Player player = new Player(initializeName);
    System.out.println("=".repeat(5) + " Name: " + player.getName() + " " + "=".repeat(5));

    GameEngine engine = new GameEngine(player.getName());
    engine.start();
  }
}
