package me.ymir.vega.commands.game;

import me.ymir.vega.utils.Util;
import me.ymir.vega.commands.CommandAbstract;
import me.ymir.vega.commands.CommandPermissions;
import me.ymir.vega.entites.Items.Pickaxe;
import me.ymir.vega.entites.player.Player;
import me.ymir.vega.entites.player.Players;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class PickaxeC extends CommandAbstract {
    @Override
    public String getName() {
        return "Kazma";
    }

    @Override
    public List<String> getAllies() {
        return Arrays.asList("p");
    }

    @Override
    public String getUsage() {
        return getName()+" <kullan/gönder/sil> (%kazma-id%) (%oyuncu-etiketi%)";
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
        }else{
            if(args.length<3){
                if(args[1].trim().equalsIgnoreCase("kullan")&&!player.getInventory().getPickaxes().isEmpty()){
                    player.setUsedPickaxe(player.getInventory().getPickaxes().get(0));
                    chat().setArg("Rastgele bir kazmanız kullanıldı!").success();
                }else{
                    chat().setArg(getUsage()).warning();
                }
            }else{
                int id = Util.parseInt(args[2].trim(),0);
                Pickaxe chosenPickaxe = null;
                for(Pickaxe pickaxe:player.getInventory().getPickaxes()){
                    if(id==pickaxe.getId()){
                        chosenPickaxe=pickaxe;
                    }
                }
                if(chosenPickaxe==null){
                    chat().setArg("Bu kazmaya sahip değilsin! **ID: **"+id).warning();
                }else{
                    switch (args[1]){
                        case "kullan":
                            player.setUsedPickaxe(chosenPickaxe);
                            chat().setArg("Kazma kullanıldı! "+chosenPickaxe.getName()).success();
                            break;
                        case "sil":
                            player.getInventory().deletePickaxe(chosenPickaxe);
                            chat().setArg("Kazma silindi! "+chosenPickaxe.getName()).success();
                            break;
                        case "gönder":
                            if(!e.getMessage().getMentionedMembers().isEmpty()&& Players.getPlayer(e.getMessage().getMentionedMembers().get(0).getId())!=null){
                                Player playerToSend = Players.getPlayer(e.getMessage().getMentionedMembers().get(0).getId());
                                if(playerToSend.getJob().getToolInventorySize()>playerToSend.getInventory().getPickaxes().size()){
                                    player.getInventory().deletePickaxe(chosenPickaxe);
                                    playerToSend.getInventory().addPickaxe(chosenPickaxe);
                                    chat().setArg("Kazma başarıyla " +playerToSend.getMention()+"'a gönderildi! **ID: **"+id).success();

                                }else{
                                    chat().setArg(playerToSend.getMention()+"'in kazma envanteri dolu!").warning();
                                }

                            }else{
                                chat().setArg("Gönderilecek oyuncu bulunamadı!").warning();
                            }
                            break;
                    }
                }
            }
        }

    }
}
