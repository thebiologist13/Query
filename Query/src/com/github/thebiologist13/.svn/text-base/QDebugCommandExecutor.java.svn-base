package com.github.thebiologist13;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QDebugCommandExecutor implements CommandExecutor {
	@SuppressWarnings("unused")
	private Query plugin;
	 
	public QDebugCommandExecutor(Query plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		/*
		 * This is the command that toggles debug mode.
		 */
		
		//Current debug state
		boolean debug = Query.debug;
		
		//Player
		Player p = null;
		
		if(cmd.getName().equalsIgnoreCase("qdebug")) {
			
			if(sender instanceof Player) {
				p = (Player) sender;
			}
			
			//If debug mode is on, turn it off
			if(debug) {
				Query.debug = false;
				Query.debugMsg("Query debug mode disabled.");
				
				if(p != null) {
					p.sendMessage(ChatColor.DARK_PURPLE + "Query debug mode disabled.");
				}
			}
			
			//If debug mode is off, turn it on
			if(!debug) {
				Query.debug = true;
				Query.debugMsg("Query debug mode enabled.");
				
				if(p != null) {
					p.sendMessage(ChatColor.DARK_PURPLE + "Query debug mode enabled.");
				}
			}
			return true;
		}
		return false;
	}
	
}
