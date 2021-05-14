package me.ymir.vega.utils;

public enum Jobs {
    MINER("Madenci", "Madenci", 2),
    BLACKSMITH("Demirci", "Demirci", 4),
    MELTER("Fırıncı","Fırıncı",2),
    JOBLESS("İşsiz","İşsiz",1);

    private final String name;
    private final String desc;

    private final int toolInventorySize;


    Jobs(String name, String desc, int toolInventorySize) {
        this.name = name;
        this.desc = desc;
        this.toolInventorySize = toolInventorySize;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getToolInventorySize() {
        return toolInventorySize;
    }
}
