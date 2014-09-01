package de.manimax3.cmd;

import org.bukkit.entity.Player;

import de.manimax3.main.ConfigManager;
import de.manimax3.main.MessageManager;
import de.manimax3.main.MessageManager.MessageType;

public class Global extends DuellCommand {

	MessageManager msgmgr = MessageManager.getInstance();
	
	@Override
	public void onCommand(Player p, String[] args) {
		
		if(!args[0].equalsIgnoreCase("global")) return;
		if(!(args.length == 2)){
			msgmgr.sendMessage(p, MessageType.BAD, "You can also use /duell global help to get more information about this command!");
			return;
		}
		if(args[1].equalsIgnoreCase("deny")){
			ConfigManager.getInstance().set("Duellglobal." + p.getName(), "false");
			msgmgr.sendMessage(p, MessageType.INFO, "You dont get any Duell requests anymore!");
			return;
		}
		else if(args[1].equalsIgnoreCase("accept")){
			ConfigManager.getInstance().set("Duellglobal." + p.getName(), "true");
			msgmgr.sendMessage(p, MessageType.INFO, "Getting Duell requests is now activated again!");
			return;
		}
		else if(args[1].equalsIgnoreCase("help")){
			msgmgr.sendMessage(p, MessageType.INFO, "If you want to suppress duell requests just use /duell global deny", "If you want to stop suppressing Duell requests just use /duell global accept");
			return;
		}
		else{
			msgmgr.sendMessage(p, MessageType.BAD, "Invalid arguments! Use /duell global help to more information.");
			return;
		}
		
	}

}
