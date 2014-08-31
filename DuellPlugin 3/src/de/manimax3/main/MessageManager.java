package de.manimax3.main;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageManager {

	private static MessageManager instance = new MessageManager();
	public static MessageManager getInstance(){
		return instance;
	}
	
	public enum MessageType{
		
		GOOD(ChatColor.GREEN),
		INFO(ChatColor.YELLOW),
		BAD(ChatColor.RED);
		
		private ChatColor color;
		
		private MessageType(ChatColor color){
			this.color = color;
		}
		private ChatColor getColor(){
			return color;
		}
	}
	
	private String prefix = ChatColor.BLUE + "[Duell] ";
	public void sendMessage(Player sender, MessageType type, String... msgs){
		for(String msg : msgs){
			sender.sendMessage(prefix + type.getColor() + msg);
		}
	}
	
	public String rawMsg(MessageType type, String msg){
		return prefix + type.color + msg;
	}
}
