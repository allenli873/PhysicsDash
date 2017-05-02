import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Game extends JPanel {
	private double rotate;
	public static final int GROUND_HEIGHT = 150;
	
	private PhysicsDash app;
	
	private Character player;
	private Enemy1 enemy1;
	private Enemy1Tri enemy1tri;
	private Image ground;
	//constructor
	public Game(PhysicsDash p) {
		rotate = 0;
		app = p;
		setSize(960, 540);
		player = new Character(app);
		enemy1 = new Enemy1(500, 350);
		enemy1tri = new Enemy1Tri(500, 350);
		loadGround();
		addKeyListener(player);
	}
	//fancy ground
	public void loadGround() {
		try {
			ground = ImageIO.read(new File("basic128.png"));
		} catch (IOException e) {
		}
	}
	//the paintComponent
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		requestFocusInWindow();
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate((int)  (app.WIDTH - player.w)/2 - player.x, 0);
		g.setColor(new Color(175, 60, 0));
		for(int x = -3000; x < app.WIDTH + 3000; x += GROUND_HEIGHT) {
			g.drawImage(ground, x, app.HEIGHT - GROUND_HEIGHT, GROUND_HEIGHT, GROUND_HEIGHT, null);
		}
		//crude hit detection for now
		Rectangle playHit = new Rectangle((int)player.x, (int)player.y, player.w, player.h);
		Rectangle enemy1Hit = new Rectangle(enemy1.x, enemy1.y, enemy1.WIDTH, enemy1.HEIGHT);
		
		if(playHit.intersects(enemy1Hit))
			System.exit(0);
		AffineTransform at = g2d.getTransform();
		AffineTransform a1 = new AffineTransform(at);
		a1.translate((enemy1tri.x + Enemy1Tri.DISPLACEMENT) + enemy1tri.LEG_WIDTH / 2, enemy1tri.y + enemy1tri.HEIGHT / 2);
		rotate += 0.01;
		a1.rotate(rotate);
		AffineTransform a2 = new AffineTransform(at);
		a2.translate(enemy1tri.x + Enemy1Tri.DISPLACEMENT + enemy1tri.LEG_WIDTH / 2, enemy1tri.y + enemy1tri.HEIGHT / 2);
		a2.rotate(rotate);
		System.out.println(rotate);
		enemy1.draw(g);
		enemy1tri.draw(g);
//		g2d.rotate(rotate);
		player.draw(g);
		
	}
}
