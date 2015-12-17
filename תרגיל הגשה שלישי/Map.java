/*
* Add a description in your own words.
*/
public class Map {

	private char[][] map;

	// Add a description in your own words.
	public Map(char[][] map) {

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {

				char addTo;

				if (('a' <= map[i][j]) && ('z' >= map[i][j])) {
					addTo = map[i][j];
				} else if (('A' <= map[i][j]) && ('Z' >= map[i][j])) {
					// Lowering the case
					addTo = (char) ((map[i][j] - 'A') + 'a');
				} else {
					// is not a valid char
					addTo = 'z';
				}

				map[i][j] = addTo;
			}
		}

		this.map = map;
	}

	public static void main(String[] args) {

		char[][] map = new char[][] { new char[] { 'b', 'b', 'b', 'r', 'b', 'g' },
				new char[] { 'g', 'g', 'r', 'y', 'b', 'g' }, new char[] { 'g', 'r', 'r', 'b', 'b', 'g' },
				new char[] { 'g', 'g', 'p', 'p', 'y', 'y' }, new char[] { 'g', 'g', 'p', 'p', 'y', 'y' },
				new char[] { 'p', 'p', 'p', 'p', 'p', 'y' }, };

		Map m = new Map(map);
		printMap(m);

		m.fill(new Point(2, 3), '*');

		printMap(m);

	}

	public static void printMap(Map map) {
		char[][] m = map.getMap();
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {

				System.out.print("[" + m[i][j] + "] ");
			}
			System.out.println();
		}
		System.out.println("----------------");

	}

	// Add a description in your own words.
	public boolean equals(Map map) {

		// in order not to call map.getMap() many times
		char[][] comparedMap = map.getMap();

		if (this.map.length != comparedMap.length) {
			return false;
		}

		if (this.map[0].length != comparedMap[0].length) {
			return false;
		}

		// in the constructor, all chars are in lower case
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				if (this.map[i][j] != comparedMap[i][j]) {
					return false;
				}
			}
		}

		return true;
	}

	// Add a description in your own words.
	public void fill(Point p, char color) {

		boolean[][] mapToPiant = new boolean[this.map.length][this.map.length];
		getAllArea(p, mapToPiant);

		for (int i = 0; i < mapToPiant.length; i++) {
			for (int j = 0; j < mapToPiant.length; j++) {
				if (mapToPiant[i][j]) {
					this.map[i][j] = color;
				}
			}
		}

	}

	public void getAllArea(Point p, boolean[][] mapToPiant) {

		mapToPiant[p.getX()][p.getY()] = true;

		int[] diffs = new int[] { -1, 0, 1 };

		for (int i = 0; i < diffs.length; i++) {
			for (int j = 0; j < diffs.length; j++) {

				int newI = p.getX() + diffs[i];
				int newJ = p.getY() + diffs[j];

				if (isInMap(newI, newJ)) {

					Point newPoint = new Point(newI, newJ);

					// not summon if point is already painted
					if (!mapToPiant[newPoint.getX()][newPoint.getY()]) {

						if (!p.equals(newPoint) && legalNeighbor(p, newPoint)) {

							// getAllArea is summoned for point that:
							// 1) is not equals current point
							// 2) is not already painted
							// 3) is legal neighbor of current point
							getAllArea(newPoint, mapToPiant);

						}
					}

				}
			}
		}
	}

	// Add a description in your own words.
	public char[][] getMap() {
		return this.map;
	}

	public boolean isInMap(int i, int j) {
		if ((i < 0) || (j < 0)) {
			return false;
		}

		if ((i >= this.map.length) || (j >= this.map[0].length)) {
			return false;
		}

		return true;
	}

	public boolean isInMap(Point p) {
		return isInMap(p.getX(), p.getY());
	}

	// Add a description in your own words.
	public boolean legalNeighbor(Point p1, Point p2) {

		// On Map
		if (!isInMap(p1) || !isInMap(p2)) {
			return false;
		}

		// Not equals
		if (p1.equals(p2)) {
			return false;
		}

		if ((Math.abs(p1.getX() - p2.getX()) > 1)) {
			return false;
		}

		if ((Math.abs(p1.getY() - p2.getY()) > 1)) {
			return false;
		}

		// same color
		if (this.map[p1.getX()][p1.getY()] != this.map[p2.getX()][p2.getY()]) {
			return false;
		}

		return true;

	}

	// Add a description in your own words.
	public int numOfColors() {
		String colors = "";
		int counter = 0;

		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[0].length; j++) {

				if (colors.indexOf(this.map[i][j]) == -1) {
					counter = counter + 1;
					colors = colors + this.map[i][j];
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

		int[] diffs = new int[] { -1, 0, 1 };

		for (int i = 0; i < diffs.length; i++) {
			for (int j = 0; j < diffs.length; j++) {

				int newI = p.getX() + diffs[i];
				int newJ = p.getY() + diffs[j];

				if (isInMap(newI, newJ)) {

					if (colors.indexOf(this.map[newI][newJ]) == -1) {
						counter = counter + 1;
						colors = colors + this.map[newI][newJ];
					}
				}
			}
		}

		return counter;
	}
}
