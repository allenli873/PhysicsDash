import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Game extends JPanel {
	private double rotate;
	public static final int GROUND_HEIGHT = 150;
	
	private PhysicsDash app;
	private LevelMap map;
	private Character player;
//	private List<Enemy1> enemies;
//	private List<Enemy1Tri> enemyLegs;
	
	private Image ground;
	//constructor
	public Game(PhysicsDash p) {
		rotate = 0;
		app = p;
		setSize(960, 540);
		player = new Character(app);
		map = new LevelMap(player, app.level);
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
	public void makeEnemy(Graphics g, int x, int y) {
		rotate += 0.01;
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform original = g2d.getTransform();
		Enemy1 e1 = new Enemy1(x, y);
		e1.draw(g);
		Enemy1Tri e1t = new Enemy1Tri(x, y);
		g2d.rotate(rotate, (e1t.x) + e1t.LEG_WIDTH / 2, e1t.y + e1t.HEIGHT / 2);
		e1t.draw(g);
		g2d.rotate(-rotate, (e1t.x) + e1t.LEG_WIDTH / 2, e1t.y + e1t.HEIGHT / 2);
		Enemy1Tri2 e1t2 = new Enemy1Tri2(x, y);
		g2d.rotate(rotate, e1t2.x + Enemy1Tri.LEG_GAP + e1t2.LEG_WIDTH * 3 / 2, e1t2.y + e1t2.HEIGHT / 2);
		e1t2.draw(g);
		g2d.rotate(-rotate, e1t2.x + Enemy1Tri.LEG_GAP + e1t2.LEG_WIDTH * 3 / 2, e1t2.y + e1t2.HEIGHT / 2);
		
		//crude hit detection for now
		Rectangle playHit = new Rectangle((int)player.x, (int)player.y, player.w, player.h);
		Rectangle enemy1Hit = new Rectangle(e1.x, e1.y, e1.WIDTH, e1.HEIGHT * 3 / 2);
		//checks if player hitbox hits the enemy hitbox
		if(playHit.intersects(enemy1Hit))
			app.playerDies();
	}
	
	//the paintComponent
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		requestFocusInWindow();
		
		Graphics2D g2d = (Graphics2D) g;
	    g2d.translate((int)  (app.WIDTH - player.w)/2 - player.x, 0);
		g.setColor(new Color(175, 60, 0));
		
		
		//rotating wheels for enemy's legs
//		AffineTransform at = g2d.getTransform();
//		AffineTransform a1 = new AffineTransform(at);
//		a1.translate((enemy1tri.x + Enemy1Tri.LEG_GAP) + enemy1tri.LEG_WIDTH / 2, enemy1tri.y + enemy1tri.HEIGHT / 2);
//		rotate += 0.01;
//		a1.rotate(rotate);
//		AffineTransform a2 = new AffineTransform(at);
//		a2.translate(enemy1tri.x + Enemy1Tri.LEG_GAP + enemy1tri.LEG_WIDTH / 2, enemy1tri.y + enemy1tri.HEIGHT / 2);
//		a2.rotate(rotate);
//		System.out.println(rotate);
//		enemy1.draw(g);
//		enemy1tri.draw(g);
////		g2d.scale(0.8, 0.8);
		map.draw(g);
		map.step(g);
//		//enemy1tri.draw(g);
////		g2d.rotate(rotate);
//		enemy1.draw(g);
		player.draw(g);
		
		makeEnemy(g, 500, 350);
		makeEnemy(g, 450, 350);
		makeEnemy(g, 350, 350);
		
	}
}
