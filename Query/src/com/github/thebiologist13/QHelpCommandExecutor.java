package com.github.thebiologist13;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QHelpCommandExecutor implements CommandExecutor{

	private static Player player = null;
	
	@SuppressWarnings("unused")
	private Query plugin;
	
	public QHelpCommandExecutor(Query plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			player = (Player) sender;
		}
		
		if(cmd.getName().equalsIgnoreCase("qhelp")) {
			if(player == null) {
				Query.debugMsg("/qlist command used by console.");
				
				Query.promptMsg("* * * * * QUERY VERSION 2.0 HELP * * * * *");
				Query.promptMsg("qlist -> Lists all queries. ");
				Query.promptMsg("delquery <query name> -> Deletes the specified query. ");
				Query.promptMsg("qlist -> Lists all Queries across ALL worlds. ");
				Query.promptMsg("qhelp -> Displays this message.");
				Query.promptMsg("qdebug -> For developer purpoes, basically spams the console.");
				Query.promptMsg("* * * * * * * * * * * * * * * * * * * * * ");
				
			} else {
				if(checkPerm("qhelp")) {
					
					//debugging
					Query.debugMsg("/qlist command used by " + player.getName());
					
					player.sendMessage(ChatColor.DARK_PURPLE + "* * * * * * * * * * " + ChatColor.WHITE + "QUERY VERSION 2.0 HELP" + ChatColor.DARK_PURPLE +" * * * * * * * * * *");
					if(checkPerm("query")) {
						player.sendMessage(ChatColor.DARK_PURPLE + "/query" + ChatColor.WHITE + " -> Queries the area you are currently standing in.");
					}
					if(checkPerm("setquery")) {
						player.sendMessage(ChatColor.DARK_PURPLE + "/setquery <query name>" + ChatColor.WHITE + " -> Sets a new query within the positions set.");
					}
					if(checkPerm("delquery")) {
						player.sendMessage(ChatColor.DARK_PURPLE + "/delquery <query name>" + ChatColor.WHITE + " -> Deletes the specified query. CAN NOT BE UNDONE!");
					}
					if(checkPerm("querydesc")) {
						player.sendMessage(ChatColor.DARK_PURPLE + "/querydesc <query name> <line> <description>" + ChatColor.WHITE + " -> Sets the description of the query. " +
								"The line can also be specifed. This server allows for a max of " + String.valueOf(Query.config.getInt("maxLines")));
					}
					if(checkPerm("pos")) {
						player.sendMessage(ChatColor.DARK_PURPLE + "/qpos1" + ChatColor.WHITE + " -> Sets the first position where you are standing.");
						player.sendMessage(ChatColor.DARK_PURPLE + "/qpos2" + ChatColor.WHITE + " -> Sets the second position where you are standing.");
					}
					if(checkPerm("qarea")) {
						player.sendMessage(ChatColor.DARK_PURPLE + "/qarea <expand:contract> <direction> <amount>" + ChatColor.WHITE + " -> Expands or contracts the area you have selected. " +
								"Accepts North, South, East, West, Up, or Down for direction.");
					}
					if(checkPerm("qlist")) {
						player.sendMessage(ChatColor.DARK_PURPLE + "/qlist" + ChatColor.WHITE + " -> Lists all Queries across ALL worlds.");
					}
					player.sendMessage(ChatColor.DARK_PURPLE + "/qhelp" + ChatColor.WHITE + " -> Displays this message.");
					player.sendMessage(ChatColor.DARK_PURPLE + "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
				}
			}
			return true;
		}
		return false;
	}
	//My way to check a permission
	public static boolean checkPerm(String perm) {
		if(player != null) {
			if(player.hasPermission("query." + perm)) {
				Query.debugMsg(player.getName() + " has the query." + perm + " permission.");
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}	
	}
}
