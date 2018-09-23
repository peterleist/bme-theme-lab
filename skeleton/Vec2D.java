
public class Vec2D {
	private int X;
	private int Y;
	
	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public Vec2D(int x, int y) {
		X = x;
		Y = y;
	}
	
	
	public String toString() {
		return X + " " + Y ;
	}
}
