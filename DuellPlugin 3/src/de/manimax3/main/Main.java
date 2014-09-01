package de.manimax3.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.manimax3.listener.DeathListener;
import de.manimax3.listener.DisconnectListener;

public class Main extends JavaPlugin 
{
	PluginManager pm;
	CommandManager cmdmgr;
	
	public void onEnable(){
		pm = Bukkit.getServer().getPluginManager();
		cmdmgr = new CommandManager();

		this.getCommand("duell").setExecutor(cmdmgr);
		
		pm.registerEvents(new DeathListener(), this);
		pm.registerEvents(new DisconnectListener(), this);
		
		ConfigManager.getInstance().setup(this);
		
		/*
		 * ToDo:
		 * - Command /duell stop (or cancel) (Duell will be canceled after a given time when the player dont get attacked) 
		 * - Help Command
		 * - ((Config))
		 */
	}
	
	public void onDisable(){
		ConfigManager.getInstance().save();
	}
	

}
