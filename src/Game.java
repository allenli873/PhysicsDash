import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Game extends JPanel {
	
	public static final int GROUND_HEIGHT = 150;
	
	private PhysicsDash app;
	
	private Character player;
	private Image ground;
	
	public Game(PhysicsDash p) {
		app = p;
		setSize(960, 540);
		player = new Character(app);
		loadGround();
		addKeyListener(player);
	}
	
	public void loadGround() {
		try {
			ground = ImageIO.read(new File("basic128.png"));
		} catch (IOException e) {
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		requestFocusInWindow();
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate((int)  (PhysicsDash.WIDTH - player.w)/2 - player.x, 0);
		
		g.setColor(new Color(175, 60, 0));
		//g.fillRect(0, PhysicsDash.HEIGHT - GROUND_HEIGHT, PhysicsDash.WIDTH, GROUND_HEIGHT);
		//g.drawImage(ground, 0, PhysicsDash.HEIGHT - GROUND_HEIGHT, PhysicsDash.WIDTH, GROUND_HEIGHT, null);
		for(int x = -3000; x < PhysicsDash.WIDTH + 3000; x += GROUND_HEIGHT) {
			g.drawImage(ground, x, PhysicsDash.HEIGHT - GROUND_HEIGHT, GROUND_HEIGHT, GROUND_HEIGHT, null);
		}
		player.draw(g);
	}
}
