package de.manimax3.cmd;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.manimax3.Request.RequestManager;
import de.manimax3.main.MessageManager;
import de.manimax3.main.MessageManager.MessageType;

public class Create extends DuellCommand {

	private RequestManager rqmgr = RequestManager.getInstance();
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCommand(Player p, String[] args) {
		
		if(!args[0].equalsIgnoreCase("create")) return;
		
		Player target = Bukkit.getServer().getPlayer(args[1]);
		if(target == null){
			MessageManager.getInstance().sendMessage(p, MessageType.BAD, "Could not find Player");
			return;			
		}
		if(!target.isOnline()) {
			MessageManager.getInstance().sendMessage(p, MessageType.BAD, "Could not find Player");
			return;
		}
		if(target.equals(p)){
			MessageManager.getInstance().sendMessage(p, MessageType.BAD, "You can't create Duell requests against yourself!");
			return;
		}
		if(rqmgr.hasRequest(target)){
			if(rqmgr.getRequest(target).getRequesting().equals(p)){
				MessageManager.getInstance().sendMessage(p, MessageType.BAD, "You can't create a Request against the same Player multiple times!");
				return;
			}
		}
		//Request rq = new Request(p, target);
		rqmgr.createRequest(p, target);
		return;
		
	}

}
