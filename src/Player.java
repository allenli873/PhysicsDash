import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//the character that is controlled
public class Player implements KeyListener {
	
	public static final float PPM = 120; //ppm is how many pixels represent one meter
	//field variables
	private PhysicsDash app;
	private Image character;
	public boolean jumped = false;
	private boolean left, right;
	protected float x, y;
	protected int w, h;
	
	private float step = 1/60f;
	public float velX = 0;
	public float velY = 0;
	private float grav = 9.8f;
	//constructor
	public Player(PhysicsDash p) {
		app = p;
		app.getMyImage();
		character = app.character;
		w = (int) PPM/2; //dimensions are 1/2m = 1/2ppm pixels
		h = (int) PPM/2; 
		x = 0;
		y = 0;
	}
	
	//draws the character in the ever changing X/Y positions
	public void draw(Graphics g, LevelMap map) {
		//checks if right or left was pressed
		
		//friction
		if(velX - 0.02 != 0 && !jumped)
			velX = velX < 0 ? velX + 0.02f : velX - 0.02f;
		
		if(Math.abs(velX) < 0.02)
			velX = 0;
		
		x += velX * step * PPM;
		y += velY * step * PPM;
		map.step(g);
//		System.out.println(y);
		if(y > 600)
			app.playerDies();
		if(right) 
			if(velX < 3f)
				velX += 0.07f;
		
		if(left) 
			if(velX > -3f)
				velX -= 0.07f;
		
		g.drawImage(character, (int) x, (int) y, w, h, null);
		velY += grav * step;
	}
	//makes sure character only jumps one time, jump by pressing up arrow
	public void keyTyped(KeyEvent e) {}
	//move left or right by pressing and holding a and d respectively
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		//code for left
		if(code == KeyEvent.VK_LEFT)
			left = true;
		//code for right
		if(code == KeyEvent.VK_RIGHT)
			right = true;
		int c = e.getKeyCode();
		if(c == KeyEvent.VK_UP) {
			if(!jumped) {
				app.numJumps++;
				velY = -4;
				jumped = true;
			}
		}
	}
	//stops moving when key is released
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_LEFT)
			left = false;
		if(code == KeyEvent.VK_RIGHT)
			right = false;
		
	}
}
