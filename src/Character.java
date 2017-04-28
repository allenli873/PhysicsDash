import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;

public class Character implements KeyListener {
	
	private float ppm = 150; //ppm is how many pixels represent one meter
	
	private PhysicsDash app;
	private Image character;
	private boolean jumped = false;
	private boolean left, right;
	private float x, y;
	private int w, h;
	
	float step = 1/60f;
	float velY = 0;
	float grav = 9.8f;
	
	public Character(PhysicsDash p) {
//		left = false;
//		right = false;
		app = p;
		app.getMyImage();
		character = app.character;
		w = (int) ppm/2; //dimensions are 1/2m = 1/2ppm pixels
		h = (int) ppm/2; 
		x = (PhysicsDash.WIDTH - w)/2;
		y = 0;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
//		System.out.println("Right:" + right);
//		System.out.println("Left:" + left);
		if(right)
			x += 5;
		if(left)
			x -= 5;
		g.drawImage(character, (int) x, (int) y, w, h, null);
		y += velY * step * ppm;
		velY += grav * step;
		if(y > PhysicsDash.HEIGHT - Game.GROUND_HEIGHT - h) {
			y = PhysicsDash.HEIGHT - Game.GROUND_HEIGHT - h;
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
