package me.ymir.vega.commands.game;

import me.ymir.vega.utils.Jobs;
import me.ymir.vega.commands.CommandAbstract;
import me.ymir.vega.commands.CommandPermissions;
import me.ymir.vega.entites.player.Player;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Collections;
import java.util.List;

public class JobC extends CommandAbstract {
    @Override
    public String getName() {
        return "Meslek";
    }

    @Override
    public List<String> getAllies() {
        return Collections.singletonList("job");
    }

    @Override
    public String getUsage() {
        return getName()+" (bilgi) (%meslek%)";
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
        if(args.length<2){
            chat().setArg(getUsage()).warning();
            return;
        }
        StringBuilder message = new StringBuilder();
        if(args.length>2&& args[1].trim().equalsIgnoreCase("bilgi")){
            switch (args[2].trim().toLowerCase()){
                case "madenci":
                    message.append(String.format("**MADENCİ** \n**Kazma Sayısı:** %d\n%s",Jobs.MINER.getToolInventorySize(),Jobs.MINER.getDesc()));
                    break;
                case "demirci":
                    message.append(String.format("**DEMİRCİ** \n**Kazma Sayısı:** %d\n%s",Jobs.BLACKSMITH.getToolInventorySize(),Jobs.BLACKSMITH.getDesc()));
                    break;
                case "fırıncı":
                    message.append(String.format("**FIRINCI** \n**Kazma Sayısı:** %d\n%s",Jobs.MELTER.getToolInventorySize(),Jobs.MELTER.getDesc()));
                    break;
                default:
                    message.append(String.format("**İŞSİZ** \n**Kazma Sayısı:** %d\n%s",Jobs.JOBLESS.getToolInventorySize(),Jobs.JOBLESS.getDesc()));
                    break;
            }
        }else if(player.getJob().equals(Jobs.JOBLESS)){
            switch (args[1].trim().toLowerCase()){
                case "madenci":
                    player.setJob(Jobs.MINER);
                    message.append("Yeni mesleğiniz **Madenci**!");
                    break;
                case "demirci":
                    player.setJob(Jobs.BLACKSMITH);
                    message.append("Yeni mesleğiniz **Demirci**!");
                    break;
                case "fırıncı":
                    player.setJob(Jobs.MELTER);
                    message.append("Yeni mesleğiniz **Fırıncı**!");
                    break;
                default:
                    message.append("**Madenci**,**Demirci** yada **Fırıncı** olabilirsin!");
                    break;
            }
        }
        chat().setArg(message.toString()).info();
    }
}
