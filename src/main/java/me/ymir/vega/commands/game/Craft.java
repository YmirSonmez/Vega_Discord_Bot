package me.ymir.vega.commands.game;

import me.ymir.vega.utils.Jobs;
import me.ymir.vega.utils.Util;
import me.ymir.vega.utils.VegaTimers;
import me.ymir.vega.commands.CommandAbstract;
import me.ymir.vega.commands.CommandPermissions;
import me.ymir.vega.entites.Items.Pickaxe;
import me.ymir.vega.entites.Items.ResourceItem;
import me.ymir.vega.entites.player.Player;
import me.ymir.vega.entites.storage.ItemStorage;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


public class Craft extends CommandAbstract {
    @Override
    public String getName() {
        return "Demirci";
    }

    @Override
    public List<String> getAllies() {
        return Arrays.asList("craft","üretim","ct");
    }

    @Override
    public String getUsage() {
        return getName()+" <üret/bilgi> (%item id/isim%)";
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
        if(player.getJob().equals(Jobs.BLACKSMITH)){
            if(args.length>2){
                if(VegaTimers.progressCraft(player)){
                    chat().setArg("**"+VegaTimers.progressCraftTime(player)+"** tarihine kadar meşgulüm! Sonra gel...").craftError();
                }else{
                    int id = Util.parseInt(args[2],0);
                    ResourceItem item = ItemStorage.getResources().get(id).isCanUseCraft() ? ItemStorage.getResource(id,args[2]) : null;
                    if(args[1].equalsIgnoreCase("üret")&& item !=null){
                        if(player.getInventory().getPickaxesSize()+1>player.getJob().getToolInventorySize()){
                            chat().setArg("Kazma için envanterinde yer yok!").warning();
                            return;
                        }
                        if(player.getInventory().getItemCount(id)<50){
                            chat().setArg(" Üretim için yeterli eşyaya sahip değilsin! Gereken: **50**").warning();
                            return;
                        }
                        player.getInventory().removeItem(item,50);
                        Random random = new Random();
                        int defaultId = 100;
                        int defaultSpeed = 25;
                        int defaultChance = 5;
                        int defaultDurability = 200;
                        String defaultName = item.getName()+" Kazma";
                        String defaultDesc = "Yıldırımlar tarafından dövüldü!";
                        int defaultTime = 1;
                        String emoji = ":x:";



                        switch (item.getId()){
                            case 1:
                                defaultSpeed -= random.nextInt(5);
                                defaultChance += random.nextInt(20);
                                defaultDurability += random.nextInt(200);
                                defaultTime += random.nextInt(1);
                                emoji = "<:stonepicaxe:838177515935367168>";
                                break;
                            case 5:
                                defaultSpeed -= random.nextInt(10);
                                defaultChance += random.nextInt(40);
                                defaultDurability += random.nextInt(350);
                                defaultTime += random.nextInt(2);
                                emoji = "<:ironpicaxe:838177142348840990>";
                                break;
                            case 7:
                                defaultSpeed -= random.nextInt(15);
                                defaultChance += random.nextInt(60);
                                defaultDurability += random.nextInt(450);
                                defaultTime += random.nextInt(3);
                                emoji = "<:goldpicaxe:838177142147776533>";
                                break;
                            case 9:
                                defaultSpeed -= random.nextInt(20);
                                defaultChance += random.nextInt(80);
                                defaultDurability += random.nextInt(550);
                                defaultTime += random.nextInt(4);
                                emoji = "<:diamondpicaxe:838177142382526494>";
                                break;
                        }

                        Calendar date = Calendar.getInstance();
                        date.add(Calendar.DAY_OF_YEAR,defaultTime);

                        player.addPendingPickaxe(date.getTime(),new Pickaxe(defaultId,defaultName,emoji,defaultDesc,id,defaultDurability,defaultDurability,defaultSpeed,defaultChance));
                    }
                }
            }else if(args.length==2&& args[1].equalsIgnoreCase("bilgi")){
                    chat().setArg("Yakında...").info();
            }else{
                chat().setArg(getUsage()).warning();
            }
        }else{
            chat().setArg("Yalnızca Demirci mesleğine sahip kişiler kullanabilir!").warning();
        }
    }
}
