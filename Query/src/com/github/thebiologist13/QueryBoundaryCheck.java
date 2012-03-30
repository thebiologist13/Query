package com.github.thebiologist13;

import org.bukkit.Location;

public class QueryBoundaryCheck {
	
	//Is the Location's X position within x1 and x2?
	public static boolean isWithinX(Location l, int x1, int x2) {
		//Location's X position
		double lx = l.getX();
		//Is x1 or x2 larger?
		boolean isX1Larger = false;
		boolean isX2Larger = false;
		
		if(x1 > x2) {
			isX1Larger = true;
		}
		if(x2 > x1) {
			isX2Larger = true;
		}
		
		//Math! Returns true if Location is within coords, false if otherwise.
		if(isX1Larger) {
			if(lx <= x1 && lx >= x2) {
				return true;
			}
			return false;
		}
		if(isX2Larger) {
			if(lx <= x2 && lx >= x1) {
				return true;
			}
			return false;
		}
		if(!isX1Larger && !isX2Larger) {
			if(lx == x1 && lx == x2) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	//Is the Location's Y position within y1 and y2?
	public static boolean isWithinY(Location l, int y1, int y2) {
		//Location's Y position
		double ly = l.getY();
		//Is y1 or y2 larger?
		boolean isY1Larger = false;
		boolean isY2Larger = false;
		
		if(y1 > y2) {
			isY1Larger = true;
		}
		if(y2 > y1) {
			isY2Larger = true;
		}
		
		//Math! Returns true if Location is within coords, false if otherwise.
		if(isY1Larger) {
			if(ly <= y1 && ly >= y2) {
				return true;
			}
			return false;
		}
		if(isY2Larger) {
			if(ly <= y2 && ly >= y1) {
				return true;
			}
			return false;
		}
		if(!isY1Larger && !isY2Larger) {
			if(ly == y1 && ly == y2) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	//Is the Location's Z position within z1 and z2?
	public static boolean isWithinZ(Location l, int z1, int z2) {
		//Location's Z position
		double lz = l.getZ();
		//Is z1 or z2 larger?
		boolean isZ1Larger = false;
		boolean isZ2Larger = false;
		
		if(z1 > z2) {
			isZ1Larger = true;
		}
		if(z2 > z1) {
			isZ2Larger = true;
		}
		
		//Math! Returns true if Location is within coords, false if otherwise.
		if(isZ1Larger) {
			if(lz <= z1 && lz >= z2) {
				return true;
			}
			return false;
		}
		if(isZ2Larger) {
			if(lz <= z2 && lz >= z1) {
				return true;
			}
			return false;
		}
		if(!isZ1Larger && !isZ2Larger) {
			if(lz == z1 && lz == z2) {
				return true;
			}
			return false;
		}
		return false;
	}
}
