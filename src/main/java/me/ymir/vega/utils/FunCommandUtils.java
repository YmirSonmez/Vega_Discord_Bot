package me.ymir.vega.utils;

import me.ymir.vega.commands.fun.FightObject;
import me.ymir.vega.entites.player.Player;


import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class FunCommandUtils {
    private final static HashMap<Player, FightObject> FIGHT_INVITE = new HashMap<>();

    public static void addFightInvite(Player invited, Player inviter, int money, ChatManager chat){
        FightObject object = new FightObject(inviter,invited,money,chat);
        FIGHT_INVITE.put(invited,object);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(FIGHT_INVITE.containsKey(invited)){
                    FIGHT_INVITE.remove(invited);
                    if(!object.isPlayer2Ready()){
                        object.end();
                    }
                }
            }
        }, 30000);
    }
    public static FightObject getFightObject(Player invited){
        return FIGHT_INVITE.get(invited);
    }
    public static boolean hasInvite(Player invited){return FIGHT_INVITE.containsKey(invited);}
    public static void rejectInvite(Player invited){FIGHT_INVITE.remove(invited).end();}

}
