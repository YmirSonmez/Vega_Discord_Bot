package me.ymir.vega.commands.game;

import me.ymir.vega.utils.Jobs;
import me.ymir.vega.utils.Util;
import me.ymir.vega.commands.CommandAbstract;
import me.ymir.vega.commands.CommandPermissions;
import me.ymir.vega.entites.Items.ResourceItem;
import me.ymir.vega.entites.player.Player;
import me.ymir.vega.entites.storage.ItemStorage;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class Furnace extends CommandAbstract {
    @Override
    public String getName() {
        return "Fırın";
    }

    @Override
    public List<String> getAllies() {
        return Arrays.asList("firin","furnace","f");
    }

    @Override
    public String getUsage() {
        return getName()+" <%item id/isim%>";
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
        if(player.getJob().equals(Jobs.MELTER)){
            if(args.length>2){
                ResourceItem item = ItemStorage.getResource(Util.parseInt(args[1].trim(),0),args[1]);
                int count = Util.parseInt(args[2].trim(),0);
                if(item.getId() != 0){
                    if(player.getInventory().getItemCount(item.getId())>=count && player.getInventory().getItemCount(3)>=count){
                        player.getInventory().removeItem(item,count).removeItem(ItemStorage.getResource(3,""),count).addResource(item.getMelted().getId(),count);
                        chat().setArg(String.format("**%d** tane %s erittin! **»** **%d** %s",count,item.getPrettyName(),count,item.getMelted().getPrettyName())).furnace();
                    }
                }else{
                    chat().setArg("Bu itemi eritebilecek sıcaklığa sahip değiliz!").furnace();
                }
            }else{
                chat().setArg(getUsage()).warning();
            }
        }else{
            chat().setArg("Yalnızca **Fırıncı** mesleğine sahip kişiler kullanabilir!").warning();
        }
    }
}
