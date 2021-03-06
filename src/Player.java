import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//the character that is controlled
public class Player implements KeyListener {
	
	public static final float PPM = 120; //ppm is how many pixels represent one meter
	//field variables
	private PhysicsDash app;
	public static Image character;
	public boolean jumped;
	protected boolean left, right;
	public static float x, y;
	protected int w, h;
	private float step = 1/60f;
	public float velX = 0;
	public float velY = 0;
	private float grav = 9.8f;
	//constructor
	public Player(PhysicsDash p) {
		app = p;
		app.charName = "deltVdeltT";
		app.getMyImage();
		character = app.character;
		w = (int) PPM/2; //dimensions are 1/2m = 1/2ppm pixels
		h = (int) PPM/2; 
		x = 0;
		y = 0;
	}
	
	//draws the character in the ever changing X/Y positions
	public void draw(Graphics g, LevelMap map) {

		g.drawImage(character, (int) x, (int) y, w, h, null);
		
		//frictiong
		if(velX - 0.02 != 0 && !jumped)
		{
			if(app.game.checkpointJump) {
				velX = 0;
				app.game.landed();
			}
			else {
				velX = velX < 0 ? velX + 0.02f : velX - 0.02f;
			}
		}
		boolean offBounds = false;
		if(Math.abs(velX) < 0.02)
			velX = 0;
		if(y > map.levelHeight) {
			if(app.charName.equals("dead")) {
				app.playerDies("You hit an enemy!");
			}
			else {
				app.playerDies("Out of bounds");
				offBounds = true;
				InfoPanel.shouldDie = 0;
			}
		}
		if (InfoPanel.shouldDie > 0 && ((!app.game.onCheckpoint && !InfoPanel.flying) || System.currentTimeMillis() - InfoPanel.shouldDie > 5000 && offBounds == false)) {
			app.playerDies("Incorrect answer");
        	InfoPanel.shouldDie = 0;
		}
		
		if(x >= app.finishX - 60) {
			if(!app.game.checkpointJump)
				app.levelComplete();
			else {
				app.playerDies("Incorrect Answer");
				offBounds = true;
				InfoPanel.shouldDie = 0;
				app.game.checkpointJump = false;
				velX = 0;
				velY = 0;
				app.game.game.shouldRequest = true;
			}
		}
		if(Math.abs(velX) < 0.02)
			velX = 0;
		x += velX * step * PPM;
		y += velY * step * PPM;
		map.step(g);
		if(right) 
			if(velX < 3f)
				velX += 0.07f;
		if(left) 
			if(velX > -3f)
				velX -= 0.07f;
		velY += grav * step;
	}
	//makes sure character only jumps one time, jump by pressing up arrow
	public void keyTyped(KeyEvent e) {}
	//move left or right by pressing and holding a and d respectively
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		//code for left
		if((code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) && !app.game.onCheckpoint)
			left = true;
		//code for right
		if((code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) && !app.game.onCheckpoint)
			right = true;
		if((code == KeyEvent.VK_UP || code == KeyEvent.VK_W) && !app.game.onCheckpoint)
			if(code == KeyEvent.VK_LEFT && !app.game.onCheckpoint)
				left = true;
		//code for right
		if(code == KeyEvent.VK_RIGHT && !app.game.onCheckpoint)
			right = true;
		int c = e.getKeyCode();
		if((c == KeyEvent.VK_UP || c == KeyEvent.VK_W) && !app.game.onCheckpoint) {
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
		if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A)
			left = false;
		if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D)
			right = false;
		
	}
}