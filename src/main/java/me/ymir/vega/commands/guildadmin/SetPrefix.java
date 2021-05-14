package me.ymir.vega.commands.guildadmin;

import me.ymir.vega.commands.CommandAbstract;
import me.ymir.vega.commands.CommandPermissions;
import me.ymir.vega.database.DbGuild;
import me.ymir.vega.entites.player.Player;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Collections;
import java.util.List;

public class SetPrefix extends CommandAbstract {
    @Override
    public String getName() {
        return "Prefix";
    }

    @Override
    public List<String> getAllies() {
        return Collections.singletonList("pref");
    }

    @Override
    public String getUsage() {
        return getName()+" <%yeni-prefix%>";
    }

    @Override
    protected boolean isRequiredPlayer() {
        return false;
    }

    @Override
    protected CommandPermissions getPermission() {
        return CommandPermissions.GUILD_OWNER;
    }

    @Override
    protected void execute(GuildMessageReceivedEvent e, Player player, String[] args) {
        if(args.length>1&&args[1].length()<45){
            DbGuild.setGuildPrefix(e.getGuild().getId(),args[1].trim());
            chat().setArg("Prefix güncellendi! : **"+args[1].trim()+"**").success();
        }else{
            chat().setArg(getUsage()).warning();
        }
    }
}
