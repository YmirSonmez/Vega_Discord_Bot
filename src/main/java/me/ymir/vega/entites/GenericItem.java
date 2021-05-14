package me.ymir.vega.entites;

public class GenericItem {

    private int id;
    private final String name;
    private final String desc;
    private final String emoji;


    public GenericItem(int id, String name, String desc, String emoji) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.emoji = emoji;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getEmoji() {
        return emoji;
    }
}
