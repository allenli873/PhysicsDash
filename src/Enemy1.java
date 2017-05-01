import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class Enemy1 {
	private final int PPM = 150;
	protected final int HEIGHT = PPM / 3;
	protected final int WIDTH = PPM / 2;
	protected int x, y;
	protected int[] xVals, yVals, xValsL, yValsL, xValsR, yValsR;
	public Enemy1(int x, int y) {
		this.x = x;
		this.y = y - PPM / 3;
	}
	//draws 
	public void draw(Graphics g) {
		x -= 1;
		//main body
		xVals = new int[]{x, x + PPM / 4, x + PPM / 2};
		yVals = new int[]{y + HEIGHT, y, y + HEIGHT};
		Color c = new Color(139, 69, 19);
		g.setColor(c);
		g.fillPolygon(xVals, yVals, 3);
	}
}
