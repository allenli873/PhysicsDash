import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Game extends JPanel {
	
	private double rotate;
	public static final int GROUND_HEIGHT = 150;
	private boolean left;
	private PhysicsDash app;
	private LevelMap map;
	protected List<Integer> xEnemy1, yEnemy1;
	protected Player player;
	private GuidePanel help;
	public boolean shouldRequest;
	public static boolean dying;
	private int freezeX, freezeY;
	
	private Image ground;
	//constructor
	public Game(PhysicsDash p) {
		rotate = 0;
		app = p;
		shouldRequest = true;
		player = new Player(app);
		xEnemy1 = new ArrayList<Integer>();
		yEnemy1 = new ArrayList<Integer>();
		map = new LevelMap(player, app.level, this, app);
		help = new GuidePanel();
		addKeyListener(player);
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
		if(playHit.intersects(enemy1Hit)) {
			app.charName = "dead";
			app.getMyImage();
			Player.character = app.character;
			player.velY = -5;
			player.velX = 0;
			LevelMap.stepOn = false;
		}
	}
	
	//the paintComponent
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//requests focus in window
		if(shouldRequest) requestFocusInWindow();
		
		if(app.game.onCheckpoint) {
			help.draw(g, app.game.info);
			app.game.info.setBackground(Color.GREEN);
		}
		else {
			app.game.info.setBackground(Color.GRAY);
		}
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.scale(400.0/540.0, 400.0/540.0);
		if(player.y < map.levelHeight - 720 && !dying)
			g2d.translate((int)  (app.WIDTH - player.w)/2 - player.x, (int) (app.HEIGHT - player.h)/2 - player.y);
		else if(!dying) {
			dying = true;
			freezeX = (int)  ((app.WIDTH - player.w)/2 - player.x);
			freezeY = (int) ((app.HEIGHT - player.h)/2 - player.y);
			g2d.translate(freezeX, freezeY);
			player.velX = 0;
		}
		else {
			g2d.translate(freezeX, freezeY);
		}
	    
		g.setColor(new Color(175, 60, 0));
		//player generation
		player.draw(g, map);
		map.draw(g);
		//enemy generation
		for(int i = 0; i < xEnemy1.size(); i++) {
			makeEnemy(g, xEnemy1.get(i), yEnemy1.get(i));
			if(xEnemy1.get(i) < 0 + (int)(Math.random() * 500))
				left = false;
			if(xEnemy1.get(i) > LevelMap.width * 60 - (int)(Math.random() * 500))
				left = true;
			xEnemy1.set(i, left ? xEnemy1.get(i) - 1 : xEnemy1.get(i) + 1);
		}
		
		
	}
}