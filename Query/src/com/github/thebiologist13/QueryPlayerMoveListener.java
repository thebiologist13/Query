package com.github.thebiologist13;

import java.io.File;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class QueryPlayerMoveListener implements Listener {
	
	private static Query plugin;
	
	private static Player player;
	
	public QueryPlayerMoveListener(Query plugin) {
		QueryPlayerMoveListener.plugin = plugin;
	}
	
	HashMap<Player, Boolean> enteredArea = new HashMap<Player, Boolean>();
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		
		player = event.getPlayer();
		
		File[] list = getFiles();
		
		HashMap<String, Integer> positions = new HashMap<String, Integer>();
		
		if(checkPerm("query")) {
			for(File f : list) {
				if(isYaml(f)) {
					FileConfiguration yaml = YamlConfiguration.loadConfiguration(f);
					
					String noExt = getFilesNoExt().get(f);
					
					positions = getPositions(f);
					
					Location pos1 = new Location(player.getWorld(), positions.get("pos1x"), positions.get("pos1y"), positions.get("pos1z"));
					Location pos2 = new Location(player.getWorld(), positions.get("pos2x"), positions.get("pos2y"), positions.get("pos2z"));
					
					if(isWithin(event.getTo(), pos1, pos2)) {
						enteredArea.put(player, true);
					} else if(!isWithin(event.getTo(), pos1, pos2)) {
						enteredArea.put(player, false);
					}
					
					if(!isWithin(event.getFrom(), pos1, pos2) && enteredArea.get(player) && inWorld(f)) {
						player.sendMessage(ChatColor.DARK_PURPLE + noExt + ": " + yaml.getString(noExt + ".onEnter"));
					} else if(isWithin(event.getFrom(), pos1, pos2) && !enteredArea.get(player) && inWorld(f)) {
						player.sendMessage(ChatColor.DARK_PURPLE + noExt + ": " + yaml.getString(noExt + ".onExit"));
					}
				}
			}
		}
	}
	
	//My way to check a permission
	public static boolean checkPerm(String perm) {
		if(player != null) {
			if(player.hasPermission("query." + perm)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}	
	}
	
	//Gets a list of all the files in the Query data folder
	public static File[] getFiles() {
		String path = plugin.getDataFolder() + "/Queries";
		File folder = new File(path);
		File[] list = folder.listFiles();
		return list;
	}
	
	//Gets all the files in the Query data folder as a string (for debugging)
	public static String getFilesAsString() {
		String list = "";
		for(File f : getFiles()) {
			if(f.isFile()) {
				list += " - " + f.getName();
			}
		}
		Query.debugMsg("Files in data folder: " + list);
		return list;
	}
	
	//Gets the names of the files in the Query data folder without extensions
	public static HashMap<File, String> getFilesNoExt() {
		HashMap<File, String> noExt = new HashMap<File, String>();
		File[] list = getFiles();
		for(int i = 0; i < list.length; i++) {
			try {
				if(list[i].isFile()) {
					int index = list[i].getName().lastIndexOf(".");
					if (index>0 && index <= list[i].getName().length() - 2 ) {
						noExt.put(list[i], list[i].getName().substring(0, index));
					}
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				Query.debugMsg("Caught ArrayIndexOutOfBoundsException in getFilesNoExt().");
				return null;
			} 
		}
		if(noExt.isEmpty()) {
			return null;
		} else {
			return noExt;
		}
	}
	
	//Checks to see if a file is a YAML file
	public static boolean isYaml(File f) {
		int index = f.getName().lastIndexOf('.');
		if(f.getName().substring(index).equals(".yml")) {
			return true;
		} else {
			return false;
		}
	}
	
	//Checks if player is in world
	public static boolean inWorld(File f) {
		HashMap<File, String> noExtMap = getFilesNoExt();
		String noExt = noExtMap.get(f);
		FileConfiguration yaml = YamlConfiguration.loadConfiguration(f);
		String worldName = "";
		
		worldName = yaml.getString(noExt + ".world");

		World playerIn = player.getWorld();
		
		if(playerIn.getName().equals(worldName)) {
			return true;
		}
		
		return false;
	}
	
	//Gets the positions stored in a YAML file
	public static HashMap<String, Integer> getPositions(File f) {
		HashMap<File, String> noExtMap = getFilesNoExt();
		String noExt = noExtMap.get(f);
		HashMap<String, Integer> positions = new HashMap<String, Integer>();
		FileConfiguration yaml = YamlConfiguration.loadConfiguration(f);
		
		positions.put("pos1x", yaml.getInt(noExt + ".pos1x"));
		positions.put("pos1y", yaml.getInt(noExt + ".pos1y"));
		positions.put("pos1z", yaml.getInt(noExt + ".pos1z"));
		positions.put("pos2x", yaml.getInt(noExt + ".pos2x"));
		positions.put("pos2y", yaml.getInt(noExt + ".pos2y"));
		positions.put("pos2z", yaml.getInt(noExt + ".pos2z"));
		
		return positions;
	}

	
	//Checks if a player is within two coordinates
	public static boolean isWithin(Location l, Location l1, Location l2) {
			if(QueryBoundaryCheck.isWithinX(l, l1.getBlockX(), l2.getBlockX()) && 
					QueryBoundaryCheck.isWithinY(l, l1.getBlockY(), l2.getBlockY()) && 
					QueryBoundaryCheck.isWithinZ(l, l1.getBlockZ(), l2.getBlockZ())) {
				return true;
			} else {
				return false;
			}
		}
}
