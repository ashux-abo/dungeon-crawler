package com.rpg.entities;
import java.util.ArrayList;
import com.rpg.items.Item;

public class Player extends Entity {
  private int exp;
  private int level;
  private int gold;

  private ArrayList<Item> inventory;

  public Player(String name) {
    super(name, 100, 100, 15);
    this.exp = 0;
    this.level = 1;
    this.gold = 0;
    this.inventory = new ArrayList<Item>();
    this.inventory.add(new Item("Health Potion", "A shimmering red liquid that restores 30 HP.", 30));
  }

  @Override
  public void attack(Entity target) {
    System.out.println("\n>>> " + this.name + " swings their sword at " + target.getName() + "!");

    int formalDamage = this.attackPower;
    if (Math.random() < 0.20) {
      formalDamage *= 1.5;
      System.out.println("CRITICAL HIT!");
    }
    target.takeDamage(formalDamage);
  }

  public void gainXp(int amount) {
    this.exp += amount;
    System.out.println("You gained " + amount + " EXP!");

    if (this.exp >= this.level * 100) {
      this.exp -= (this.level * 100);
      this.level++;
      this.maxHp += 20;
      this.hp = this.maxHp;
      this.attackPower += 5;
      System.out.println("LEVEL UP! You are now Level " + this.level + "!");
      System.out.println("Stats Increased: Max HP is now " + this.maxHp + " Attack Power is now " + this.attackPower);
    }
  }

  public void addGold(int amount) {
    this.gold += amount;
    System.out.println("Found " + amount + " gold pieces. (Total Gold: " + this.gold + ")");
  }

  public void displayStatus() {
    System.out.println("\n---[ " + name.toUpperCase() + " STATUS ] ---");
    System.out.println("Level: " + level + " | EXP: " + exp + "/" + (level * 100));
    System.out.println("HP: " + hp + "/" + maxHp);
    System.out.println("Attack Power: " + attackPower);
    System.out.println("Gold: " + gold);
    System.out.println("-".repeat(33));
  }

  public void displayInventory(){
    System.out.println("\n ----- YOUR INVENTORY -----");
    if(inventory.isEmpty()){
      System.out.println("Your backpack is completely empty.");
      return;
    }

    for(int i = 0; i < inventory.size(); i++){
      System.out.println("[" + (i+1) + "] " + inventory.get(i).getName());
    }
  }

  public void useItem(int index){
    if(index >= 0 && index < inventory.size()){
      Item item = inventory.get(index);
      item.use(this);
      inventory.remove(index);
    }else{
      System.out.println("Invalid item selection");
    }
  }

  public void heal(int amount){
    this.hp += amount;

    if(this.hp > this.maxHp){
      this.hp = this.maxHp;
    }

    System.out.println(this.name + " recovered " + amount + " HP! (Current HP: " + this.hp + "/" + this.maxHp + ")");
  }
}
