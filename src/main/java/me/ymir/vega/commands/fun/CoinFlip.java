package me.ymir.vega.commands.fun;

import me.ymir.vega.utils.Util;
import me.ymir.vega.commands.CommandAbstract;
import me.ymir.vega.commands.CommandPermissions;
import me.ymir.vega.entites.player.Player;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CoinFlip extends CommandAbstract{
    @Override
    public String getName() {
        return "YazıTura";
    }

    @Override
    public List<String> getAllies() {
        return Arrays.asList("cf","yt");
    }

    @Override
    public String getUsage() {
        return getName()+" (%miktar%)";
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
        int count;
        Random random = new Random();
        if(args.length>1){
            count = Util.parseInt(args[1],0);
        }else{
            count = player.getMoney();
        }
        player.giveMoney(-count);

        EmbedBuilder coinFlip = new EmbedBuilder();
        if(count==0){
            chat().setArg("**0TL** yatıramazsın!").warning();
            return;
        }

        coinFlip.setTitle("<a:flip:839202981005230081> Dönüyorr...")
                .setDescription(String.format("**%dTL** yatırdın!",count)).setColor(new Color(0xFFD100));
        e.getChannel().sendMessage(coinFlip.build()).queue(message -> {
            if(random.nextBoolean()){
                player.giveMoney((2*count));
                coinFlip.setTitle(String.format(":coin: **%dTL** kazandın!",(2*count)));
            }else{
                coinFlip.setTitle(":coin: Kaybettin!");
            }
            message.editMessage(coinFlip.build()).queueAfter(3,TimeUnit.SECONDS);
        });

    }
}
