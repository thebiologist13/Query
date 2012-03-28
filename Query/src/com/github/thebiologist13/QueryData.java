package com.github.thebiologist13;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class QueryData {
	
	/*
	 * QueryData is essentially just for holding methods and data
	 * used throughout Query.
	 */
	
	//Position variables
	static HashMap<String, Location> position1 = new HashMap<String, Location>();
	static HashMap<String, Location> position2 = new HashMap<String, Location>();
	
	/*
	 * These unfathomable methods are used to modify the selection area.
	 * First it checks to see which positions (position1 or position2)
	 * is larger on the X and Z axes (Y is not needed). Then, it updates
	 * the locations appropriately with the new position.
	 */
	
	
	/*
	 * HOORAY FOR MATH!!! :D
	 */
	public static void expandSelection(String[] args, Player p) {
		
		for(int i = 0; i<position1.size(); i++) {
			Query.debugMsg(position1.toString());
		}
		for(int i = 0; i<position2.size(); i++) {
			Query.debugMsg(position2.toString());
		}
		
		//Which position on X axis is larger.
		boolean x1 = false, x2 = false;
		//Which position on Y axis is larger.
		boolean y1 = false, y2 = false;
		//Which position on Z axis is larger.
		boolean z1 = false, z2 = false;
		
		if(!position1.containsKey(p.getName()) || !position2.containsKey(p.getName())) {
			Query.debugMsg("Does not contain player name in position data.");
			return;
		}
		if(position1.get(p.getName()).getBlockX() > position2.get(p.getName()).getBlockX()) {
			x1 = true;
		} else if(position1.get(p.getName()).getBlockX() < position2.get(p.getName()).getBlockX()) {
			x2 = true;
		}
		
		if(position1.get(p.getName()).getBlockY() > position2.get(p.getName()).getBlockY()) {
			y1 = true;
		} else if(position1.get(p.getName()).getBlockY() < position2.get(p.getName()).getBlockY()) {
			y2 = true;
		}

		
		if(position1.get(p.getName()).getBlockZ() > position2.get(p.getName()).getBlockZ()) {
			z1 = true;
		} else if(position1.get(p.getName()).getBlockZ() < position2.get(p.getName()).getBlockZ()) {
			z2 = true;
		}
		
		if(args[1].equalsIgnoreCase("north")) {
			// negative on z
			if(z1) {
				Location newLoc = new Location(p.getWorld(), position2.get(p.getName()).getBlockX(), position2.get(p.getName()).getBlockY(), position2.get(p.getName()).getBlockZ() - Integer.parseInt(args[2]));
				position2.put(p.getName(), newLoc);
			} else if(z2) {
				Location newLoc = new Location(p.getWorld(), position1.get(p.getName()).getBlockX(), position1.get(p.getName()).getBlockY(), position1.get(p.getName()).getBlockZ() - Integer.parseInt(args[2]));
				position1.put(p.getName(), newLoc);
			} else {
				Location newLoc = new Location(p.getWorld(), position2.get(p.getName()).getBlockX(), position2.get(p.getName()).getBlockY(), position2.get(p.getName()).getBlockZ() - Integer.parseInt(args[2]));
				position2.put(p.getName(), newLoc);
			}
		} else if(args[1].equalsIgnoreCase("south")) {
			if(z1) {
				Location newLoc = new Location(p.getWorld(), position2.get(p.getName()).getBlockX(), position2.get(p.getName()).getBlockY(), position2.get(p.getName()).getBlockZ() + Integer.parseInt(args[2]));
				position2.put(p.getName(), newLoc);
			} else if(z2) {
				Location newLoc = new Location(p.getWorld(), position1.get(p.getName()).getBlockX(), position1.get(p.getName()).getBlockY(), position1.get(p.getName()).getBlockZ() + Integer.parseInt(args[2]));
				position1.put(p.getName(), newLoc);
			} else {
				Location newLoc = new Location(p.getWorld(), position2.get(p.getName()).getBlockX(), position2.get(p.getName()).getBlockY(), position2.get(p.getName()).getBlockZ() + Integer.parseInt(args[2]));
				position2.put(p.getName(), newLoc);
			}
		} else if(args[1].equalsIgnoreCase("east")) {
			if(x1) {
				Location newLoc = new Location(p.getWorld(), position2.get(p.getName()).getBlockX() + Integer.parseInt(args[2]), position2.get(p.getName()).getBlockY(), position2.get(p.getName()).getBlockZ());
				position2.put(p.getName(), newLoc);
			} else if(x2) {
				Location newLoc = new Location(p.getWorld(), position1.get(p.getName()).getBlockX() + Integer.parseInt(args[2]), position1.get(p.getName()).getBlockY(), position1.get(p.getName()).getBlockZ());
				position1.put(p.getName(), newLoc);
			} else {
				Location newLoc = new Location(p.getWorld(), position2.get(p.getName()).getBlockX() + Integer.parseInt(args[2]), position2.get(p.getName()).getBlockY(), position2.get(p.getName()).getBlockZ());
				position2.put(p.getName(), newLoc);
			}
		} else if(args[1].equalsIgnoreCase("west")) {
			// negative on x
			if(x1) {
				Location newLoc = new Location(p.getWorld(), position2.get(p.getName()).getBlockX() - Integer.parseInt(args[2]), position2.get(p.getName()).getBlockY(), position2.get(p.getName()).getBlockZ());
				position2.put(p.getName(), newLoc);
			} else if(x2) {
				Location newLoc = new Location(p.getWorld(), position1.get(p.getName()).getBlockX() - Integer.parseInt(args[2]), position1.get(p.getName()).getBlockY(), position1.get(p.getName()).getBlockZ());
				position1.put(p.getName(), newLoc);
			} else {
				Location newLoc = new Location(p.getWorld(), position2.get(p.getName()).getBlockX() - Integer.parseInt(args[2]), position2.get(p.getName()).getBlockY(), position2.get(p.getName()).getBlockZ());
				position2.put(p.getName(), newLoc);
			}
		} else if(args[1].equalsIgnoreCase("up")) {
			if(y1) {
				Location newLoc = new Location(p.getWorld(), position1.get(p.getName()).getBlockX(), position1.get(p.getName()).getBlockY() + Integer.parseInt(args[2]), position1.get(p.getName()).getBlockZ());
				position1.put(p.getName(), newLoc);
			} else if(y2) {
				Location newLoc = new Location(p.getWorld(), position2.get(p.getName()).getBlockX(), position2.get(p.getName()).getBlockY() + Integer.parseInt(args[2]), position2.get(p.getName()).getBlockZ());
				position1.put(p.getName(), newLoc);
			} else {
				Location newLoc = new Location(p.getWorld(), position1.get(p.getName()).getBlockX(), position1.get(p.getName()).getBlockY() + Integer.parseInt(args[2]), position1.get(p.getName()).getBlockZ());
				position1.put(p.getName(), newLoc);
			}
		} else if(args[1].equalsIgnoreCase("down")) {
			if(y1) {
				Location newLoc = new Location(p.getWorld(), position2.get(p.getName()).getBlockX(), position2.get(p.getName()).getBlockY() - Integer.parseInt(args[2]), position2.get(p.getName()).getBlockZ());
				position2.put(p.getName(), newLoc);
			} else if(y2) {
				Location newLoc = new Location(p.getWorld(), position1.get(p.getName()).getBlockX(), position1.get(p.getName()).getBlockY() - Integer.parseInt(args[2]), position1.get(p.getName()).getBlockZ());
				position1.put(p.getName(), newLoc);
			} else {
				Location newLoc = new Location(p.getWorld(), position2.get(p.getName()).getBlockX(), position2.get(p.getName()).getBlockY() - Integer.parseInt(args[2]), position2.get(p.getName()).getBlockZ());
				position2.put(p.getName(), newLoc);
			}
		} else {
			p.sendMessage(ChatColor.DARK_RED + "That isn't a valid direction! Use north, south, east, west, up, or down.");
		}
	}
	
	public static void contractSelection(String[] args, Player p) {

		//Which position on X axis is larger.
		boolean x1 = false, x2 = false;
		//Which position on Z axis is larger.
		boolean z1 = false, z2 = false;
		
		if(position1.get(p.getName()).getBlockX() > position2.get(p.getName()).getBlockX()) {
			x1 = true;
		} else if(position1.get(p.getName()).getBlockX() < position2.get(p.getName()).getBlockX()) {
			x2 = true;
		}

		
		if(position1.get(p.getName()).getBlockZ() > position2.get(p.getName()).getBlockZ()) {
			z1 = true;
		} else if(position1.get(p.getName()).getBlockZ() < position2.get(p.getName()).getBlockZ()) {
			z2 = true;
		}
		
		if(args[1].equalsIgnoreCase("north")) {
			// negative on z
			if(z1) {
				Location newLoc = new Location(p.getWorld(), position1.get(p.getName()).getBlockX(), position1.get(p.getName()).getBlockY(), position1.get(p.getName()).getBlockZ() - Integer.parseInt(args[2]));
				position1.put(p.getName(), newLoc);
			} else if(z2) {
				Location newLoc = new Location(p.getWorld(), position2.get(p.getName()).getBlockX(), position2.get(p.getName()).getBlockY(), position2.get(p.getName()).getBlockZ() - Integer.parseInt(args[2]));
				position2.put(p.getName(), newLoc);
			} else {
				Location newLoc = new Location(p.getWorld(), position1.get(p.getName()).getBlockX(), position1.get(p.getName()).getBlockY(), position1.get(p.getName()).getBlockZ() - Integer.parseInt(args[2]));
				position1.put(p.getName(), newLoc);
			}
		} else if(args[1].equalsIgnoreCase("south")) {
			if(z1) {
				Location newLoc = new Location(p.getWorld(), position1.get(p.getName()).getBlockX(), position1.get(p.getName()).getBlockY(), position1.get(p.getName()).getBlockZ() + Integer.parseInt(args[2]));
				position1.put(p.getName(), newLoc);
			} else if(z2) {
				Location newLoc = new Location(p.getWorld(), position2.get(p.getName()).getBlockX(), position2.get(p.getName()).getBlockY(), position2.get(p.getName()).getBlockZ() + Integer.parseInt(args[2]));
				position2.put(p.getName(), newLoc);
			} else {
				Location newLoc = new Location(p.getWorld(), position1.get(p.getName()).getBlockX(), position1.get(p.getName()).getBlockY(), position1.get(p.getName()).getBlockZ() + Integer.parseInt(args[2]));
				position1.put(p.getName(), newLoc);
			}
		} else if(args[1].equalsIgnoreCase("east")) {
			if(x1) {
				Location newLoc = new Location(p.getWorld(), position1.get(p.getName()).getBlockX() + Integer.parseInt(args[2]), position1.get(p.getName()).getBlockY(), position1.get(p.getName()).getBlockZ());
				position1.put(p.getName(), newLoc);
			} else if(x2) {
				Location newLoc = new Location(p.getWorld(), position2.get(p.getName()).getBlockX() + Integer.parseInt(args[2]), position2.get(p.getName()).getBlockY(), position2.get(p.getName()).getBlockZ());
				position2.put(p.getName(), newLoc);
			} else {
				Location newLoc = new Location(p.getWorld(), position1.get(p.getName()).getBlockX() + Integer.parseInt(args[2]), position1.get(p.getName()).getBlockY(), position1.get(p.getName()).getBlockZ());
				position1.put(p.getName(), newLoc);
			}
		} else if(args[1].equalsIgnoreCase("west")) {
			// negative on x
			if(x1) {
				Location newLoc = new Location(p.getWorld(), position1.get(p.getName()).getBlockX() - Integer.parseInt(args[2]), position1.get(p.getName()).getBlockY(), position1.get(p.getName()).getBlockZ());
				position1.put(p.getName(), newLoc);
			} else if(x2) {
				Location newLoc = new Location(p.getWorld(), position2.get(p.getName()).getBlockX() - Integer.parseInt(args[2]), position2.get(p.getName()).getBlockY(), position2.get(p.getName()).getBlockZ());
				position2.put(p.getName(), newLoc);
			} else {
				Location newLoc = new Location(p.getWorld(), position1.get(p.getName()).getBlockX() - Integer.parseInt(args[2]), position1.get(p.getName()).getBlockY(), position1.get(p.getName()).getBlockZ());
				position1.put(p.getName(), newLoc);
			}
		} else if(args[1].equalsIgnoreCase("up")) {
			Location newLoc = new Location(p.getWorld(), position1.get(p.getName()).getBlockX(), position1.get(p.getName()).getBlockY() + Integer.parseInt(args[2]), position1.get(p.getName()).getBlockZ());
			position1.put(p.getName(), newLoc);
		} else if(args[1].equalsIgnoreCase("down")) {
			Location newLoc = new Location(p.getWorld(), position1.get(p.getName()).getBlockX(), position1.get(p.getName()).getBlockY() - Integer.parseInt(args[2]), position1.get(p.getName()).getBlockZ());
			position1.put(p.getName(), newLoc);
		} else {
			p.sendMessage(ChatColor.DARK_RED + "That isn't a valid direction! Use north, south, east, west, up, or down.");
		}
	}
}
