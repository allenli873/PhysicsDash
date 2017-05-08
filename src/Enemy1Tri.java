import java.awt.Color;
import java.awt.Graphics;
//essentially the left leg of the enemy
public class Enemy1Tri {
	private final static int PPM = 150;
	protected int x, y;
	//small gap before the legs are drawn
	public final static int LEG_GAP = PPM / 12;
	protected final int LEG_WIDTH = PPM / 6;
	protected final int WIDTH = PPM / 2;
	protected final int HEIGHT = PPM / 8;
	protected final int BODY_HEIGHT = PPM / 3;
	protected int[] xValsL, yVals, xValsR;
	//constructor
	public Enemy1Tri(int x, int y) {
		this.x = x;
		this.y = y;
	}
	//draws the left leg in a place proportional to the body
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		xValsL = new int[]{x, x + LEG_WIDTH / 2, x + LEG_WIDTH};
		yVals = new int[]{y + HEIGHT, y, y + HEIGHT};
		g.fillPolygon(xValsL, yVals, 3);
	}
	
}
