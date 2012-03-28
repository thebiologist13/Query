package com.github.thebiologist13;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class QueryPlayerListener implements Listener {
	
	private static Player player = null;
	
	@SuppressWarnings("unused")
	private Query plugin;
	
	public QueryPlayerListener(Query plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		try {
			
			/*
			 * All these variable store different values like positions, items, blocks, etc.
			 */
			
			//Variable for the clicked block
			Block block = event.getClickedBlock();
			//Variable for the location of the block
			Location blockLocation = block.getLocation();
			//Variables for the X, Y, and Z of the block
			int blockX = blockLocation.getBlockX();
			int blockY = blockLocation.getBlockY();
			int blockZ = blockLocation.getBlockZ();
			
			//Variable for the player
			player = event.getPlayer();
			
			//Variable for the item used to click with
			ItemStack item = event.getItem();
			//Variable for the action (right or left click)
			Action action = event.getAction();
			
			//Variable for the plugin's config file
			FileConfiguration config = Query.config;
			//Variable for the item used to set positions and query areas from the config file
			int posID = config.getInt("positionID");
			int queryID = config.getInt("queryID");

			/*
			 * This if statement checks if the item the player clicked with is the same
			 * item specified in the config for setting positions.
			 */
			
			if(item.getTypeId()==posID) {
				
				//debugging
		    	Query.debugMsg("Item ID used (" + String.valueOf(item.getTypeId()) + ") equals position ID from config file.");
		    	Query.debugMsg("Player from event: " + player.getName());
		    	Query.debugMsg("Item from event: " + item);
		    	Query.debugMsg("Action from event: " + action.toString());
		    	Query.debugMsg("Position ID from config: "  + String.valueOf(posID) + ", Query ID from config: " + String.valueOf(queryID));
		    	
				//Makes sure the player has permission.
				if(checkPerm("pos")) {
					
					//If it was a left click
					if(action.equals(Action.LEFT_CLICK_BLOCK)) {
						
						QueryData.position1.put(player.getName(), blockLocation);
						
						//debugging
						Query.debugMsg(player.getName() + " left-clicked  a block at location x=" + String.valueOf(blockX) + ", y=" + String.valueOf(blockY) +
								", z=" + String.valueOf(blockZ) + "with a " + item.toString());
						
						player.sendMessage(ChatColor.DARK_PURPLE + "Position 1 set at (" + String.valueOf(blockX) + 
								", " + String.valueOf(blockY) + ", " + String.valueOf(blockZ) + ")");
					}
					
					//If it was a right click
					if(action.equals(Action.RIGHT_CLICK_BLOCK)) {
						
						QueryData.position2.put(player.getName(), blockLocation);
						
						//debugging
						Query.debugMsg(player.getName() + " right-clicked  a block at location x=" + String.valueOf(blockX) + ", y=" + String.valueOf(blockY) +
								", z=" + String.valueOf(blockZ) + "with a " + item.toString());
						
						player.sendMessage(ChatColor.DARK_PURPLE + "Position 2 set at (" + String.valueOf(blockX) + 
								", " + String.valueOf(blockY) + ", " + String.valueOf(blockZ) + ")");
					}
				}
			}
			
			/*
			 * This if statement checks if the item the player clicked with is the same
			 * item specified in the config to query an area.
			 */
			if(item.getTypeId()==queryID) {
				
				//debugging
		    	Query.debugMsg("Item ID used (" + String.valueOf(item.getTypeId()) + ") equals position ID from config file.");
		    	Query.debugMsg("Player from event: " + player.getName());
		    	Query.debugMsg("Item from event: " + item);
		    	Query.debugMsg("Action from event: " + action.toString());
		    	Query.debugMsg("Position ID from config: "  + String.valueOf(posID) + ", Query ID from config: " + String.valueOf(queryID));
		    	
				//If it was a right click
				if(action.equals(Action.RIGHT_CLICK_BLOCK)) {
					
					//debugging
					Query.debugMsg(player.getName() + " right-clicked a block at location x=" + String.valueOf(blockX) + ", y=" + String.valueOf(blockY) +
							", z=" + String.valueOf(blockZ) + "with a " + item.toString() + ".");
					
					/*
					 * Calls the query method and will display the query(if applicable) that the player is STANDING in. 
					 * Also checks for the permission (QueryCommandExecutor, Line 53)
					 */
					
					QueryCommandExecutor.query();
				}
			}
		} catch(Exception e) {
			//The only reason this is here is to prevent error spam messages.
		}
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
