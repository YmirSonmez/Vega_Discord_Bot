package me.ymir.vega.database;


import me.ymir.vega.entites.player.Player;

import java.sql.SQLException;

public class DbUpdate {

    public static void update(String execute){
        try {
            DbGet.connect.getConnection().createStatement().execute(execute);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public static void insert(Player player){
        try {
            DbGet.connect.getConnection().createStatement().execute(String.format("INSERT INTO `vega`.`players` (`id`, `level`, `xp`, `next_level_xp`, `money`, `job`, `inventory`, `craft`) VALUES ('%s', '%d', '%d', '%d', '%d', '%s','%s', 'a')",
                    player.getId(),player.getLevel(),player.getXp(),player.getNextLevelXp(),player.getMoney(),"jobless","empty","a"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
