package me.ymir.vega.commands;


import me.ymir.vega.commands.fun.CoinFlip;
import me.ymir.vega.commands.fun.Fight;
import me.ymir.vega.commands.fun.GuessNumber;
import me.ymir.vega.commands.game.*;
import me.ymir.vega.commands.guildadmin.SetPrefix;
import me.ymir.vega.database.DbGet;
import me.ymir.vega.database.DbGuild;
import me.ymir.vega.entites.player.Players;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    protected static List<CommandAbstract> commands = new ArrayList<>();
    public static String getPrefix(Guild guild){
        return DbGuild.getGuildPrefix(guild.getId());
    }

    public static void run(GuildMessageReceivedEvent e){
        CommandAbstract commandToRun = null;
        String[] args = e.getMessage().getContentDisplay().trim().split(" ");
        String commandToTry = args[0].replace(getPrefix(e.getGuild()),"").trim().toLowerCase();
        for(CommandAbstract command: commands){
            if(commandToTry.equalsIgnoreCase(command.getName().toLowerCase())||command.getAllies().contains(commandToTry)){
                commandToRun=command;
            }
        }
        if(commandToRun!=null){
            commandToRun.run(e, Players.getPlayer(e.getAuthor().getId()),args);
        }else{
            e.getChannel().sendMessage("Komut bulunamadı!").queue();
        }


    }
    public static void registerCommands(){
        if(commands.isEmpty()){
            registerCommand(new Mine());
            registerCommand(new Start());
            registerCommand(new Inventory());
            registerCommand(new Sell());
            registerCommand(new PickaxeC());
            registerCommand(new JobC());
            registerCommand(new Id());
            registerCommand(new Craft());
            registerCommand(new Furnace());
            registerCommand(new SendResource());
            registerCommand(new SendMoney());
            registerCommand(new CoinFlip());
            registerCommand(new Fight());
            registerCommand(new GuessNumber());
            registerCommand(new SetPrefix());
        }
    }

    private static void registerCommand(CommandAbstract command){
        if(!commands.contains(command)){
            commands.add(command);
        }else{
            System.out.println("Bu komut zaten eklenmiş!");
        }
    }

}
