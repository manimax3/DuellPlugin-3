package de.manimax3.duell;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Duell {
	
	Player aggressingPlayer, defendingPlayer;
	Location aggLoc, defLoc;
	
	protected Duell(Player aggressing, Player defending){
		this.aggressingPlayer = aggressing;
		this.defendingPlayer = defending;
		this.aggLoc = aggressingPlayer.getLocation();
		this.defLoc = defendingPlayer.getLocation();
	}
	
	protected Player getAggressing(){
		return aggressingPlayer;
	}
	
	protected Player getDefending(){
		return defendingPlayer;
	}
	public void initialTP(){
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getServer().getPluginManager().getPlugin("DuellPlugin"), new Runnable(){
			@Override
			public void run() {
				defendingPlayer.teleport(aggLoc);
			}
		}, 200);
	}
}
