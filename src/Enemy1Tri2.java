import java.awt.Graphics;

public class Enemy1Tri2 {
	private final static int PPM = 150;
	protected int x, y;
	//small gap before the legs are drawn
	public final static int LEG_GAP = PPM / 12;
	protected final int LEG_WIDTH = PPM / 6;
	protected final int WIDTH = PPM / 2;
	protected final int HEIGHT = PPM / 8;
	protected final int BODY_HEIGHT = PPM / 3;
	protected int[] xValsL, yVals, xValsR;
	public Enemy1Tri2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {
//		x -= 2;
		yVals = new int[]{y + HEIGHT, y, y + HEIGHT};
		xValsR = new int[]{x + LEG_WIDTH + LEG_GAP, x + LEG_WIDTH + (LEG_WIDTH / 2) + LEG_GAP, x + 2 * LEG_WIDTH + LEG_GAP};
		g.fillPolygon(xValsR, yVals, 3);
	}
	
}
