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

public class QMsgCommandExecutor implements CommandExecutor {

	private static Query plugin;
	
	private static Player player;
	
	public QMsgCommandExecutor(Query plugin) {
		
		QMsgCommandExecutor.plugin = plugin;
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			player = (Player) sender;
		}
		
		if(cmd.getName().equalsIgnoreCase("qmsg")) {
			boolean queryFound = false;
			
			if(player == null) {
				HashMap<File, String> noExtMap = getFilesNoExt();
				File[] list = getFiles();
				
				for(File f : list) {
					
					String noExt = noExtMap.get(f);
					
					if(isYaml(f)) {
						if(noExt.equalsIgnoreCase(args[2])) {
							if(args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("-s")) {
								setMsg(args, f, noExt);
							}
							
							if(args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("-rm")) {
								removeMsg(args, f, noExt);
							}
							queryFound = true;
						}
					}
				}
				if(!queryFound) {
					Query.promptMsg("No Query with that name.");
				}
				return true;
			}
			
			if(checkPerm("qmsg")) {
				
				HashMap<File, String> noExtMap = getFilesNoExt();
				File[] list = getFiles();
				
				for(File f : list) {
					
					String noExt = noExtMap.get(f);
					
					if(isYaml(f)) {
						if(noExt.equalsIgnoreCase(args[2])) {
							if(args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("-s")) {
								setMsg(args, f, noExt);
							}
							
							if(args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("-rm")) {
								removeMsg(args, f, noExt);
							}
							queryFound = true;
						}
					}
				}
			}
			if(!queryFound) {
				Query.promptMsg("No Query with that name.");
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
	
	//Sets a message
	public static void setMsg(String[] args, File f, String noExt) {
		
		FileConfiguration yaml = YamlConfiguration.loadConfiguration(f);
		
		String msg = "";
		
		for(int i = 3; i < args.length; i++) {
			if(i==3) {
				msg = msg + args[i];
			} else {
				msg = msg + " " + args[i];
			}
		}
		if(args[1].equalsIgnoreCase("enter") || args[1].equalsIgnoreCase("-en")) {
			yaml.set(noExt + ".onEnter", msg);
		}
		if(args[1].equalsIgnoreCase("exit") || args[1].equalsIgnoreCase("-ex")) {
			yaml.set(noExt + ".onExit", msg);
		}
		
		try {
			yaml.save(f);
			
			Query.debugMsg(player.getName() + " set a player move message for Query: " + noExt);
			Query.promptMsg(player.getName() + " set a player move message");
			
			player.sendMessage(ChatColor.DARK_PURPLE + "Your message has been saved!");
		} catch(IOException e) {
			Query.debugMsg(player.getName() + " attempted to set a player move message for Query: " + noExt + ", but an IOException occured.");
			Query.promptMsg(player.getName() + " attempted to set a player move message, but it failed to save.");
			
			player.sendMessage(ChatColor.DARK_RED + "Your message failed to save!");
		}
	}
	
	//Removes a message
	public static void removeMsg(String[] args, File f, String noExt) {
		
		FileConfiguration yaml = YamlConfiguration.loadConfiguration(f);
		
		if(args[1].equalsIgnoreCase("enter") || args[1].equalsIgnoreCase("-en")) {
			yaml.set(noExt + ".onEnter", "");
		}
		if(args[1].equalsIgnoreCase("exit") || args[1].equalsIgnoreCase("-ex")) {
			yaml.set(noExt + ".onExit", "");
		}
		
		try {
			yaml.save(f);
			
			Query.debugMsg(player.getName() + " removed a player move message for Query: " + noExt);
			Query.promptMsg(player.getName() + " removed a player move message");
			
			player.sendMessage(ChatColor.DARK_PURPLE + "Message successfully removed!");
		} catch(IOException e) {
			Query.debugMsg(player.getName() + " attempted to remove a player move message for Query: " + noExt + ", but an IOException occured.");
			Query.promptMsg(player.getName() + " attempted to remove a player move message, but it failed to save.");
			
			player.sendMessage(ChatColor.DARK_RED + "Failed to remove message!");
		}
	}
}
