package me.ymir.vega;



import me.ymir.vega.commands.CommandManager;
import me.ymir.vega.database.DbGet;
import me.ymir.vega.database.DbGuild;
import me.ymir.vega.entites.storage.ItemStorage;
import me.ymir.vega.listeners.GenericListeners;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Core {
    private static void Bot(String token) throws LoginException {
        JDABuilder.createDefault(token,GatewayIntent.GUILD_MESSAGES).addEventListeners(new GenericListeners()).build();

    }
    public static void main(String[] args) throws LoginException {
        Bot("token");
        ItemStorage.updateList();
        CommandManager.registerCommands();
        DbGet.getPlayers();
        DbGuild.getGuildsPrefix();
    }


}
