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
	private double rotate;
	public static final int GROUND_HEIGHT = 150;
	
	private PhysicsDash app;
	private LevelMap map;
	protected Player player;
	public boolean shouldRequest, dying;
	private int freezeX, freezeY;
	
	private Image ground;
	//constructor
	public Game(PhysicsDash p) {
		rotate = 0;
		app = p;
		shouldRequest = true;
		player = new Player(app);
		map = new LevelMap(player, app.level, this, app);
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
		//this is actually hella laggy :(
		rotate += 0.1;
		Graphics2D g2d = (Graphics2D)g;
		Enemy1 e1 = new Enemy1(x, y, app);
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
		//requests focus in window
		if(shouldRequest) requestFocusInWindow();
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.scale(400.0/540.0, 400.0/540.0);
		if(player.y < map.levelHeight - 720 && !dying)
			g2d.translate((int)  (app.WIDTH - player.w)/2 - player.x, (int) (app.HEIGHT - player.h)/2 - player.y);
		else if(!dying) {
			dying = true;
			freezeX = (int)  ((app.WIDTH - player.w)/2 - player.x);
			freezeY = (int) ((app.HEIGHT - player.h)/2 - player.y);
			g2d.translate(freezeX, freezeY);
		}
		else {
			g2d.translate(freezeX, freezeY);
		}
	    
		g.setColor(new Color(175, 60, 0));
		//player generation
		player.draw(g, map);
		map.draw(g);
		//enemy generation
		for(int i = 0; i < app.xEnemy1.size(); i++) {
			makeEnemy(g, app.xEnemy1.get(i), app.yEnemy1.get(i));
			app.xEnemy1.set(i, app.xEnemy1.get(i) - 1);
		}
		
	}
}