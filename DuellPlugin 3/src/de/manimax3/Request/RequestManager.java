package de.manimax3.Request;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import de.manimax3.duell.DuellManager;
import de.manimax3.main.ConfigManager;
import de.manimax3.main.MessageManager;
import de.manimax3.main.MessageManager.MessageType;

public class RequestManager {
	private static RequestManager instance = new RequestManager();
	public static RequestManager getInstance(){
		return instance;
	}
	
	private MessageManager msgmgr = MessageManager.getInstance();
	private DuellManager dlmgr = DuellManager.getInstance();
	private ConfigManager  cfgmgr = ConfigManager.getInstance();
	private boolean global;
	
	public ArrayList<Request> requests = new ArrayList<Request>();
	
	public void createRequest(Player requesting, Player requested){
		if(!(cfgmgr.get("Duellglobal." + requested.getName()) == null)){
			if(cfgmgr.get("Duellglobal." + requested.getName()) == "false"){
				msgmgr.sendMessage(requesting, MessageType.INFO, requested.getName() + " is suppressing Duell requests!");
				return;
			}
		}
		
		Request r = new Request(requesting, requested);
		requests.add(r);
		this.doRequests(r);
	}
	public boolean hasRequest(Player p){
		for(Request r : requests){
			if(p.equals(r.getRequested())) return true;
		}
		return false;
	}
	private void doRequests(Request r){
		msgmgr.sendMessage(r.getRequested(), MessageType.INFO, "The Player " + r.getRequesting().getName() + " wants a Duell againsts you! Use /duell accept or deny");
		msgmgr.sendMessage(r.getRequesting(), MessageType.INFO, "The Request got send to Player " + r.getRequested().getName());
	}
	public Request getRequest(Player p){
		for(Request r : requests){
			if(p.equals(r.getRequested()) || p.equals(r.getRequesting())) return r;
		}
		return null;
	}
	public void removeRequest(Request r){
		requests.remove(r);
		return;
	}
	public void acceptRequest(Request r){
		msgmgr.sendMessage(r.getRequested(), MessageType.GOOD, "You have accepted the Duell against " + r.getRequesting().getName() + " !");
		msgmgr.sendMessage(r.getRequesting(), MessageType.GOOD, "Player " + r.getRequested() + " has accepted the Duell against you!");
		
		msgmgr.sendMessage(r.getRequested(), MessageType.INFO, "Teleport will come in 10 Seconds!");
		msgmgr.sendMessage(r.getRequesting(), MessageType.INFO, "Teleport will come in 10 Seconds!");
		
		dlmgr.createDuell(r.getRequesting(), r.getRequested());
		dlmgr.startDuell(dlmgr.getDuell(r.getRequesting()));
		requests.remove(r);
	}
	public void denyRequest(Request r){
		msgmgr.sendMessage(r.getRequested(), MessageType.INFO, "You denied the Duell request against " + r.getRequesting().getName());
		msgmgr.sendMessage(r.getRequesting(), MessageType.INFO, r.getRequested().getName() + "denied the Duell request from you!");
		
		requests.remove(r);
	}
}
