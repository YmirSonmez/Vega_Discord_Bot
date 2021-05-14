package me.ymir.vega.entites.Items;

import me.ymir.vega.entites.GenericItem;

public class ResourceItem extends GenericItem {
    private final int buyPrice;
    private final int sellPrice;
    private final int requiredLevel;
    private final int chance;
    private final boolean canUseCraft;
    private final ResourceItem melted;



    public ResourceItem(int id, String name, String desc,String emote ,int buyPrice, int sellPrice, int requiredLevel, int chance, boolean canUseCraft, ResourceItem melted) {
        super(id, name, desc, emote);
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.requiredLevel = requiredLevel;
        this.chance = chance;
        this.canUseCraft = canUseCraft;
        this.melted = melted;
    }


    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public int getChance() {
        return chance;
    }

    public boolean isCanUseCraft() {
        return canUseCraft;
    }

    public ResourceItem getMelted() {
        return melted;
    }

    public String getPrettyName(){return this.getEmoji()+"**"+this.getName()+"**";}
}
