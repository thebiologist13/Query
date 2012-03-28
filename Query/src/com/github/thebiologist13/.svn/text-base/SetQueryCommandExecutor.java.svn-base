package com.github.thebiologist13;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SetQueryCommandExecutor implements CommandExecutor {
	
	//Player
	private static Player player = null;

	//Plugin Variable
	private Query plugin;
		 
	public SetQueryCommandExecutor(Query plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//Location of Player
		Location playerLocation = null;
		
		//X, Y, and Z of Player
		int playerX = 0;
		int playerY = 0;
		int playerZ = 0;
		
		//Name of the player as a string
		String playerName = "";
		
		//If the sender of the command is a player (not the console)
		if(sender instanceof Player) {
			
			//player = sender cast into player
			player = (Player) sender;
			
			//Get stuff and save them to the appropriate variables
			playerLocation = player.getLocation();
			playerX = playerLocation.getBlockX();
			playerY = playerLocation.getBlockY();
			playerZ = playerLocation.getBlockZ();
			playerName = player.getName();
		}
		
		//If the command is /setquery, do the following
		if(cmd.getName().equalsIgnoreCase("setquery")) {
			
			//debugging
	    	Query.debugMsg("/setquery command used.");
	    	
			//If the player remained null (sender wasn't cast) because it was from the console,
			//Send a message to the console saying it is only for in game
			if(player==null) {
				Query.promptMsg("You can only use the command: '" + cmd.getName() + "' in-game.");
				
			//Otherwise
			} else {
				
				//If debug is turned on, print the debug message
				Query.debugMsg(playerName + " at location = (" + String.valueOf(playerX) + ", " + String.valueOf(playerY) + ", " +
							String.valueOf(playerZ) + ") used the /setquery command. Argument 1: " + args[0]);
				
				if(checkPerm("setquery")) {
					
					//debugging
			    	Query.debugMsg(playerName + "has the query.setquery permission.");
			    	
					//Create new file
					File file = new File(plugin.getDataFolder() + "\\Queries", args[0] + ".yml");
					
					//debugging
			    	Query.debugMsg("File Location to save to: " + plugin.getDataFolder() + "/Queries");
			    	
			    	//Load the YAML configuration
					FileConfiguration yaml = YamlConfiguration.loadConfiguration(file);
					
					//Saves the owner of the Query
					yaml.set(args[0] + ".owner", playerName);
					
					//Saves world name
					yaml.set(args[0] + ".world", player.getWorld().getName());
					
					//Saves the positions, but also checks that positions are set.
					if(QueryData.position1.get(playerName) !=null || QueryData.position2.get(playerName) != null) {
						yaml.set(args[0] + ".pos1x", QueryData.position1.get(playerName).getBlockX());
						yaml.set(args[0] + ".pos1y", QueryData.position1.get(playerName).getBlockY());
						yaml.set(args[0] + ".pos1z", QueryData.position1.get(playerName).getBlockZ());
						yaml.set(args[0] + ".pos2x", QueryData.position2.get(playerName).getBlockX());
						yaml.set(args[0] + ".pos2y", QueryData.position2.get(playerName).getBlockY());
						yaml.set(args[0] + ".pos2z", QueryData.position2.get(playerName).getBlockZ());
					} else {
						Query.promptMsg("/setquery attempted, but one or more of the positions were not set.");
						player.sendMessage(ChatColor.DARK_RED + "You haven't set both positions yet!");
					}
					
					//Try to save but catch the IO exception the .save(file) method throws
					try {
						//Save the file
						yaml.save(file);
						
						//Send a success msg.
						player.sendMessage(ChatColor.DARK_PURPLE + "Query " + args[0] + " has been created! Add the description with /querydesc <query name> <line> <description>");
						
						//Tell the server console
						Query.promptMsg("Query " + args[0] +  " has been created!");
				    	Query.promptMsg(playerName + " has saved their position 1 coordinates to: " + QueryData.position1.get(playerName).getBlockX() + ", " + 
				    			QueryData.position1.get(playerName).getBlockY() + ", "+ QueryData.position1.get(playerName).getBlockZ());
				    	Query.promptMsg(playerName + " has saved their position 1 coordinates to: " + QueryData.position2.get(playerName).getBlockX() + ", " + 
				    			QueryData.position2.get(playerName).getBlockY() + ", "+ QueryData.position2.get(playerName).getBlockZ());
				    	
					} catch (IOException e) {
						//OH NOES! Tell the player and print out to console if an error occurred
						Query.debugMsg("IO Exception when saving new query. " + e.getMessage());
						Query.severeMsg("Error saving new Query.");
						player.sendMessage(ChatColor.DARK_RED + "Error saving new query");
					}
				}
			}
			//return
			return true;
			
		}
		if(cmd.getName().equalsIgnoreCase("qpos1")) {
			
			//If the player remained null (sender wasn't cast) because it was from the console,
			//Send a message to the console saying it is only for in game
			if(player==null) {
				Query.promptMsg("You can only use the command: '" + cmd.getName() + "' in-game.");
				
			//Otherwise
			} else {
				
				//Checks for the permission
				if(checkPerm("pos")) {
					
					//debugging
					Query.debugMsg(playerName + " at location = (" + String.valueOf(playerX) + ", " + String.valueOf(playerY) + ", " +
							String.valueOf(playerZ) + ") used the /qpos1 command.");
					Query.debugMsg(playerName + "has the query.pos permission.");
					
					//Sets the position in QueryData
					QueryData.position1.put(playerName, playerLocation);
					Query.debugMsg(playerName + " set position 1 at (" + String.valueOf(playerX) + 
							", " + String.valueOf(playerY) + ", " + String.valueOf(playerZ) + ")");
					player.sendMessage(ChatColor.DARK_PURPLE + "Position 1 set at (" + String.valueOf(playerX) + 
							", " + String.valueOf(playerY) + ", " + String.valueOf(playerZ) + ")");
				}
			}
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("qpos2")) {
			
			//If the player remained null (sender wasn't cast) because it was from the console,
			//Send a message to the console saying it is only for in game
			if(player==null) {
				Query.promptMsg("You can only use the command: '" + cmd.getName() + "' in-game.");
				
			//Otherwise
			} else {
				//Checks for the permission
				if(checkPerm("pos")) {
					
					//debugging
					Query.debugMsg(playerName + " at location = (" + String.valueOf(playerX) + ", " + String.valueOf(playerY) + ", " +
							String.valueOf(playerZ) + ") used the /qpos2 command.");
					Query.debugMsg(playerName + "has the query.pos permission.");
					
					//Sets the position in QueryData
					QueryData.position2.put(playerName, playerLocation);
					Query.debugMsg(playerName + " set position 2 at (" + String.valueOf(playerX) + 
							", " + String.valueOf(playerY) + ", " + String.valueOf(playerZ) + ")");
					player.sendMessage(ChatColor.DARK_PURPLE + "Position 2 set at (" + String.valueOf(playerX) + 
							", " + String.valueOf(playerY) + ", " + String.valueOf(playerZ) + ")");
				}
			}
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("qarea")) {
			
			if(player == null) {
				Query.promptMsg("You can only use the command: '" + cmd.getName() + "' in-game.");
			} else {
				if(args.length == 3) {
					if(checkPerm("qarea")) {
						
						//debugging
						Query.debugMsg(playerName + "used the /qarea command.");
						
						HashMap<String, Location> pos1 = QueryData.position1;
						HashMap<String, Location> pos2 = QueryData.position2;
						
						if(args[0].equalsIgnoreCase("expand")) {
							//The Gigantic method in QueryData that expands.
							QueryData.expandSelection(args, player);
							
							player.sendMessage(ChatColor.DARK_PURPLE + "Successfully expanded selection area. The new positions are: Position 1 = (" + 
									pos1.get(playerName).getBlockX() + ", " + pos1.get(playerName).getBlockY() + ", " + pos1.get(playerName).getBlockZ()
									+ ") Position 2 = (" + pos2.get(playerName).getBlockX() + ", " + pos2.get(playerName).getBlockY() + ", " +
									pos2.get(playerName).getBlockZ() + ")");
							
						} else if(args[0].equalsIgnoreCase("contract")) {
							//The Gigantic method in QueryData that contracts.
							QueryData.contractSelection(args, player);
							
							player.sendMessage(ChatColor.DARK_PURPLE + "Successfully contracted selection area. The new positions are: Position 1 = (" + 
									pos1.get(playerName).getBlockX() + ", " + pos1.get(playerName).getBlockY() + ", " + pos1.get(playerName).getBlockZ()
									+ ") Position 2 = (" + pos2.get(playerName).getBlockX() + ", " + pos2.get(playerName).getBlockY() + ", " +
									pos2.get(playerName).getBlockZ() + ")");
							
						} else {
							player.sendMessage(ChatColor.DARK_RED + "That isn't a valid modifier for the selection. Use expand or contract.");
						}
					}
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
