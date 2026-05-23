package com.rpg.items;

import com.rpg.entities.Player;

public class Item {
    private String name;
    private String description;
    private int healAmount;

    public Item(String name, String description, int healAmount){
        this.name = name;
        this.description = description;
        this.healAmount = healAmount;
    }

    public void use(Player player){
        System.out.println("\nYou use the " + name + "!");
        player.heal(healAmount);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public int getHealAmount() {
        return healAmount;
    }
}
