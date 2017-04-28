import java.awt.Graphics;

public class Enemy1 {
	private final int PPM = 150;
	private int x, y;
	private int[] xVals;
	private int[] yVals;
	public Enemy1(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {
		x -= 1;
		xVals = new int[]{x, x + PPM / 4, x + PPM / 2};
		yVals = new int[]{};
	}
}
