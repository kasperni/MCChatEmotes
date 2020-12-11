package me.Hazer.MChatEmotes;

import java.util.ArrayList;

import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This is the main class of the plugin. It starts onEnable() and sets ChatListener as an event
 * @author Hazerr
 *
 */
public class Main extends JavaPlugin
{
	private final String tag = "\u00A7b[\u00A7kx\u00A7bChat Emotes\u00A7kx\u00A7b]\u00A7f ";

	/**
	 * Called whenever this plugin is enabled on the server
	 */
	@Override
	public void onEnable()
	{
		// Register ChatListener as an event in our plugin
		this.getServer().getPluginManager().registerEvents(new ChatListener(), this);
	}
	
	/**
	 * Called whenever this plugin is disabled on the server
	 */
	@Override
	public void onDisable()
	{
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{	
		if(label.equalsIgnoreCase("isemote"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player) sender;
				EmoteHandler emoteHandler = new EmoteHandler();
				ArrayList<Emote> emoteList = emoteHandler.getEmoteList();
				
				if(args.length == 0)
				{
					player.sendMessage(tag + "\u00A77\u00A7lCorrect usage:\u00A7f /isemote <emote_name> " + StringEscapeUtils.unescapeJava(emoteHandler.getUnicodeByEmoteName("peeponotes")));
				}
				
				else
				{
					boolean found = false;
					int i = 0;
					
					while(!found && i < emoteList.size())
					{
						String emote_name = emoteList.get(i).getName();
						if(args[0].equalsIgnoreCase(emote_name))
						{
							String message = tag + "\u00A77\u00A7lThat emote exist\u00A7f -> " +  StringEscapeUtils.unescapeJava(emoteHandler.getUnicodeByEmoteName(emote_name));
							player.sendMessage(message);
							found = true;
						}
						
						i++;
					}
					
					if(!found)
					{
						String message = tag + "\u00A77\u00A7lThis emote does \u00A7c\u00A7lnot \u00A77\u00A7lexist\u00A7f " +  StringEscapeUtils.unescapeJava(emoteHandler.getUnicodeByEmoteName("sadge"));
						player.sendMessage(message);
					}
				}
			}
		}
		
		if(label.equalsIgnoreCase("emotes"))
		{
			if(sender instanceof Player)
			{
				Player player = (Player) sender;
				EmoteHandler emoteHandler = new EmoteHandler();

				if(args.length == 1)
				{
					final int emotesPerPage = 75;
					
					ArrayList<Emote> emoteList = emoteHandler.getEmoteList();
					
					ArrayList<ArrayList<Emote>> pageList = new ArrayList<ArrayList<Emote>>();
					
					double a = (double) emoteList.size() / (double) emotesPerPage;
					int pages = (int) Math.ceil(a);
					
					for(int i = 0; i < pages; i++)
					{
						pageList.add(new ArrayList<Emote>());
					}
					
					int currentPage = 0;
					for(Emote emote : emoteList)
					{
						pageList.get(currentPage).add(emote);

						if(pageList.get(currentPage).size() >= emotesPerPage)
						{
							currentPage++;
						}
					}
					
					
					int argNum = Integer.parseInt(args[0]);
					int fixedArgNum = argNum - 1;
	
					String emoteStringList = "";
					if(argNum > 0 && pageList.size() > fixedArgNum)
					{
						for(int i = 0; i < pageList.get(fixedArgNum).size(); i++)
						{
							String emote = pageList.get(fixedArgNum).get(i).getName();
							emoteStringList = emoteStringList + " \u00a77" + emote + " \u00A7f" + StringEscapeUtils.unescapeJava(emoteHandler.getUnicodeByEmoteName(emote));
						}
						player.sendMessage(emoteStringList);
						player.sendMessage(tag + "\u00a77\u00a7lYou are currently viewing page \u00A72" + argNum + "\u00a77\u00a7l of " + "\u00A72" + pageList.size());
					}
					
					else if(argNum <= 0)
					{
						String message = tag + "\u00A77\u00A7lPage\u00A72 " + argNum + " \u00A77\u00A7ldoes \u00A7c\u00A7lnot \u00A77\u00A7lexist\u00A7f " + StringEscapeUtils.unescapeJava(emoteHandler.getUnicodeByEmoteName("pepege"));
						player.sendMessage(message);
					}
					
					else
					{
						String message = tag + "\u00A77\u00A7lThere are only\u00A72 " + pageList.size() + " \u00A77\u00A7lpages of emotes";
						player.sendMessage(message);
					}
				}
				
				else
				{
					String message = tag + "\u00A77\u00A7lCorrect usage:\u00A7f /emotes <page> " + StringEscapeUtils.unescapeJava(emoteHandler.getUnicodeByEmoteName("peeponotes"));
					player.sendMessage(message);
				}
			}
		}
		return true;
	}
}












