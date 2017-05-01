import java.awt.Graphics;

public class Enemy1Tri {
	private final static int PPM = 150;
	protected int x, y;
	//small gap before the legs are drawn
	public final static int DISPLACEMENT = PPM / 24;
	protected final int LEG_WIDTH = PPM / 6;
	protected final int WIDTH = PPM / 2;
	protected final int HEIGHT = PPM / 9;
	protected final int BODY_HEIGHT = PPM / 3;
	protected int[] xValsL, yVals, xValsR;
	public Enemy1Tri(int x, int y) {
		this.x = x + DISPLACEMENT;
		this.y = y;
	}
	
	public void draw(Graphics g) {
		x -= 1;
		xValsL = new int[]{x, x + LEG_WIDTH / 2, x + LEG_WIDTH};
		yVals = new int[]{y + HEIGHT, y, y + HEIGHT};
		xValsR = new int[]{x + LEG_WIDTH + DISPLACEMENT * 2, x + LEG_WIDTH + (LEG_WIDTH / 2) + DISPLACEMENT * 2, x + 2 * LEG_WIDTH + DISPLACEMENT * 2};
		g.fillPolygon(xValsL, yVals, 3);
		g.fillPolygon(xValsR, yVals, 3);
	}
	
}
