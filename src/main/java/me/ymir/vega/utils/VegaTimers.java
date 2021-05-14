package me.ymir.vega.utils;

import me.ymir.vega.entites.player.Player;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class VegaTimers {
    public static final SimpleDateFormat SDF= new SimpleDateFormat("d MMMM yyyy kk:mm",new Locale("tr"));

    private static final HashMap<String, Date> miningDelays = new HashMap<>();
    private static final HashMap<String, Date> craftTime = new HashMap<>();



    public static void addMiningDelay(int second, Player player){
        miningDelays.remove(player.getId());
        Calendar date = Calendar.getInstance();
        date.add(Calendar.SECOND,second);
        miningDelays.put(player.getId(),date.getTime());
    }



    public static void addCraft(Player player,Date date){
        craftTime.remove(player.getId());
        craftTime.put(player.getId(),date);
    }

    public static void removeCraft(Player player){
        craftTime.remove(player.getId());
    }



    public static boolean progressCraft(Player player){
        return craftTime.containsKey(player.getId());
    }
    public static String progressCraftTime(Player player){
        if (!progressCraft(player)) {
            return "0";
        }
        return SDF.format(craftTime.get(player.getId()));
    }



    public static int getMiningDelay(Player player){
        if(miningDelays.containsKey(player.getId())){
           return (int) getDateDiff(new Date(),miningDelays.get(player.getId()),TimeUnit.SECONDS);
        }
        return 0;
    }




    private static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillis = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillis,TimeUnit.MILLISECONDS);
    }



}
