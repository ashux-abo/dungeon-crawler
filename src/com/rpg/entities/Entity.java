package com.rpg.entities;

public abstract class Entity {
  protected String name;
  protected int hp;
  protected int maxHp;
  protected int attackPower;

  public Entity(String name, int hp, int maxHp, int attackPower) {
    this.name = name;
    this.hp = hp;
    this.maxHp = maxHp;
    this.attackPower = attackPower;
  }

  public void takeDamage(int damage) {
    this.hp -= damage; 
    if (this.hp < 0) this.hp = 0;
    System.out.println(name + " takes " + damage + " damge! (HP: " + hp + "/" + maxHp + ")");
  }

  public boolean isAlive(){
    return this.hp > 0;
  }

  public abstract void attack(Entity target);

  public String getName() {return name;}
  public int getHp() {return hp;}
  public int getMaxHp() {return maxHp; }
  public  int getAttackPower() {return  attackPower;}
}
