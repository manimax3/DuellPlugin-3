package de.manimax3.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import de.manimax3.duell.Duell;
import de.manimax3.duell.DuellManager;

public class DeathListener implements Listener {
	
	private DuellManager dlmgr = DuellManager.getInstance();
	private Duell d;
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e){
		Player p = e.getEntity();
		d = dlmgr.getDuell(p);
		if(d == null) return;
		DuellManager.getInstance().duellDeath(p);
		
		
	}
	
}
