import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//the character that is controlled
public class Player implements KeyListener {
	
	private final float PPM = 120; //ppm is how many pixels represent one meter
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
		if(velX - 0.02 != 0)
			velX = velX < 0 ? velX + 0.02f : velX - 0.02f;
		
		x += velX * step * PPM;
		y += velY * step * PPM;
		map.step(g);
		if(right) 
			if(velX < 3f)
				velX += 0.07f;
		if(left) 
			if(velX > -3f)
				velX -= 0.07f;
		g.drawImage(character, (int) x, (int) y, w, h, null);
		velY += grav * step;
	}
	//makes sure character only jumps one time, jump by pressing w
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if(c == 'w') {
			if(!jumped) {
				app.numJumps++;
				velY = -4;
				jumped = true;
			}
		}
		
	}
	//move left or right by pressing and holding a and d respectively
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		//code for a
		if(code == KeyEvent.VK_A)
			left = true;
		//code for d
		if(code == KeyEvent.VK_D)
			right = true;
	}
	//stops moving when key is released
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_A)
			left = false;
		if(code == KeyEvent.VK_D)
			right = false;
		
	}
}
