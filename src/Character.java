import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;

public class Character implements KeyListener {
	
	private PhysicsDash app;
	private Image character;
	private boolean jumped = false;
	private boolean left, right;
	private float x, y;
	private int w, h;
	
	float inc = 0;
	float grav = 12;
	
	public Character(PhysicsDash p) {
//		left = false;
//		right = false;
		app = p;
		app.getMyImage();
		character = app.character;
		w = 70;
		h = 70;
		x = (PhysicsDash.WIDTH - w)/2;
		y = 0;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
//		System.out.println("Right:" + right);
//		System.out.println("Left:" + left);
		if(right)
			x += 3;
		if(left)
			x -= 3;
		g.drawImage(character, (int) x, (int) y, w, h, null);
		y += grav + inc;
		inc *= 0.98f;
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
				inc = -20;
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
