package me.Hazer.MChatEmotes;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;


/**
 * This class handles the event on which player types message in chat.
 * @author Hazerr
 *
 */
public class ChatListener implements Listener
{
	private MessageHandler messageHandler;
	
	/**
	 * Class constructor
	 * Creates an instance of MessageHandler
	 */
	public ChatListener()
	{
		messageHandler = new MessageHandler();
	}
	
	/**
	 * The event handler which gets message sent and changes it to formatted message
	 * @param event
	 */
	@EventHandler
	private void onPlayerChat(AsyncPlayerChatEvent event)
	{
		String message = event.getMessage();
		Player player = event.getPlayer();
		String newmsg = messageHandler.formatMessage(message);
		
		EmoteHandler emoteHandler = new EmoteHandler();
		
		BaseComponent msg = createPlayerNameComponent(player);
		String[] parts = newmsg.split(" ");
		
		for (String part : parts)
		{
			String unicodeToName;
			BaseComponent cmp = new TextComponent(part + " ");
		
			if(emoteHandler.getEmoteNameByUnicode(part) != null)
			{
				unicodeToName = emoteHandler.getEmoteNameByUnicode(part);
				cmp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("\u00A76\u00A7o" + unicodeToName)));
			}
			
			msg.addExtra(cmp);
		}
		
		for (Player recipient : event.getRecipients()) {
		    recipient.spigot().sendMessage(msg);
		}
		
		Bukkit.getLogger().info(String.format(event.getFormat(), event.getPlayer().getDisplayName(), event.getMessage()));
		
		event.setCancelled(true);
	}
	
	Team getPlayerTeam(Player player) {
        Team result = null;

        ScoreboardManager mgr = Bukkit.getScoreboardManager();
        if (mgr == null) {
            Bukkit.getLogger().warning("Unable to get scoreboard manager");
            return null;
        }

        Set<Team> teams = mgr.getMainScoreboard().getTeams();
        for (Team team : teams) {
            if (team.hasEntry(player.getDisplayName())) {
                result = team;
                break;
            }
        }

        return result;
    }

    ChatColor getNameColor(Player player) {
        ChatColor result = ChatColor.WHITE;

        Team team = getPlayerTeam(player);
        if (team != null) {
            result = team.getColor().asBungee();
        }
        return result;
    }

    BaseComponent createPlayerNameComponent(Player player)
    {
        BaseComponent result = new TextComponent("<");
        BaseComponent playerNameComponent = new TextComponent(player.getDisplayName());
        playerNameComponent.setColor(getNameColor(player));
        result.addExtra(playerNameComponent);
        result.addExtra(new TextComponent("> "));
        return result;
    }
}
