package com.github.thebiologist13;

import java.io.File;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QListCommandExecutor implements CommandExecutor{
	
	private static Player player = null;
	
	private static Query plugin;
	 
	@SuppressWarnings("static-access")
	public QListCommandExecutor(Query plugin) {
		
		this.plugin = plugin;
		
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			player = (Player) sender;
		}
		
		if(cmd.getName().equalsIgnoreCase("qlist")) {
			//The part to check if the sender is a player or not is just where the message is. Just prints to log instead if it is the console.
			
			//Except for 1 check here! :P
			if(player!=null) {
				Query.debugMsg(player.getName() + " used the /qlist command.");
			}
			
			File[] list = getFiles();
			
			//debugging
			Query.debugMsg("Files in " + plugin.getDataFolder() + "/Queries: " + list.toString());
			
			/*
			 * Now that we have an array of the files in the Queries folder, we
			 * can iterate through them.
			 *
			 * We need to know the file's name without an extension so
			 * the query names can be displayed properly.
			 * 
			 */
			
			String queries ="";
			String noExt = "";
			
			if(list.length != 0) {
				for(File f : list) {
					if(isYaml(f)) {
						noExt = getFilesNoExt().get(f);
						
						//These statements make a nice pretty string of all the queries.
				    	if(!queries.equals("")) {
				    		queries = queries + ", " + noExt;
				    		
				    	} else {
				    		queries = queries + noExt;
				    		
				    		//debugging
					    	Query.debugMsg("queries variable = " + queries);
				    	}
					}
				}
			}
			
			if(player==null) {
				//Print it out bro! (This is to the console
				Query.promptMsg("Queries: " + queries);
			} else {
				if(checkPerm("qlist")) {
					//Print it out bro!
					player.sendMessage(ChatColor.DARK_PURPLE + "Queries: " + queries);
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
}
