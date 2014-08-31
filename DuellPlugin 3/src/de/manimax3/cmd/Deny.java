package de.manimax3.cmd;

import org.bukkit.entity.Player;

import de.manimax3.Request.RequestManager;
import de.manimax3.main.MessageManager;
import de.manimax3.main.MessageManager.MessageType;

public class Deny extends DuellCommand {
	
	private RequestManager rqmgr = RequestManager.getInstance();

	@Override
	public void onCommand(Player p, String[] args) {
		if(!args[0].equalsIgnoreCase("deny")) return;
		if(!(rqmgr.hasRequest(p))){
			MessageManager.getInstance().sendMessage(p, MessageType.INFO, "You don't have currently any requests!");
			return;
		}
		rqmgr.denyRequest(rqmgr.getRequest(p));
		return;
		
	}

}
