package me.ymir.vega.commands.game;

import me.ymir.vega.utils.Util;
import me.ymir.vega.commands.CommandAbstract;
import me.ymir.vega.commands.CommandPermissions;
import me.ymir.vega.entites.Items.ResourceItem;
import me.ymir.vega.entites.player.Player;
import me.ymir.vega.entites.storage.ItemStorage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class Sell extends CommandAbstract {
    @Override
    public String getName() {
        return "Sat";
    }

    @Override
    public List<String> getAllies() {
        return Arrays.asList("sell","s");
    }

    @Override
    public String getUsage() {
        return getName()+" (%item id/isim%) (%miktar%)";
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
    protected void execute(GuildMessageReceivedEvent e, Player player, String[] args){
        int money = 0;
        StringBuilder message = new StringBuilder().append(Util.defaultStringFormat);
        if(args.length>2){
            ResourceItem item;
            int count;
            item = ItemStorage.getResource(Util.parseInt(args[1].trim(),0),args[1]);
            count = Util.parseInt(args[2].trim(),0);
            if(item!=null){
                if(player.getInventory().getItemCount(item.getId())>=count){
                    message.append(player.getInventory().sellID(item,count));
                }else{
                    message.append(String.format("**%d** tane satamazsın! Envanterinde bulunan: **%d**",count,player.getInventory().getItemCount(item.getId())));
                }
            }else{
                message.append("Ürün bulunamadı!");
            }

        }else{
            message.append(player.getInventory().sellAll());
        }



        player.giveMoney(money);
        e.getChannel().sendMessage(new EmbedBuilder().setDescription(message.toString()).setThumbnail(e.getAuthor().getAvatarUrl()).setTitle(e.getAuthor().getName()).build()).queue();

    }
}
