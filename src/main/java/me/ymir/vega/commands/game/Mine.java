package me.ymir.vega.commands.game;


import me.ymir.vega.utils.VegaTimers;
import me.ymir.vega.commands.CommandAbstract;
import me.ymir.vega.commands.CommandPermissions;
import me.ymir.vega.entites.player.Player;
import me.ymir.vega.entites.storage.ItemStorage;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Arrays;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Mine extends CommandAbstract {

    @Override
    public String getName() {
        return "Kaz";
    }

    @Override
    public List<String> getAllies() {
        return Arrays.asList("m","mine","k");
    }

    @Override
    public String getUsage() {
        return getName();
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
        if(VegaTimers.getMiningDelay(player)>0){
            chat().setArg(VegaTimers.getMiningDelay(player) + " saniye beklemelisin!").mineDelay();
            return;
        }
        StringBuilder message = new StringBuilder().append("Kaz kaz kaz...");
        Random random = new Random();
        AtomicInteger i = new AtomicInteger();
        AtomicInteger total = new AtomicInteger();
        ItemStorage.getResources().forEach((k,v)->{
            if(v.getChance()!=0 && player.getLevel()>=v.getRequiredLevel()){
                i.set(random.nextInt(v.getChance()));
                total.addAndGet(i.get());
                if(player.getUsedPickaxe()!=null){
                    if(player.getUsedPickaxe().getChance()>random.nextInt(101)){
                        i.addAndGet(i.get());
                    }
                }
                player.getInventory().addResource(k,i.get());
                if(i.get()!=0){
                    message.append(String.format("\n**%d** tane %s kazdın!",i.get(),v.getPrettyName()));
                }
            }


        });

        int xpk = random.nextInt(10);
        int xp = 10*(xpk != 0 ? xpk : 5);
        message.append(String.format("\n\n**%dXP** kazandın!",xp));
        player.addXP(xp);
        if(total.get()==0){
            player.getInventory().addResource(1,10);
            message.append(String.format("\n**%d** tane %s kazdın!",10,ItemStorage.getResource(1,"").getPrettyName()));
        }
        if(player.getUsedPickaxe()!=null){
            message.append(String.format("\n\n %s hasar aldın **»** %d `%d --> %d`",player.getUsedPickaxe().getName(),total.get()/2,player.getUsedPickaxe().getDurability(),player.getUsedPickaxe().giveDamage(total.get()/2).getDurability()));
            player.getUsedPickaxe().giveDamage(total.get()/2);
        }
        VegaTimers.addMiningDelay(player.getUsedPickaxe() != null ? player.getUsedPickaxe().getSpeed() : 30,player);
        chat().setArg(message.toString()).mine();
    }


}
