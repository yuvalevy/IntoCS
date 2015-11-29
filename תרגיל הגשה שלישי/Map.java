import java.awt.Point;

/*
* Add a description in your own words.
*/
public class Map {
	
	private char[][] map;
	
	// Add a description in your own words.
	public Map(char[][] map) {
		this.map = map;
	}
	
	// Add a description in your own words.
	public char[][] getMap() {
		return this.map;
	}
	
	// Add a description in your own words.
	public boolean equals(Map map) {
		
		char[][] newMap = map.getMap();
		
		if (this.map.length != newMap.length) {
			return false;
		}
		
		if (this.map[0].length != newMap[0].length) {
			return false;
		}
		
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				if (this.map[i][j] != newMap[i][j]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	// Add a description in your own words.
	public int numOfColors() {
		String colors = "";
		int counter = 0;
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				
				String newChar = (map[i][j] + "" ).toLowerCase();
				if (!colors.contains(newChar)) {
					counter = counter  +1;
					colors = colors + newChar;
				}
			}
		}
	
		return counter;
	
	}
	
	// Add a description in your own words.
	public int numOfColors(Point p) {
		
		if (!isInMap(p)) {
			return 0;
		}
		
		String colors = "";
		int counter = 0;
		
		int[] diffs = new int[]{-1,0,1};
		
		for (int i = 0; i < diffs.length; i++) {
			for (int j = 0; j < diffs.length; j++) {
				
				int newI = p.getX() + diffs[i];
				int newJ = p.getY() + diffs[j];
				
				if (isInMap(newI, newJ)) {
					String newChar = (this.map[newI][newJ] + "" ).toLowerCase();
					if (!colors.contains(newChar)) {
						counter = counter  +1;
						colors = colors + newChar;
					}
				}
			}
		}
		
		return counter;
	}
	
	public boolean isInMap(int i,int j){
		if (i<0 || j<0) {
			return false;
		}
		
		if (i> this.map.length || j > this.map[0].length) {
			return false;
		}
		
		return true;
	}
	
	public boolean isInMap(Point p){
		return isInMap(p.getX(), p.getY());
	}
	
	// Add a description in your own words.
	public boolean legalNeighbor(Point p1, Point p2) {
		
		// On Map 
		if(!isInMap(p1) || !isInMap(p2)){
			return false;
		}
		
		// Not equals
		if (p1.equals(p2)) {
			return false;
		}
		
		// nigh
		if (Math.abs(p1.getX() - p2.getX()) != 1) {
			return false;
		}
		
		if (Math.abs(p1.getY() - p2.getY()) != 1) {
			return false;
		}
		
		//same color
		
		String color1 = this.map[p1.getX()][p1.getY()] + "";
		String color2 = this.map[p2.getX()][p2.getY()] + "";
		
		if (color1.toLowerCase() != color2.toLowerCase()) {
			return false;
		}
		
		return true;
		
	}
	
	// Add a description in your own words.
	public void fill(Point p,char color) {
		
		boolean inArea = true;
		
		if (inArea) {
			
		}
		
	}
}
