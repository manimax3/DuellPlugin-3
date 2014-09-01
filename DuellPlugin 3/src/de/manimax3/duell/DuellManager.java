package de.manimax3.duell;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.manimax3.main.MessageManager;
import de.manimax3.main.MessageManager.MessageType;

public class DuellManager {
	private static DuellManager instance = new DuellManager();
	public static DuellManager getInstance(){
		return instance;
	}
	
	private ArrayList<Duell> duells = new ArrayList<Duell>(); 
	
	public void createDuell(Player aggressing, Player defending){
		duells.add(new Duell(aggressing, defending));
	}
	public void removeDuell(Duell d){
		duells.remove(d);
	}
	public void startDuell(Duell d){
		d.initialTP();
	}
	public Duell getDuell(Player p){
		for(Duell d : duells){
			if(d.getDefending().equals(p) || d.getAggressing().equals(p)) return d;
		}
		return null;
	}
	public void duellDeath(Player died){
		Duell dl = instance.getDuell(died);
		Player living;
		if(died.equals(dl.aggressingPlayer)) living = dl.defendingPlayer;
		else living = dl.aggressingPlayer;
		Bukkit.getServer().broadcastMessage(MessageManager.getInstance().rawMsg(MessageType.INFO, "Player " + died.getName() + " lost a Duell against " + living.getName()));
		
		duells.remove(dl);
	}
	public boolean hasDuell(Player p){
		for(Duell d : duells){
			if(p.equals(d.aggressingPlayer)) return true;
			if(p.equals(d.getDefending())) return true;
		}
		return false;
	}
	public void duellDisconnect(Player p){
		Duell d = this.getDuell(p);
		Bukkit.getServer().broadcastMessage(MessageManager.getInstance().rawMsg(MessageType.INFO, "Player " + p.getName() + " disconnected whilst a Duell"));
		duells.remove(d);
	}
}
