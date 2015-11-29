/*
* A class that handles points
*/
public class Point {

	private int x;
	private int y;

	// Constructor for Point. Gets the point x,y coordinates.
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean equals(Point p) {

		if (p.getX() != this.x) {
			return false;
		}
		if (p.getY() != this.y) {
			return false;
		}

		return true;

	}

	// returns the x value of current point.
	public int getX() {
		return this.x;
	}

	// returns the y value of current point.
	public int getY() {
		return this.y;
	}
}
