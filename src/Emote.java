package me.Hazer.MChatEmotes;

/**
 * Creates a class of an Emote which holds info about the name and unicode of the emote
 * @author Hazerr
 *
 */
public class Emote 
{
	private String name;
	private String unicode;
	
	public Emote(String name, String unicode)
	{
		this.name = name;
		this.unicode = unicode;
	}
	
	public String getName() { return this.name; }
	public String getUnicode() { return this.unicode; }
}




