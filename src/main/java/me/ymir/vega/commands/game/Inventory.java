package me.ymir.vega.commands.game;

import me.ymir.vega.commands.CommandAbstract;
import me.ymir.vega.commands.CommandPermissions;
import me.ymir.vega.entites.player.Player;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Inventory extends CommandAbstract {
    @Override
    public String getName() {
        return "Envanter";
    }

    @Override
    public List<String> getAllies() {
        return Arrays.asList("e","env","profil");
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
        EmbedBuilder embedBuilder = player.getInventory().toEmbed();
        embedBuilder.setTitle(e.getAuthor().getName()+"'nın Envanteri").setColor(new Color(0x1AFF00))
                .setThumbnail(e.getAuthor().getAvatarUrl())
                .setFooter("Kazmaya devam!",e.getJDA().getSelfUser().getAvatarUrl())
                .addField("BİLGİLER",player.toString(),false);
        e.getChannel().sendMessage(embedBuilder.build()).queue();
    }
}
