package me.ymir.vega.commands.fun;

import me.ymir.vega.utils.Util;
import me.ymir.vega.commands.CommandAbstract;
import me.ymir.vega.commands.CommandPermissions;
import me.ymir.vega.entites.player.Player;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GuessNumber extends CommandAbstract {
    @Override
    public String getName() {
        return "Tahmin";
    }

    @Override
    public List<String> getAllies() {
        return Arrays.asList("guess","t");
    }

    @Override
    public String getUsage() {
        return getName() +" <%Miktar%> <%0-20%>";
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
        if(args.length>2){
            int count = Util.parseInt(args[1],0);
            int guess = Util.parseInt(args[2],0);
            int number = new Random().nextInt(21);
            if(player.getMoney()<count){
                chat().setArg(String.format("**%dTL**'ye sahip değilsin!",player.getMoney())).warning();
            }else{
                EmbedBuilder gn = new EmbedBuilder().setTitle("<:think:839430982893568070> Aklımdaki sayı...").addField("Tahmin ettiğin sayı: "+guess,"",false).setFooter(e.getAuthor().getAsTag(),e.getAuthor().getAvatarUrl());
                player.giveMoney(-count);
                e.getChannel().sendMessage(gn.build()).queue(message -> {
                    if(guess==number){
                        player.giveMoney((int) (1.5*count));
                        gn.setTitle(":tada: Sen büyücüsün!").setDescription("\nTuttuğum sayıyı doğru bildin! İşte ödülün: **"+(int)(1.5*count)+"TL**\n");
                    }else{
                        gn.setTitle(":disappointed: Bilemedin!").setDescription("\nTuttuğum sayı **"+number+"'di**!\n");
                    }
                    message.editMessage(gn.build()).queueAfter(3, TimeUnit.SECONDS);
                });

            }

        }else{
            chat().setArg(getUsage()).warning();
        }

    }
}
