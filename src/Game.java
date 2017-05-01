import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Game extends JPanel {
	
	public static final int GROUND_HEIGHT = 150;
	
	private PhysicsDash app;
	
	private Character player;
	private Enemy1 enemy1;
	private Image ground;
	
	public Game(PhysicsDash p) {
		app = p;
		setSize(960, 540);
		player = new Character(app);
		enemy1 = new Enemy1(500, 350);
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
		g2d.translate((int)  (app.WIDTH - player.w)/2 - player.x, 0);
		g.setColor(new Color(175, 60, 0));
		for(int x = -3000; x < app.WIDTH + 3000; x += GROUND_HEIGHT) {
			g.drawImage(ground, x, app.HEIGHT - GROUND_HEIGHT, GROUND_HEIGHT, GROUND_HEIGHT, null);
		}
		Rectangle r = new Rectangle((int)player.x, (int)player.y, player.w, player.h);
		g.drawRect((int)player.x, (int)player.y, player.w, player.h);
		Rectangle r2 = new Rectangle(enemy1.x, enemy1.y, enemy1.WIDTH, enemy1.HEIGHT);
		g.drawRect(enemy1.x, enemy1.y, enemy1.WIDTH, enemy1.HEIGHT);
		if(g2d.hit(r, r2, false))
			System.exit(0);
		player.draw(g);
		enemy1.draw(g);
	}
}
