package com.github.thebiologist13;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class QueryDescCommandExecutor implements CommandExecutor{
	
	private static Player player = null;
	
	private static Query plugin;
	 
	@SuppressWarnings("static-access")
	public QueryDescCommandExecutor(Query plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		//Variable for the plugin's config file
		FileConfiguration config = Query.config;
		//Variable for the max lines allowed
		int maxLines = config.getInt("maxLines");
		
		if(sender instanceof Player) {
			player = (Player) sender;
		}
		
		if(cmd.getName().equalsIgnoreCase("querydesc")) {
			if(player==null) {
				Query.debugMsg("You can only use the command: '" + cmd.getName() + "' in-game.");
				return true;
			}
			
			//debugging
			Query.debugMsg(ChatColor.DARK_PURPLE + player.getName() + " used the /querydesc command. Argument 1: " + args[0] + ", Argument 2: " + args[1] + ", Argument 3: " + args[2]);
			
			if(checkPerm("querydesc")) {
				
				/*
				 * This block of code gets all of the files in the queries directory of the plugin.
				 * 
				 * First, the path for the directory: <the plugin's data folder>/Queries
				 */
				
				File[] list = getFiles();
				
				//debugging
				Query.debugMsg("Files in " + plugin.getDataFolder() + "\\Queries: " + list.toString());
				
				/*
				 * Now that we have an array of the files in the Queries folder, we
				 * can iterate through them.
				 * 
				 * Each one it iterates through will need to be checked for the right title.
				 * If it has the right title, then it will save the description to the
				 * query's yaml file in this format:
				 * 
				 * desc:
				 *   lineX: description
				 * 
				 * UNLESS, the line number (args[1]) exceeds the maximum allowed lines in 
				 * the plugin config. Then, it will send a message telling the player.
				 */
				
				boolean match = false;
				
				if(list.length != 0) {
					for(File f : list) {
						if(isYaml(f)) {
							String noExt = getFilesNoExt().get(f);
							if(args[0].equalsIgnoreCase(noExt)) {
								
					    		//debugging
						    	Query.debugMsg("Match found for " + args[0] + ": " + f.getName());
						    	
					    		//Sets that a match was found
					    		match = true;
					    		
					    		//This statement checks that the line specified doesn't exceed the maximum length from the config.
					    		if(Integer.parseInt(args[1]) <= maxLines) {
					    			
					    			//debugging
							    	Query.debugMsg("Within line limit with max value at " + maxLines + ", entered value: " + args[1]);
							    	
					    			/* Variable for the description.
					    			 * 
					    			 * Why can't I do this below in yaml.set? Well, that is because Bukkit thinks that
					    			 * every word in the description is a separate argument! So, I iterate through
					    			 * every argument after args[1] (which is the line number) and add it to the
					    			 * description (String desc). This way, every part of the description is included.
					    			 */
							    	
					    			String desc = "";
					    			
					    			//The i variable is already in use. So, m I guess?
					    			for(int m = 2; m<args.length; m++) {
					    				desc = desc + " " + args[m];
					    			}
					    			
					    			//debugging
							    	Query.debugMsg("Description to be set: " + desc + "- Setting to:" + f.getName() + "- File's path: " + f.getPath() + "- Path to set to: " + noExt + ".desc.line" + args[1]);
							    	
					    			//Loading the YamlConfiguration
					    			FileConfiguration yaml = YamlConfiguration.loadConfiguration(f);
					    			//Setting the description
					    			yaml.set(noExt + ".desc.line" + args[1], desc);
					    			
					    			try {
										yaml.save(f);
										
										//Send a success msg.
										player.sendMessage(ChatColor.DARK_PURPLE + "Query description set!");
										
										Query.promptMsg("Description set for " + noExt + " by " + player.getName());
									} catch (IOException e) {
										//OH NOES! Tell the player and print out to console if an error occurred
										Query.debugMsg("IO Exception when saving new query. " + e.getMessage());
										Query.severeMsg("Error saving description. ");
										player.sendMessage(ChatColor.DARK_RED + "Error saving description.");
									}
					    			
					    			//debugging
							    	Query.debugMsg("Description set to YAML file " + f.getName());
					    			
					    		} else {
					    			//If the player puts and invalid line value in args[1], tell them 
					    			player.sendMessage(ChatColor.DARK_RED + args[1] + " is an invalid value for a line. Please put a value between 1 and " + String.valueOf(maxLines));
					    		}
					    		//Break the loop if the query was found.
					    		break;
					    	}
						}
					}
				}
				
				//If no match for the argument was found
				if(!match) {
					//Tell the player and give some options to solve the problem.
					player.sendMessage(ChatColor.DARK_RED + "Query " + args[0] + " was not found. Is it in a different directory, or was it misspelled?");
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
}
