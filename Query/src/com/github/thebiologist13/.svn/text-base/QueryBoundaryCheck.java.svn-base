package com.github.thebiologist13;

import org.bukkit.entity.Player;

public class QueryBoundaryCheck {
	
	//Is the player's X position within x1 and x2?
	public static boolean isWithinX(Player p, int x1, int x2) {
		//Player's X position
		int px = p.getLocation().getBlockX();
		//Is x1 or x2 larger?
		boolean isX1Larger = false;
		boolean isX2Larger = false;
		
		if(x1 > x2) {
			isX1Larger = true;
		}
		if(x2 > x1) {
			isX2Larger = true;
		}
		
		//Math! Returns true if player is within coords, false if otherwise.
		if(isX1Larger) {
			if(px <= x1 && px >= x2) {
				return true;
			}
			return false;
		}
		if(isX2Larger) {
			if(px <= x2 && px >= x1) {
				return true;
			}
			return false;
		}
		if(!isX1Larger && !isX2Larger) {
			if(px == x1 && px == x2) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	//Is the player's Y position within y1 and y2?
	public static boolean isWithinY(Player p, int y1, int y2) {
		//Player's Y position
		int py = p.getLocation().getBlockY();
		//Is y1 or y2 larger?
		boolean isY1Larger = false;
		boolean isY2Larger = false;
		
		if(y1 > y2) {
			isY1Larger = true;
		}
		if(y2 > y1) {
			isY2Larger = true;
		}
		
		//Math! Returns true if player is within coords, false if otherwise.
		if(isY1Larger) {
			if(py <= y1 && py >= y2) {
				return true;
			}
			return false;
		}
		if(isY2Larger) {
			if(py <= y2 && py >= y1) {
				return true;
			}
			return false;
		}
		if(!isY1Larger && !isY2Larger) {
			if(py == y1 && py == y2) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	//Is the player's Z position within z1 and z2?
	public static boolean isWithinZ(Player p, int z1, int z2) {
		//Player's Z position
		int pz = p.getLocation().getBlockZ();
		//Is z1 or z2 larger?
		boolean isZ1Larger = false;
		boolean isZ2Larger = false;
		
		if(z1 > z2) {
			isZ1Larger = true;
		}
		if(z2 > z1) {
			isZ2Larger = true;
		}
		
		//Math! Returns true if player is within coords, false if otherwise.
		if(isZ1Larger) {
			if(pz <= z1 && pz >= z2) {
				return true;
			}
			return false;
		}
		if(isZ2Larger) {
			if(pz <= z2 && pz >= z1) {
				return true;
			}
			return false;
		}
		if(!isZ1Larger && !isZ2Larger) {
			if(pz == z1 && pz == z2) {
				return true;
			}
			return false;
		}
		return false;
	}
}
