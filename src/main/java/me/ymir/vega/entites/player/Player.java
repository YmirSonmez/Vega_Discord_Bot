package me.ymir.vega.entites.player;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.ymir.vega.utils.Jobs;
import me.ymir.vega.utils.Util;
import me.ymir.vega.utils.VegaTimers;
import me.ymir.vega.database.DbUpdate;
import me.ymir.vega.entites.Items.Pickaxe;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

import java.text.ParseException;
import java.util.*;

public class Player {



    //Player main data
    private final String id;
    private int level;
    private int xp;
    private int nextLevelXp;
    private int money;
    private Jobs job;



    //inventory
    private final PlayerInventory inventory;
    private Pickaxe usedPickaxe;
    private Pickaxe pendingPickaxe;
    private String warning;

    public Player(String id) {
        this.id = id;
        this.level =1;
        this.xp = 0;
        this.nextLevelXp = 50;
        this.money = 2000;
        setJob(Jobs.JOBLESS);
        this.inventory = new PlayerInventory(this,new HashMap<>(),new ArrayList<>());
        warning = "Bu uyarıyı ilk kez kayıt olduğun için alıyorsun! Herhangi bir bildiriniz olduğunda komut kullanırken bunun gibi uyarı alacaksınız.\n Kazmanızı kullanmayı unutmayın!";
    }

    public Player(String id,int level,int xp,int nextLevelXp,int money,String job,String serialized,String craft){
        this.id = id;
        this.level = level;
        this.xp = xp;
        this.nextLevelXp = nextLevelXp;
        this.money = money;
        this.inventory = new PlayerInventory(this,new HashMap<>(),new ArrayList<>());
        deserialize(serialized,job,craft);
    }

    public PlayerInventory getInventory(){
        return this.inventory;
    }



    public Player giveMoney(Integer val1){
        this.money += val1;
        DbUpdate.update(String.format("UPDATE `vega`.`players` SET `money` = '%d' WHERE (`id` = '%s')",this.money,this.id));
        return this;
    }




    public Player addXP(int xp){
        this.xp += xp;
        if(this.xp>this.nextLevelXp){
            ++this.level;
            this.xp -= this.nextLevelXp;
            this.nextLevelXp = (this.level+1)*(this.nextLevelXp/2);
        }
        DbUpdate.update(String.format("UPDATE `vega`.`players` SET `level` = '%d', `xp` = '%d', `next_level_xp` = '%d' WHERE (`id` = '%s')",this.level,this.xp,this.nextLevelXp,this.id));
        return this;
    }




    public Player addPendingPickaxe(Date date,Pickaxe pickaxe){
        this.pendingPickaxe = pickaxe;
        if(date.getTime()<=new Date().getTime()){
            finishPending();
        }else{
            VegaTimers.addCraft(this,date);
            DbUpdate.update(String.format("UPDATE `vega`.`players` SET `craft` = '%s' WHERE (`id` = '%s')",serializeCraft(),this.id));
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    finishPending();
                }
            }, date);
        }
        return this;
    }
    public Player finishPending(){
        inventory.addPickaxe(pendingPickaxe);
        pendingPickaxe=null;
        setWarning("Üretilen kazman envanterine eklendi!");
        VegaTimers.removeCraft(this);
        DbUpdate.update(String.format("UPDATE `vega`.`players` SET `craft` = '%s' WHERE (`id` = '%s')","a",this.id));
        return this;
    }


    public void deserialize(String serialized,String job,String craft){
        String[] args = serialized.split("#");

        for(String arg: args){
            if(arg.startsWith("RESOURCE")){
                String[] newArgs = arg.replace("RESOURCE","").split(",");
                for(String a: newArgs){
                    if(!a.equalsIgnoreCase("")){
                        String[] item = a.split(":");
                        this.inventory.addResource(Util.parseInt(item[0],0),Util.parseInt(item[1],0));
                    }
                }
            }else if (arg.startsWith("PICKAXE")){
                String[] newArgs = arg.replace("PICKAXE","").split("%");
                for(String a: newArgs){
                    if(!a.equalsIgnoreCase("")){
                        this.inventory.addPickaxe(new Gson().fromJson(a,Pickaxe.class));
                    }}
            }
        }
        switch (job){
            case "miner":
                this.job=Jobs.MINER;
                break;
            case "blacksmith":
                this.job=Jobs.BLACKSMITH;
                break;
            case "melter":
                this.job =Jobs.MELTER;
                break;
            case "jobless":
                this.job=Jobs.JOBLESS;
                break;
        }

        if(!craft.equalsIgnoreCase("a")){
            String[] craftArg = craft.split("&");
            try {
                this.addPendingPickaxe(VegaTimers.SDF.parse(craftArg[1]),new Gson().fromJson(craftArg[0],Pickaxe.class));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }



    public String serialize(){
        StringBuilder serialized = inventory.serializeInv();
        DbUpdate.update(String.format("UPDATE `vega`.`players` SET `inventory` = '%s' WHERE (`id` = '%s')",serialized.toString(),this.id));
        return serialized.toString();
    }

    public String serializeCraft(){
        if(pendingPickaxe!=null){
            return new GsonBuilder().disableHtmlEscaping().create().toJson(pendingPickaxe)+"&"+VegaTimers.progressCraftTime(this);
        }
        return "a";
    }







    public void getWarning(TextChannel channel){
        if(warning != null){
            channel.sendMessage(new EmbedBuilder().setDescription("<@"+this.getId()+">\n"+warning).build()).queue();
            warning = null;
        }
    }

    public void setWarning(String text){
        if(warning != null){
            this.warning = this.warning +"\n\n"+text;
        }else{
            this.warning = text;
        }
    }






    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public int getNextLevelXp() {
        return nextLevelXp;
    }

    public int getMoney() {
        return money;
    }

    public void setJob(Jobs job) {
        String jobString = "";
        switch (job) {
            case MINER:
                jobString = "miner";
                break;
            case BLACKSMITH:
                jobString = "blacksmith";
                break;
            case MELTER:
                jobString = "melter";
                break;
            case JOBLESS:
                jobString = "jobless";
                break;
        }

        DbUpdate.update(String.format("UPDATE `vega`.`players` SET `job` = '%s' WHERE (`id` = '%s')",jobString,this.id));
        this.job = job;
    }

    public Jobs getJob() {
        return job;
    }

    public Pickaxe getUsedPickaxe() {
        return usedPickaxe;
    }

    public Player setUsedPickaxe(Pickaxe pickaxe){this.usedPickaxe=pickaxe;return this;}

    public String toString(){return String.format("**Para:** %d\n**Seviye:** %d\n**Yeni Seviye:** %d/%d\n**Meslek:** %s",money,level,xp,nextLevelXp,job.getName());}

    public String getMention(){return "<@"+this.id+">";}

    public boolean hasPendingPickaxe(){return pendingPickaxe!=null;}
}
