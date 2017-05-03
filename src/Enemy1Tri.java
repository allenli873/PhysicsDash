import java.awt.Color;
import java.awt.Graphics;

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
	public Enemy1Tri(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {
//		x -= 2;
		g.setColor(Color.BLACK);
		xValsL = new int[]{x, x + LEG_WIDTH / 2, x + LEG_WIDTH};
		yVals = new int[]{y + HEIGHT, y, y + HEIGHT};
		g.fillPolygon(xValsL, yVals, 3);
	}
	
}
