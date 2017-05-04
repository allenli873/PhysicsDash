import java.awt.Graphics;
import java.awt.Image;
//second enemy
public class Pepe {
	//field variables
	private final int PPM = 120;
	protected final int WIDTH = PPM / 2;
	protected final int HEIGHT = PPM / 2;
	private PhysicsDash app;
	protected int x, y;
	protected Image frog;
	
	public Pepe(PhysicsDash p, int x, int y) {
		app = p;
		app.charName = "pepe.png";
		app.getMyImage();
		frog = app.character;
		app.charName = "lenny.png";
	}
	
	public void draw(Graphics g) {
		g.drawImage(frog, x, y, WIDTH, HEIGHT, null);
	}
}
