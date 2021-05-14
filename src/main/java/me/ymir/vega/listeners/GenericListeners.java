package me.ymir.vega.listeners;





import me.ymir.vega.commands.CommandManager;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class GenericListeners extends ListenerAdapter {


    public void onReady(ReadyEvent e){

    }


    public void onGuildMessageReceived(GuildMessageReceivedEvent e){
        if(!e.getAuthor().isBot()){
            if(e.getMessage().getContentDisplay().startsWith(CommandManager.getPrefix(e.getGuild()))){
                CommandManager.run(e);
            }
        }
    }




}
