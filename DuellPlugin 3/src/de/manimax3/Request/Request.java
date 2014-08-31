package de.manimax3.Request;

import org.bukkit.entity.Player;

public class Request {
	private Player requesting, requested;
	public Player getRequesting(){
		return requesting;
	}
	public Player getRequested(){
		return requested;
	}

	public Request(Player requesting, Player requested){
		this.requesting = requesting;
		this.requested = requested;
	}
}
