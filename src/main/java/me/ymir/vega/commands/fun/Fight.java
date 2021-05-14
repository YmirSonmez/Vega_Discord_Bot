package me.ymir.vega.commands.fun;

import me.ymir.vega.utils.FunCommandUtils;
import me.ymir.vega.utils.Util;
import me.ymir.vega.commands.CommandAbstract;
import me.ymir.vega.commands.CommandPermissions;
import me.ymir.vega.entites.player.Player;
import me.ymir.vega.entites.player.Players;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class Fight extends CommandAbstract {
    @Override
    public String getName() {
        return "Dövüş";
    }

    @Override
    public List<String> getAllies() {
        return Arrays.asList("Fight","fgt");
    }

    @Override
    public String getUsage() {
        return getName()+" (%miktar%) <%oyuncu-etiketi%>";
    }

    @Override
    protected boolean isRequiredPlayer() {
        return true;
    }

    @Override
    protected CommandPermissions getPermission() {
        return CommandPermissions.PLAYER;
    }

    @Override
    protected void execute(GuildMessageReceivedEvent e, Player player, String[] args) {
        if(args.length>1&&!e.getMessage().getMentionedMembers().isEmpty()){
            int count = Util.parseInt(args[1],0);
            Player playerToSend = Players.getPlayer(e.getMessage().getMentionedMembers().get(0).getId());
            if(playerToSend != null){
                if(FunCommandUtils.hasInvite(player)){
                    if(FunCommandUtils.getFightObject(player).getPlayer1().equals(playerToSend)){
                        FunCommandUtils.getFightObject(player).ready();
                        return;
                    }else{
                        FunCommandUtils.rejectInvite(player);
                    }
                }
                if(player.getMoney()>=count){
                    player.giveMoney(-count);
                    chat().setArg("Dövüş daveti gönderildi! Katılamk için "+getUsage()+" yazmalı!\n\nDövüş Ücreti: `"+count+"`").success();
                    FunCommandUtils.addFightInvite(playerToSend,player,count,chat());
                }else{
                    chat().setArg(String.format("**%d**TL'ye sahip değilsin!",count)).warning();
                }
            }else{
                chat().setArg("Göndermek istediğiniz kişi oyuncu değil!").warning();
            }
        }else{
            chat().setArg(getUsage()).warning();
        }
    }
}
