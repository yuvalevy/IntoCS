/**
 * A class that handles points
 */
public class Point {

	private int x;
	private int y;

	/**
	 * Constructor for Point. Gets the point x,y coordinates.
	 * 
	 * @param x
	 *            X coordinate
	 * @param y
	 *            Y coordinate
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Gets a point and compares it to the current point
	 * 
	 * @param p
	 * @return True if points are equals, false otherwise.
	 */
	public boolean equals(Point p) {

		if (p.getX() != this.x) {
			return false;
		}
		if (p.getY() != this.y) {
			return false;
		}

		return true;

	}

	/**
	 *
	 * @return returns the x value of current point.
	 */
	public int getX() {
		return this.x;
	}

	/**
	 *
	 * @return returns the y value of current point.
	 */
	public int getY() {
		return this.y;
	}
}
