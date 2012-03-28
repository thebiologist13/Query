package com.github.thebiologist13;

import java.io.File;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class QueryCommandExecutor implements CommandExecutor {
	
	private static Query plugin;

	private static Player player = null;
	 
	public QueryCommandExecutor(Query plugin) {
		
		QueryCommandExecutor.plugin = plugin;
		
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			player = (Player) sender;
		}
		
		if(cmd.getName().equalsIgnoreCase("query")) {
			
			if(player == null) {
				Query.debugMsg("You can only use the command: '" + cmd.getName() + "' in-game.");
				return true;
			} 
			
			Query.debugMsg(player.getName() + " used /query.");
			
			//Call the query method
			query();
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
			Query.debugMsg("No files without extensions to return.");
			return null;
		} else {
			Query.debugMsg("Files without extensions returned.");
			return noExt;
		}
	}
	
	//Checks to see if a file is a YAML file
	public static boolean isYaml(File f) {
		int index = f.getName().lastIndexOf('.');
		if(f.getName().substring(index).equals(".yml")) {
			Query.debugMsg("File " + f.getName() + " is a YAML file.");
			return true;
		} else {
			Query.debugMsg("File " + f.getName() + " is not a YAML file.");
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
		Query.debugMsg("World name loaded from YAML: " + worldName);

		World playerIn = player.getWorld();
		Query.debugMsg("World player is in: " + playerIn.getName());
		
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
		
		Query.debugMsg("Loaded positions from Yaml.");
		return positions;
	}
	
	//Gets the descriptions in a YAML file
	public static HashMap<String, String> getDescription(File f) {
		HashMap<String, String> description = new HashMap<String, String>();
		HashMap<File, String> noExtMap = getFilesNoExt();
		String noExt = noExtMap.get(f);
		FileConfiguration yaml = YamlConfiguration.loadConfiguration(f);
		
		for(int i = 1; i <= Query.config.getInt("maxLines"); i++) {
			if(yaml.contains(noExt + ".desc.line" + i)) {
				description.put("line" + i, yaml.getString(noExt + ".desc.line" + i));
			} 
		}
		
		if(description.isEmpty()) {
			return null;
		} else {
			return description;
		}
	}
	
	//Checks if a player is within two coordinates
	public static boolean isWithin(Player p, Location l1, Location l2) {
		if(QueryBoundaryCheck.isWithinX(player, l1.getBlockX(), l2.getBlockX()) && 
				QueryBoundaryCheck.isWithinY(player, l1.getBlockY(), l2.getBlockY()) && 
				QueryBoundaryCheck.isWithinZ(player, l1.getBlockZ(), l2.getBlockZ())) {
			return true;
		} else {
			return false;
		}
	}
	
	//Actually runs query
	public static void query() {
		boolean queryFound = false;
		File[] list = getFiles();
		HashMap<String, Integer> positions = new HashMap<String, Integer>();
		getFilesAsString();
		if(checkPerm("query")) {
			Query.debugMsg("Has permission.");
			if(list.length != 0) {
				for(File f : list) {
					Query.debugMsg("Iterating through " + f.getName());
					if(isYaml(f)) {
						Query.debugMsg(f.getName() + " is YAML");
						HashMap<String, String> description = new HashMap<String, String>();
						
						positions = getPositions(f);
						
						Location pos1 = new Location(player.getWorld(), positions.get("pos1x"), positions.get("pos1y"), positions.get("pos1z"));
						Location pos2 = new Location(player.getWorld(), positions.get("pos2x"), positions.get("pos2y"), positions.get("pos2z"));
						
						if(isWithin(player, pos1, pos2) && inWorld(f)) {
							
							Query.debugMsg("Is within.");
							description = getDescription(f);
							
							if(description == null) {
								
								Query.debugMsg("No description set for" + getFilesNoExt().get(f));
								player.sendMessage(ChatColor.DARK_PURPLE + "No description has been set for query " + getFilesNoExt().get(f) + " yet.");
								
								if(checkPerm("querydesc")) {
									player.sendMessage(ChatColor.DARK_PURPLE + "You can set this query's description with /querydesc <name of query> <line number> <description>");
								}
								
							} else {
								
								Query.debugMsg("Description is set for query.");
								
								for(int i = 1; i <= Query.config.getInt("maxLines"); i++) {
									
									Query.debugMsg("Looking for description.");
									
									if(description.containsKey("line" + i)) {
										
										Query.debugMsg("Contains path " + getFilesNoExt().get(f) + ".desc.line" + i);
										
										if(i == 1) {
											player.sendMessage(ChatColor.DARK_PURPLE + getFilesNoExt().get(f) + ": " + description.get("line" + i));
										} else {
											player.sendMessage(ChatColor.DARK_PURPLE + description.get("line" + i));
										}
										
									} else {
										Query.debugMsg("Does not contain path " + getFilesNoExt().get(f) + "desc.line" + i);
									}
								}
							}
							queryFound = true;
						}
					}
				}
				if(!queryFound) {
					player.sendMessage(ChatColor.DARK_PURPLE + "You are not in a query area at the moment.");
				}
			}
		}
	}
}
