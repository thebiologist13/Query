package com.github.thebiologist13;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public class Query extends JavaPlugin{

	protected static FileConfiguration config;
	
	public static boolean debug = false;
	
	static Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable() {	
		//Player Listener
		getServer().getPluginManager().registerEvents(new QueryPlayerListener(this), this);
		
		//Commands
		QueryCommandExecutor queryExecutor = new QueryCommandExecutor(this);
		QListCommandExecutor qListExecutor = new QListCommandExecutor(this);
		QDebugCommandExecutor qDebugExecutor = new QDebugCommandExecutor(this);
		SetQueryCommandExecutor setQueryCommandExecutor = new SetQueryCommandExecutor(this);
		DelQueryCommandExecutor delQueryExecutor = new DelQueryCommandExecutor(this);
		QueryDescCommandExecutor queryDescExecutor = new QueryDescCommandExecutor(this);
		QHelpCommandExecutor qHelpExecutor = new QHelpCommandExecutor(this);
		getCommand("query").setExecutor(queryExecutor);
		getCommand("setquery").setExecutor(setQueryCommandExecutor);
		getCommand("qdebug").setExecutor(qDebugExecutor);
		getCommand("qlist").setExecutor(qListExecutor);
		getCommand("delquery").setExecutor(delQueryExecutor);
		getCommand("querydesc").setExecutor(queryDescExecutor);
		getCommand("qhelp").setExecutor(qHelpExecutor);
		getCommand("qpos1").setExecutor(setQueryCommandExecutor);
		getCommand("qpos2").setExecutor(setQueryCommandExecutor);
		getCommand("qarea").setExecutor(setQueryCommandExecutor);
		
		/*
		 * Config
		 * 
		 * This creates a new config file if none has been generated yet.
		 */

		config = this.getConfig();
		config.options().header("Query Configuration");
		config.addDefault("positionID", 371);
		config.addDefault("queryID", 370);
		config.addDefault("maxLines", 8);
		config.addDefault("debugOn", false);
		config.options().copyDefaults(true);
		this.saveConfig(); 
		
		debug = config.getBoolean("debugOn");
		
		log.info("Query version 2.0 by thebiologist13 has been enabled!");
	}
	
	public void onDisable() {
		log.info("Query version 2.0 by thebiologist13 has been disabled!");
	}
	
	//Prints a debug message
	public static void debugMsg(String msg) {
		if(Query.debug) {
			log.info("[QDEBUG] " + msg);
		}
	}
	
	//Prints a [SEVERE] message
	public static void severeMsg(String msg) {
		log.severe(msg);
	}
	
	//Prints a message to console
	public static void promptMsg(String msg) {
		log.info(msg);
	}
}
