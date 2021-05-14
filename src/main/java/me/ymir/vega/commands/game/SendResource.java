package me.ymir.vega.commands.game;

import me.ymir.vega.utils.Util;
import me.ymir.vega.commands.CommandAbstract;
import me.ymir.vega.commands.CommandPermissions;
import me.ymir.vega.entites.Items.ResourceItem;
import me.ymir.vega.entites.player.Player;
import me.ymir.vega.entites.player.Players;
import me.ymir.vega.entites.storage.ItemStorage;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class SendResource extends CommandAbstract {
    @Override
    public String getName() {
        return "Gönder";
    }

    @Override
    public List<String> getAllies() {
        return Arrays.asList("send","yolla");
    }

    @Override
    public String getUsage() {
        return getName()+"<%eşya id/isim%> <%miktar%> <%oyuncu-etiketi%>";
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
        if(args.length>3&&!e.getMessage().getMentionedMembers().isEmpty()){
            ResourceItem item = ItemStorage.getResource(Util.parseInt(args[1],0),args[1]);
            int count = Util.parseInt(args[2],0);
            Player playerToSend = Players.getPlayer(e.getMessage().getMentionedMembers().get(0).getId());
            if(playerToSend != null){
                if(player.getInventory().getItemCount(item.getId())>=count){
                    player.getInventory().removeItem(item,count);
                    playerToSend.getInventory().addResource(item.getId(),count);
                    chat().setArg(String.format("**%d** tane %s başarıyla %s'a gönderildi!",count,item.getPrettyName(),e.getMessage().getMentionedMembers().get(0).getAsMention())).success();
                }else{
                    chat().setArg(String.format("**%d** tane %s'e sahip değilsin!",count,item.getPrettyName())).warning();
                }
            }else{
                chat().setArg("Göndermek istediğiniz kişi oyuncu değil!").warning();

            }
        }else{
            chat().setArg(getUsage()).warning();
        }
    }
}
