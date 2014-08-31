package de.manimax3.main;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.manimax3.cmd.Accept;
import de.manimax3.cmd.Create;
import de.manimax3.cmd.Deny;
import de.manimax3.cmd.DuellCommand;
import de.manimax3.main.MessageManager.MessageType;

public class CommandManager implements CommandExecutor {
	
	private ArrayList<DuellCommand> cmds;
	
	protected CommandManager(){
		cmds = new ArrayList<>();
		cmds.add(new Create());
		cmds.add(new Accept());
		cmds.add(new Deny());
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!command.getName().equalsIgnoreCase("duell")) return true;
		if(!(sender instanceof Player)){
			System.out.println("Please only use as a Player!");
			return true;
		}
		Player p = (Player) sender;
		
		if(args.length == 0){
			MessageManager.getInstance().sendMessage(p, MessageType.BAD, "Invalid usage of command!");
			return true;
		}
		
		DuellCommand c = getCommand(args[0]);
		if(c == null){
			MessageManager.getInstance().sendMessage(p, MessageType.BAD, "Could not find this command!");
			return true;
		}
		
		c.onCommand(p, args);
		
		return true;
	}
	private DuellCommand getCommand(String name) {
		for (DuellCommand cmd : cmds) if (cmd.getClass().getSimpleName().equalsIgnoreCase(name)) return cmd;
		return null;
	}

}
