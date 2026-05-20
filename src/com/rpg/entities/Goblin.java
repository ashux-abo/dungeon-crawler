package com.rpg.entities;

public class Goblin extends Entity {
  private int level;

  public Goblin(String name) {
    super("Goblin", 50, 50, 15);
    this.level = 1;
  }

  @Override
  public void attack(Entity target) {
    System.out.println("\n>>>" + this.name + "Uses their scratch to the " + target.getName() + "!");

    int formalDamage = this.attackPower;
    if (Math.random() < 0.50) {
      formalDamage *= (int) 2.5;
      System.out.println("CRITICAL HIT!");
    }
  }

  public void displayStatus() {
    System.out.println("\n---[ " + name.toUpperCase() + " STATUS ]---");
    System.out.println("Level: " + level);
    System.out.println("HP: " + hp + "/" + maxHp);
    System.out.println("Attack Power: " + attackPower);
    System.out.println("-".repeat(30));
  }

}
