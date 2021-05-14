package me.ymir.vega.database;

import me.ymir.vega.entites.player.Players;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbGet {
    protected static Connect connect;

    public static void getPlayers(){
        connect = new Connect();
        try {
            ResultSet result = connect.getStatement().executeQuery("select * from players");
            while (result.next()){
                Players.addPlayerFromDb(result.getString("id"),
                        result.getInt("level"),
                        result.getInt("xp"),
                        result.getInt("next_level_xp"),
                        result.getInt("money"),
                        result.getString("job"),
                        result.getString("inventory"),
                        result.getString("craft"));

            }
            result.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }




}
