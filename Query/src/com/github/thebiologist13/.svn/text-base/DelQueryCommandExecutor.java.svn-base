package com.github.thebiologist13;

import java.io.File;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelQueryCommandExecutor implements CommandExecutor{
	
	private static Query plugin;
	
	private static Player player = null;
	
	@SuppressWarnings("static-access")
	public DelQueryCommandExecutor(Query plugin) {
		
		this.plugin = plugin;
		
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player) {
			player = (Player) sender;
		}
		
		if(cmd.getName().equalsIgnoreCase("delquery")) {
			if(player==null) {
				
				//debugging
				Query.debugMsg("Console used the /delquery command. Argument 1: " + args[0]);
				
			} else {
				
				//debugging
				Query.debugMsg(ChatColor.DARK_PURPLE + player.getName() + " used the /delquery command. Argument 1: " + args[0]);
				
				File[] list = getFiles();
				
				//debugging
				Query.debugMsg("Files in " + plugin.getDataFolder() + "/Queries: " + list.toString());
				
				boolean match = false;
				
				if(checkPerm("delquery")) {
					Query.debugMsg("Has permission.");
					if(list.length != 0) {
						for(File f : list) {
							
							Query.debugMsg("Iterating through " + f.getName());
							
							if(isYaml(f)) {
								
								String noExt = getFilesNoExt().get(f);
								
								if(args[0].equalsIgnoreCase(noExt)) {
									
						    		//debugging
							    	Query.debugMsg("File match found for delete argument.");
							    	
						    		//Sets that a match was found
						    		match = true;
						    		
						    		/*
						    		 * Actually deletes the file of the specified query
						    		 * This comes after the message because if it was deleted,
						    		 * Java wouldn't be able to get the name of the file
						    		 * anymore because the file WOULDN'T EXIST!
						    		 */
						    		
						    		f.delete();

						    		//Tells the player that the file was deleted.
						    		player.sendMessage(ChatColor.DARK_PURPLE + "Query " + noExt + " was sucessfully deleted.");
						    		
						    		Query.promptMsg("Query " + noExt + " was sucessfully deleted by " + player.getName());
						    		
						    		//Break the loop because the query was found and deleted.
						    		break;
						    	}
							}
						}
					}
				}
				if(!match) {
					player.sendMessage(ChatColor.DARK_RED + "Query " + args[0] + " was not found.");
				}
			}
			return true;
		}
		return false;	
	}
	
	//Gets a list of all the files in the Query data folder
	public static File[] getFiles() {
		String path = plugin.getDataFolder() + "/Queries";
		File folder = new File(path);
		File[] list = folder.listFiles();
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
