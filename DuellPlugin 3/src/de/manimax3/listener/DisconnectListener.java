package de.manimax3.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.manimax3.duell.Duell;
import de.manimax3.duell.DuellManager;

public class DisconnectListener implements Listener{
	
	private DuellManager dlmgr = DuellManager.getInstance();
	private Duell d;
	
	@EventHandler
	public void onPlayerDisconnect(PlayerQuitEvent e){
		
		d = dlmgr.getDuell(e.getPlayer());
		if(d == null) return;
		dlmgr.duellDisconnect(e.getPlayer());
		
	}
}
