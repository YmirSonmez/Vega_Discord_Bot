package me.ymir.vega.database;

import me.ymir.vega.entites.player.Players;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DbGuild {
    public final static HashMap<String,String> GUILD_PREFIX = new HashMap<>();

    public static String getGuildPrefix(String guildID){
        if(GUILD_PREFIX.containsKey(guildID)){
            return GUILD_PREFIX.get(guildID);
        }else{
            return "-";
        }
    }

    public static void setGuildPrefix(String guildID,String prefix){
        if(GUILD_PREFIX.containsKey(guildID)){
            DbUpdate.update(String.format("UPDATE `vega`.`guild` SET `prefix` = '%s' WHERE (`guild_id` = '%s');\n",prefix,guildID));
            GUILD_PREFIX.remove(guildID);
            GUILD_PREFIX.put(guildID,prefix);
        }else{
            DbUpdate.update(String.format("INSERT INTO `vega`.`guild` (`guild_id`, `prefix`) VALUES ('%s', '%s')",guildID,prefix));
            GUILD_PREFIX.put(guildID,prefix);
        }
    }
    public static void getGuildsPrefix(){
        try {
            ResultSet result = DbGet.connect.getStatement().executeQuery("select * from guild");
            while (result.next()){
                GUILD_PREFIX.put(result.getString("guild_id"),result.getString("prefix"));
            }
            result.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
