package me.ymir.vega.commands.game;

import me.ymir.vega.commands.CommandAbstract;
import me.ymir.vega.commands.CommandPermissions;
import me.ymir.vega.entites.player.Player;
import me.ymir.vega.entites.player.Players;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Collections;
import java.util.List;

public class Start extends CommandAbstract {
    @Override
    public String getName() {
        return "Başla";
    }

    @Override
    public List<String> getAllies() {
        return Collections.singletonList("start");
    }

    @Override
    public String getUsage() {
        return getName();
    }

    @Override
    protected boolean isRequiredPlayer() {
        return false;
    }

    @Override
    protected CommandPermissions getPermission() {
        return CommandPermissions.PLAYER;
    }

    @Override
    protected void execute(GuildMessageReceivedEvent e, Player player, String[] args) {
        if(player==null){
            Player player1 = Players.newPlayer(e.getAuthor().getId());
            chat().setArg("Hesabınız oluşturuldu! Hemen kazmaya başlayabilirsin! "+e.getAuthor().getAsMention()).info();
        }else{
            chat().setArg("Zaten bir hesabın var!").warning();
        }
    }
}
