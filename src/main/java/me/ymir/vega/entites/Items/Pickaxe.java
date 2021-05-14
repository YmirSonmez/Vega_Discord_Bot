package me.ymir.vega.entites.Items;

import me.ymir.vega.entites.GenericItem;

public class Pickaxe extends GenericItem {

    private final int resource;
    private final int maxDurability;
    private int durability;

    private final int speed;
    private final int chance;

    public Pickaxe(int id, String name,String emoji,String desc, int resource, int maxDurability, int durability, int speed, int chance) {
        super(id, name, desc, emoji);
        this.resource = resource;
        this.maxDurability = maxDurability;
        this.durability = durability;
        this.speed = speed;
        this.chance = chance;
    }

    public int getResource() {
        return resource;
    }

    public int getMaxDurability() {
        return maxDurability;
    }

    public int getDurability() {
        return durability;
    }

    public int getSpeed() {
        return speed;
    }

    public int getChance() {
        return chance;
    }

    public Pickaxe giveDamage(int i){
        this.durability -= i;
        return this;
    }

}
