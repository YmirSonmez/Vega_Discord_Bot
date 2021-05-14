package me.ymir.vega.commands.game;

import me.ymir.vega.utils.Util;
import me.ymir.vega.commands.CommandAbstract;
import me.ymir.vega.commands.CommandPermissions;
import me.ymir.vega.entites.player.Player;
import me.ymir.vega.entites.storage.ItemStorage;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class Id extends CommandAbstract {
    @Override
    public String getName() {
        return "Item";
    }

    @Override
    public List<String> getAllies() {
        return Arrays.asList("İtem","id","ıd");
    }

    @Override
    public String getUsage() {
        return getName()+" <%ID/isim%>";
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
        if(args.length>1){
            chat().setArg(ItemStorage.itemInfo(Util.parseInt(args[1].trim(),0),args[1])).info();
        }else{
            chat().setArg(getUsage()).warning();
        }
    }
}
