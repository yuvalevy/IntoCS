/**
 * Describes a 2D map that contains colors
 */
public class Map {

	private char[][] map;

	/**
	 * Creates new Map instance
	 *
	 * All letters are switched to lower case. Also if one of the chars in the
	 * given char[][] is not English alfabithic, it will be switched with 'z'
	 *
	 * @param map
	 */
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

	/**
	 * Checks if the given map is equals current map
	 *
	 * Equals means: maps' lengths are the same, and all chars values in one
	 * array is equals to the other one
	 *
	 * @param map
	 *            Map to compare
	 * @return True, if maps are equals, false otherwise
	 */
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

	/**
	 * Fills the point, and the point's legal neighbors with the given color
	 *
	 * @param p
	 *            point to paint
	 * @param color
	 *            color to paint
	 */
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

	/**
	 * Fills the boolean[][] with 'true' values, where all point's legal
	 * neighbors are, and where all legal neighbors' legal neighbors are.
	 *
	 * @param p
	 *            Starting point
	 * @param mapToPiant
	 */
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

	/**
	 *
	 * @return the char[][] of the map
	 */
	public char[][] getMap() {
		return this.map;
	}

	/**
	 * Checks if given coordinate is on the map
	 *
	 * @param i
	 *            as 'x'
	 * @param j
	 *            as 'y'
	 * @return True if coordinate is on the map, false otherwise
	 */
	public boolean isInMap(int i, int j) {
		if ((i < 0) || (j < 0)) {
			return false;
		}

		if ((i >= this.map.length) || (j >= this.map[0].length)) {
			return false;
		}

		return true;
	}

	/**
	 * Checks if given point is on the map
	 *
	 * @param p
	 *            point to be checked
	 * @return True if point is on the map, false otherwise
	 */
	public boolean isInMap(Point p) {
		return isInMap(p.getX(), p.getY());
	}

	/**
	 * Checks if two points are defined 'legal neighbor' in current map
	 *
	 * legalNeighbor defined: 1) Both points are in map 2) Points are not the
	 * same 3) Points distance in 'x' axis is 1 or 0 4) Points distance in 'y'
	 * axis is 1 or 0 5) Points' color on map is the same
	 *
	 * @param p1
	 * @param p2
	 * @return If two point are legalNeighbor returns true, otherwise return
	 *         false
	 */
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

	/**
	 *
	 * @return The number of color on the map
	 */
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

	/**
	 *
	 * @param p
	 * @return The number of the point's neighbor's colors including point
	 *         himself
	 */
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
