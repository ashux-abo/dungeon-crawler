package com.rpg.entities;

public class Skeleton extends Entity{
    private int level;

    public Skeleton (String name){
        super(name, 40, 40, 18);
        this.level = 1;
    }

    @Override
    public void attack(Entity target) {
        System.out.println("\n>>> " + this.name + " rattles its bones and lunges at " + target.getName() + "!");

        int formalDamage = this.attackPower;
        if(Math.random() < 0.15){
            formalDamage += 7;
            System.out.println("CRIT! the rusted blade pierces deep!");
        }
        target.takeDamage(formalDamage);
    }

    public void displayStatus() {
        System.out.println("\n--- [ " + name.toUpperCase() + " STATUS ] ---");
        System.out.println("Level: " + level);
        System.out.println("HP: " + hp + "/" + maxHp);
        System.out.println("Attack Power: " + attackPower);
        System.out.println("---------------------------");
    }
}
