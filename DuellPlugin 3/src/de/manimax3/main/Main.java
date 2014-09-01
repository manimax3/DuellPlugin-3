package de.manimax3.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.manimax3.listener.DeathListener;

public class Main extends JavaPlugin 
{
	PluginManager pm;
	CommandManager cmdmgr;
	
	public void onEnable(){
		pm = Bukkit.getServer().getPluginManager();
		cmdmgr = new CommandManager();

		this.getCommand("duell").setExecutor(cmdmgr);
		
		pm.registerEvents(new DeathListener(), this);
		
		/*
		 * ToDo:
		 * 
		 * - Removing Duell after Disconnect (Listener)
		 * - No Duells against yourself (easy if Abfrage)
		 * - Only 1 Duell against same Player
		 * - Advanced Death Message (Attacking Players without Duell fight with a Player in a Duell)
		 * - Global Duell Deny (dont get Request Messages)
		 * - ((Config))
		 */
	}
	

}
