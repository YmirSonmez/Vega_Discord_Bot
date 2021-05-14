package me.ymir.vega.entites.player;


import me.ymir.vega.database.DbUpdate;
import me.ymir.vega.entites.Items.Pickaxe;

import java.util.HashMap;

public class Players {
    private static final HashMap<String, Player> players = new HashMap<>();

    public static Player getPlayer(String id){return players.get(id);}

    public static Player newPlayer(String id){
        Player player;
        if(getPlayer(id)==null) {
            player = new Player(id);
            players.put(player.getId(), player);
            DbUpdate.insert(player);
            player.getInventory().addPickaxe(new Pickaxe(100,"Oyuncu Kazması","","Yeni başlayan oyuncular için bir kazma!",1,200,200,3,5));
            System.out.println("Yeni oyuncu eklendi: " + player.getId());
        }else{player=getPlayer(id);}

        return player;
    }

    public static void addPlayerFromDb(String id,int level,int xp,int nextLevelXp,int money,String job,String seriliazed,String craft){
        Player player = new Player(id,level,xp,nextLevelXp,money,job,seriliazed,craft);
        players.put(id,player);
    }


}
