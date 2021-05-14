package me.ymir.vega.entites.player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.ymir.vega.entites.Items.Pickaxe;
import me.ymir.vega.entites.Items.ResourceItem;
import me.ymir.vega.entites.storage.ItemStorage;
import net.dv8tion.jda.api.EmbedBuilder;


import java.util.HashMap;
import java.util.List;

public class PlayerInventory {

    private final Player player;
    private final HashMap<ResourceItem, Integer> resourceInv;
    private final List<Pickaxe> pickaxes;

    public PlayerInventory(Player player,HashMap<ResourceItem, Integer> resourceInv,List<Pickaxe> pickaxes) {
        this.player = player;
        this.pickaxes = pickaxes;
        this.resourceInv = resourceInv;
    }



    public PlayerInventory addResource(int id,int number){
        ResourceItem item = ItemStorage.getResource(id,"");
        if(!this.resourceInv.containsKey(item)){
            this.resourceInv.put(item,number);
        }else{
            this.resourceInv.replace(item,getItemCount(id)+number);
        }
        this.player.serialize();
        return this;}



    private HashMap<String,Integer> getInventoryItems(){
        return null;
    }


    public String sellAll(){
        StringBuilder s = new StringBuilder();
        this.resourceInv.forEach((k,v)->{
            if(v!=0){
                s.append("\n"+sellID(k,v));
            }
        });
        return s.toString();
    }

    public String sellID(ResourceItem item,int i){
        int money = item.getSellPrice()*i;
        int newCount = this.resourceInv.get(item)-i;
        this.resourceInv.replace(item, newCount);
        this.player.serialize();
        player.giveMoney(money);
        return String.format((item.getEmoji()+" **%d** adet **%s** satıldı! **%dTL**"),i,item.getName(),money);
    }

    public List<Pickaxe> getPickaxes(){
        return this.pickaxes;
    }

    public int getPickaxesSize(){
        return this.pickaxes.size()+(player.hasPendingPickaxe()?1:0);
    }




    public EmbedBuilder toEmbed(){
        EmbedBuilder inventory = new EmbedBuilder();
        StringBuilder resources = new StringBuilder();
        StringBuilder pickaxes = new StringBuilder();
        this.resourceInv.forEach((item,count)->{
            if(count!=0){
                resources.append(String.format("\n%s » %d",item.getPrettyName(),count));
            }
        });
        this.pickaxes.forEach((p)->{
            pickaxes.append("\n"+ItemStorage.itemInfo(p.getId(),""));
        });
        inventory.setDescription("**KAYNAKLAR**\n" + resources.toString()).addField("**KAZMALAR**",pickaxes.toString(),true);

        return inventory;
    }

    public PlayerInventory addPickaxe(Pickaxe pickaxe){
        this.pickaxes.add(pickaxe);
        ItemStorage.addPickaxe(pickaxe);
        this.player.serialize();
        return this;
    }
    public PlayerInventory deletePickaxe(Pickaxe pickaxe){
        if(player.getUsedPickaxe()==pickaxe){
            player.setUsedPickaxe(null);
        }
        ItemStorage.removePickaxe(pickaxe);
        this.pickaxes.remove(pickaxe);
        this.player.serialize();
        return this;
    }

    public PlayerInventory removeItem(ResourceItem item,int count){
        int i = getItemCount(item.getId())-count;
        if(i<=0){
            this.resourceInv.remove(item);
        }else {
            this.resourceInv.replace(item,i);
        }
        this.player.serialize();
        return this;
    }

    public int getItemCount(int id){
        if(!resourceInv.containsKey(ItemStorage.getResource(id,""))){
            return 0;
        }
        return this.resourceInv.get(ItemStorage.getResource(id,""));
    }

    protected StringBuilder serializeInv(){
        StringBuilder inv = new StringBuilder();
        if(!this.resourceInv.isEmpty()){
            inv.append("RESOURCE");
            this.resourceInv.forEach((k, v)->{
                inv.append(String.format("%d:%d,",k.getId(),v));
            });
            inv.append("#");
        }
        if(!this.pickaxes.isEmpty()){
            inv.append("PICKAXE");
            for(Pickaxe pickaxe: this.pickaxes){
                inv.append(new GsonBuilder().disableHtmlEscaping().create().toJson(pickaxe)+"%");
            }
        }
        return inv;
    }

}
