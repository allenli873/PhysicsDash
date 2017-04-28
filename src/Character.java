import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Character implements KeyListener {
	
	private final float PPM = 150; //ppm is how many pixels represent one meter
	
	private PhysicsDash app;
	private Image character, ground;
	private boolean jumped = false;
	private boolean left, right;
	public float x, y;
	public int w, h;
	
	float step = 1/60f;
	float velY = 0;
	float grav = 9.8f;
	
	public Character(PhysicsDash p) {
		app = p;
		app.getMyImage();
		character = app.character;
		w = (int) PPM/2; //dimensions are 1/2m = 1/2ppm pixels
		h = (int) PPM/2; 
		x = 0;
		y = 0;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		
		if(right)
			x += 5;
		if(left)
			x -= 5;
		g.drawImage(character, (int) x, (int) y, w, h, null);
		y += velY * step * PPM;
		velY += grav * step;
		if(y > app.HEIGHT - Game.GROUND_HEIGHT - h) {
			y = app.HEIGHT - Game.GROUND_HEIGHT - h;
			jumped = false;
//			System.out.println(jumped);
		}
	}
	
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
//		System.out.println(c);
		if(c == 'w') {
			if(!jumped) {
				velY = -4;
				jumped = true;
			}
		}
		
	}
	public void keyPressed(KeyEvent e) {
		System.out.println("called");
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_A)
			left = true;
		if(code == KeyEvent.VK_D)
			right = true;
	}
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_A)
			left = false;
		if(code == KeyEvent.VK_D)
			right = false;
		
	}
}
