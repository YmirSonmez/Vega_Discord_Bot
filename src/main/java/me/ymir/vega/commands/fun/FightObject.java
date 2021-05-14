package me.ymir.vega.commands.fun;

import me.ymir.vega.utils.ChatManager;
import me.ymir.vega.entites.player.Player;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class FightObject {
    private final Player player1;
    private final Player player2;
    private boolean player2Ready;
    private final int money;
    private final TextChannel channel;


    private final String[] success = {"%s  vurdu!","%s'den başarılı bir sağ kroşe!"};
    private final String[] miss = {"%s vuramadı!","%s yumruğunu dağlara taşlara attı!"};




    public FightObject(Player player1, Player player2, int money, ChatManager chat) {
        this.player1 = player1;
        this.player2 = player2;
        this.money = money;
        this.channel = chat.getChannel();

        this.player2Ready = false;
    }

    public FightObject ready(){
        if((player2.getMoney()<this.money)){
            channel.sendMessage("Davet edilen oyuncunun yeteri kadar parası bulunmuyor!").queue();
        }else{
            player2.giveMoney(-this.money);
            this.player2Ready= true;
            fight();
        }
        return this;
    }

    public boolean isPlayer2Ready() {
        return this.player2Ready;
    }

    private void fight(){
        final Random[] random = {new Random()};
        final boolean[] player1punch = new boolean[1];
        final boolean[] player2punch = new boolean[1];
        final int[] lap = {0};
        EmbedBuilder fightEmbed = new EmbedBuilder().setTitle("İyi olan kazansın!");
        channel.sendMessage(fightEmbed.build()).queue(message -> {
            while(true){
                player1punch[0] = random[0].nextBoolean();
                player2punch[0] = random[0].nextBoolean();
                fightEmbed.setDescription((player1punch[0] ? String.format(this.success[random[0].nextInt(this.success.length)],getName(this.player1)) : String.format(this.miss[random[0].nextInt(this.miss.length)],getName(this.player1)))+"\n"
                        +(player2punch[0] ? String.format(this.success[random[0].nextInt(this.success.length)],getName(this.player2)) : String.format(this.miss[random[0].nextInt(this.miss.length)],getName(this.player2)))).
                setFooter("Tur: "+ ++lap[0]);
                message.editMessage(fightEmbed.build()).queue();

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(player1punch[0] && !player2punch[0]){
                    fightEmbed.setDescription(String.format("%s son darbesiyle rakibini yere serdi ve `%dTL'nin` sahibi oldu!",getName(this.player1),2*money));
                    player1.giveMoney(2*money);
                    message.editMessage(fightEmbed.build()).queueAfter(3, TimeUnit.SECONDS);
                    break;
                }else if(player2punch[0] && !player1punch[0]){
                    fightEmbed.setDescription(String.format("%s son darbesiyle rakibini yere serdi ve `%dTL'nin` sahibi oldu!",getName(this.player2),2*money));
                    player1.giveMoney(2*money);
                    message.editMessage(fightEmbed.build()).queueAfter(3, TimeUnit.SECONDS);
                    break;
                }
            }
        });

    }


    private String getName(Player player3){
        return "<@"+ player3.getId()+">";
    }

    public Player getPlayer1() {
        return player1;
    }

    public int getMoney(){return this.money;}

    public void end(){
        this.player1.giveMoney(this.money);

    }

}
