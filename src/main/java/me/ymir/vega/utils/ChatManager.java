package me.ymir.vega.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class ChatManager {
    private TextChannel channel;
    private User user;
    private EmbedBuilder embed;


    private final String TOP_OF_PAGE = "**━━━━━━━━\uD835\uDD4D\uD835\uDD3C\uD835\uDD3E\uD835\uDD38━━━━━━━━** \n\n";
    private final String PAGE_BREAK = "\n\n**━━━━━━━━━<a:vegadisco:839206604616630323>━━━━━━━━━**";


    public ChatManager() {
    }

    public ChatManager set(TextChannel channel,User user){
        this.channel = channel;
        this.user = user;
        this.embed = new EmbedBuilder().setFooter(user.getName(),user.getAvatarUrl());
        return this;
    }

    public ChatManager setArg(String arg){
        this.embed.setDescription(TOP_OF_PAGE+arg+PAGE_BREAK);
        return this;
    }

    public void warning(){
        embed.setTitle("<a:warn:838775435906777098> Bir Terslik var!");
        embed.setColor(new Color(0xFF0000));
        this.send();
    }
    public void success(){
        embed.setTitle("<a:oky:829697831383203903> Sorunsuz!");
        embed.setColor(new Color(0x88FF00));
        this.send();
    }
    public void info(){
        embed.setTitle("<a:info:839205120251396136> Bilgi");
        embed.setColor(new Color(0x4A00FF));
        this.send();
    }
    public void mine(){
        embed.setTitle("<a:mine:839210641990746143> Kazmak ne yorucu iş!");
        embed.setColor(new Color(0x6F3300));
        embed.setThumbnail("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/39ac6b8e-c8c9-4d20-a781-2b36215bf34a/darz0p6-c135df35-834a-4c05-9629-4da136081691.gif?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzM5YWM2YjhlLWM4YzktNGQyMC1hNzgxLTJiMzYyMTViZjM0YVwvZGFyejBwNi1jMTM1ZGYzNS04MzRhLTRjMDUtOTYyOS00ZGExMzYwODE2OTEuZ2lmIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.CsEDhx9PEif5kPUEIItQTTmzJ1UUjRgk585Uc8BaiSU");
        this.send();
    }
    public void craftError(){
        embed.setTitle("Ahtapot değilim!");
        embed.setColor(new Color(0xFFFFFF));
        this.send();
    }
    public void mineDelay(){
        embed.setTitle("Bizde insanız biraz dinlenelim!");
        embed.setColor(new Color(0xFF9800));
        this.send();
    }

    public void furnace(){
        embed.setTitle("<a:Furnace:838798843600175195> Çok sıcak!");
        embed.setColor(new Color(0xFF8400));
        this.send();
    }

    public void unknown(){
        this.send();
    }



    private boolean send(){
        channel.sendMessage(embed.build()).queue();


        return true;
    }

    public TextChannel getChannel(){return this.channel;}
}
