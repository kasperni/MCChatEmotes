package me.Hazer.MChatEmotes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang.StringEscapeUtils;


public class EmoteHandler 
{
	private ArrayList<Emote> emoteList;
	
	/**
	 * Class constructor
	 * Creates an array of Emote objects
	 */
	public EmoteHandler()
	{
		emoteList = new ArrayList<>();
		getEmotesFromFile();
	}
	
	/**
	 * Sets emotes
	 */
	public void getEmotesFromFile()
	{
		BufferedReader br = null;
		
		try
		{
			br = new BufferedReader(new FileReader("emoteList.txt"));
		}
		catch(FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
		
		try
		{
			for (String line; (line = br.readLine()) != null;)
			{			
				String emote = "";
				String unicode = "";
				
				
				int index = line.indexOf(":");
				
				for(int i = 0; i < index; i++)
				{
					emote = emote + line.charAt(i);
				}
				
				for(int i = index + 1; i < line.length(); i++)
				{
					unicode = unicode + line.charAt(i);
				}
				
				//Bukkit.getLogger().info(unicode);
				
				this.emoteList.add(new Emote(emote, unicode));
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets emoteName and returns unicode of the emote
	 * @param emoteName : name of the emote
	 * @return : unicode of the emote
	 */
	public String getUnicodeByEmoteName(String emoteName)
	{
		String unicode = null;
		
		int i = 0;
		boolean searching = true;
		
		while(searching && i < this.emoteList.size())
		{
			Emote emote = this.emoteList.get(i);
			
			if(emote.getName().equalsIgnoreCase(emoteName))
			{
				unicode = emote.getUnicode();
				searching = false;
			}
			i++;
		}
		return unicode;
	}
	
	public String getEmoteNameByUnicode(String uni)
	{
		String emoteName = null;
		String unicode = StringEscapeUtils.escapeJava(uni);
		
		int i = 0;
		boolean searching = true;
		
		while(searching && i < this.emoteList.size())
		{
			Emote emote = this.emoteList.get(i);
			
			if(emote.getUnicode().equalsIgnoreCase(unicode))
			{
				emoteName = emote.getName();
				searching = false;
			}
			i++;
		}
		return emoteName;
	}
	
	/**
	 * Checks if emote name is an emote
	 * @param emoteName : name of the emote
	 * @return : true if is in list / false if not
	 */
	public boolean isEmote(String emoteName)
	{
		int i = 0;
		boolean searching = true;
		boolean isValid = false;

		while(searching && i < this.emoteList.size())
		{
			Emote emote = this.emoteList.get(i);

			if(emote.getName().equalsIgnoreCase(emoteName))
			{
				isValid = true;
			}
			i++;
		}
		
		return isValid;
	}
	
	public ArrayList<Emote> getEmoteList() { return this.emoteList; }
}
