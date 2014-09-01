package de.manimax3.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigManager {
	private File f;
	private FileConfiguration config;
	private Plugin p;
	
	private static ConfigManager instance = new ConfigManager();
	public static ConfigManager getInstance(){
		return instance;
	}

	protected void setup(Plugin p){
		this.p = p;
		if(!p.getDataFolder().exists()) p.getDataFolder().mkdir();		
		
		f = new File(p.getDataFolder(), "config.yml");
		
		if(!f.exists()){
			try{f.createNewFile();}
			catch(Exception e) {e.printStackTrace();}
		}
		config = YamlConfiguration.loadConfiguration(f);
	}
	protected void save(){
		try {
			config.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void set(String path, Object value){
		config.set(path, value);
		try {
			config.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Object get(String path){
		Object o = config.get(path);
		return o;
	}
	
}
