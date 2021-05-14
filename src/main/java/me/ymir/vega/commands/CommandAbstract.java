package me.ymir.vega.commands;

import me.ymir.vega.utils.ChatManager;
import me.ymir.vega.entites.player.Player;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public abstract class CommandAbstract {

    private GuildMessageReceivedEvent e;
    private Player player;
    private ChatManager chat;


    public void run(GuildMessageReceivedEvent e, Player player, String[] args){
        this.e = e;
        this.player = player;
        this.chat = new ChatManager();
        if(checkPlayerRequired()&&checkPermission()){
            chat().set(e.getChannel(),e.getAuthor());
            execute(e,player,args);
        }
    }

    public abstract String getName();

    public abstract List<String> getAllies();

    public abstract String getUsage();

    protected abstract boolean isRequiredPlayer();

    protected abstract CommandPermissions getPermission();

    private boolean checkPermission(){
        StringBuilder message = new StringBuilder().append("");
        switch (getPermission()){
            case ADMIN:
                if(!CommandPermissions.ADMIN.getAdminIDs().contains(e.getAuthor().getId())){
                    message.append("Yalnızca **bot sahibi** kullanabilir!");
                }
                break;
            case PLAYER:
                break;
            case GUILD_OWNER:
                if(!e.getMember().hasPermission(CommandPermissions.GUILD_OWNER.getPermission())){
                    message.append("Yalnızca **sunucu sahibi** kullanabilir!");
                }
                break;
        }
        if(!message.toString().equalsIgnoreCase("")){
            this.chat.set(e.getChannel(),e.getAuthor()).setArg(message.toString()).warning();
            return false;
        }
        return true;

    }

    private boolean checkPlayerRequired(){
        if(this.isRequiredPlayer()){
            if(this.player==null){
                this.chat.set(e.getChannel(),e.getAuthor()).setArg("Oyuna başlamadan kullanamazsınız!").warning();
                return false;
            }
            player.getWarning(e.getChannel());
        }
        return true;
    }

    protected abstract void execute(GuildMessageReceivedEvent e, Player player,String[] args);

    protected ChatManager chat(){return this.chat;}


}
