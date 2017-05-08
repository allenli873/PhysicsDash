import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Enemy1 {
	//Pixels per meter
	private final int PPM = 120;
	//Width and height of the character
	protected final int HEIGHT = PPM / 3;
	protected final int WIDTH = PPM / 2;
	//xy pos of the character
	protected int x, y;
	protected String temp;
	//the face of the character
	protected Image lenny;
	protected int[] xVals, yVals;
	protected PhysicsDash app;
	public Enemy1(int x, int y, PhysicsDash p) {
		app = p;
		//getting the image
		temp = app.charName;
		app.charName = "lenny";
		app.getMyImage();
		lenny = app.character;
		app.charName = temp;
		
		this.x = x;
		this.y = y - PPM / 3;
	}
	//draws 
	public void draw(Graphics g) {
		//main body
		xVals = new int[]{x, x + PPM / 4, x + PPM / 2};
		yVals = new int[]{y + HEIGHT, y, y + HEIGHT};
		Color c = new Color(139, 69, 19);
		g.setColor(c);
		g.fillPolygon(xVals, yVals, 3);
		g.setColor(Color.BLACK);
		//draws the image
		g.drawImage(lenny, x, y - 15, PPM / 2, HEIGHT + 20, null);
	}
}
