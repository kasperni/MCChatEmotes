package me.Hazer.MChatEmotes;

import java.util.ArrayList;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * This class takes the message and formats it to the formatted version with the specified unicode values
 * @author Hazerr
 *
 */
public class MessageHandler 
{
	private EmoteHandler emoteHandler;
	
	/**
	 * Class constructor
	 * Creates an instance of EmoteHandler
	 */
	public MessageHandler()
	{
		emoteHandler = new EmoteHandler();
	}
	
	/**
	 * Gets a message then formats it to a new message with the unicoded emote
	 * @param message : message to format
	 * @return : formatted message
	 */
	public String formatMessage(String message)
	{
		String formattedMessage = message;
		
		ArrayList<String> words = getWordsFromMessage(message);
		
		for(String word : words)
		{
			if(emoteHandler.isEmote(word))
			{
				formattedMessage = formattedMessage.replaceFirst(word, StringEscapeUtils.unescapeJava(emoteHandler.getUnicodeByEmoteName(word)));
			}
		}
		
		return formattedMessage;
	}
	
	/**
	 * Gets a message and splits it to words in an ArrayList
	 * @param message : message to split
	 * @return : words
	 */
	public ArrayList<String> getWordsFromMessage(String message)
	{
		ArrayList<String> words = new ArrayList<>();
		
		for(String word: message.split(" "))
		{
			words.add(word);
		}
		return words;
	}
	
}
