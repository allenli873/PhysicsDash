import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Game extends JPanel {
	//Pixels per meter
	private final int PPM = 120;
	//Width and height of the character
	protected final int HEIGHT = PPM / 3;
	protected final int WIDTH = PPM / 2;
	//xy pos of the character
	protected int x, y;
	protected String temp;
	//the face of the character
	protected Image lenny;
	//x and y values of the current character
	protected int[] xVals, yVals, xValsL, xValsR;
	//list of enemies with their x and y values
	protected List<Integer> xEnemy1, yEnemy1;
	//height of the leg
	protected final int HEIGHT_LEG = PPM / 8;
	//gap between the legs
	private final int LEG_GAP = PPM / 12;
	//width of the legs
	protected final int LEG_WIDTH = PPM / 6;
	private double rotate;
	public static final int GROUND_HEIGHT = 150;
	private boolean left;
	private PhysicsDash app;
	public LevelMap map;
	
	
	protected Player player;
	private GuidePanel help;
	public boolean shouldRequest;
	public static boolean dying;
	private int freezeX, freezeY;
	
	
	private Image ground;
	//constructor
	public Game(PhysicsDash p) {
		app = p;
		app.charName = "lenny";
		app.getMyImage();
		lenny = app.character;
		app.charName = temp;
		rotate = 0;
		
		shouldRequest = true;
		player = new Player(app);
		xEnemy1 = new ArrayList<Integer>();
		yEnemy1 = new ArrayList<Integer>();
		map = new LevelMap(player, app.level, this, app);
		help = new GuidePanel(app);
		addKeyListener(player);
	}
	
	public void makeEnemy(Graphics g, int x, int y) {
		//this is actually hella laggy :(
		rotate += 0.1;
		Graphics2D g2d = (Graphics2D)g;
//		Enemy1 e1 = new Enemy1(x, y, app);
//		e1.draw(g);
		//main body
		y = y - PPM / 3;
		xVals = new int[]{x, x + PPM / 4, x + PPM / 2};
		yVals = new int[]{y + HEIGHT, y, y + HEIGHT};
		Color c = new Color(139, 69, 19);
		g.setColor(c);
		g.fillPolygon(xVals, yVals, 3);
		g.setColor(Color.BLACK);
		//draws the image
		g.drawImage(lenny, x, y - 15, PPM / 2, HEIGHT + 20, null);
		
		y = y + PPM / 3;
		
//		Enemy1Tri e1t = new Enemy1Tri(x, y);
		g2d.rotate(rotate, x + LEG_WIDTH / 2, y + HEIGHT_LEG / 2);
		//left leg
		g.setColor(Color.BLACK);
		xValsL = new int[]{x, x + LEG_WIDTH / 2, x + LEG_WIDTH};
		yVals = new int[]{y + HEIGHT_LEG, y, y + HEIGHT_LEG};
		g.fillPolygon(xValsL, yVals, 3);
		g2d.rotate(-rotate, x + LEG_WIDTH / 2, y + HEIGHT_LEG / 2);
//		Enemy1Tri2 e1t2 = new Enemy1Tri2(x, y);
		
		g2d.rotate(rotate, x + LEG_GAP + LEG_WIDTH * 3 / 2, y + HEIGHT_LEG / 2);
		yVals = new int[]{y + HEIGHT_LEG, y, y + HEIGHT_LEG};
		xValsR = new int[]{x + LEG_WIDTH + LEG_GAP, x + LEG_WIDTH + (LEG_WIDTH / 2) + LEG_GAP, x + 2 * LEG_WIDTH + LEG_GAP};
		g.fillPolygon(xValsR, yVals, 3);
		g2d.rotate(-rotate, x + LEG_GAP + LEG_WIDTH * 3 / 2, y + HEIGHT_LEG / 2);
		
		y = y - PPM / 3;
		if(!app.game.onCheckpoint) {
			//hit detection
			Rectangle playHit = new Rectangle((int)player.x, (int)player.y, player.w, player.h);
			Rectangle enemy1Hit = new Rectangle(x, y, WIDTH, HEIGHT * 3 / 2);
			//checks if player hitbox hits the enemy hitbox
			if(playHit.intersects(enemy1Hit) && !app.charName.equals("dead")) {
				app.charName = "dead";
				app.getMyImage();
				Player.character = app.character;
				player.velY = -5;
				player.velX = 0;
				LevelMap.stepOn = false;
			}
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
			addMouseListener(help);
		}
		else {
			app.game.info.setBackground(Color.GRAY);
			removeMouseListener(help);
		}
		
		Graphics2D g2d = (Graphics2D) g;
		if(!app.game.onCheckpoint)
			g2d.scale(400.0/540.0, 400.0/540.0);
		else
			g2d.scale(200.0/540.0, 200.0/540.0);
		if(app.game.onCheckpoint)
			g2d.translate((int)  (app.WIDTH - player.w)/2 - player.x, 0);
		else if(player.y < map.levelHeight - 720 && !dying)
			g2d.translate((int)  (app.WIDTH - player.w)/2 - player.x, (int) (app.HEIGHT - player.h)/2 - player.y);
		else if(!dying) {
			dying = true;
			freezeX = (int)  ((app.WIDTH - player.w)/2 - player.x);
			freezeY = (int) ((app.HEIGHT - player.h)/2 - player.y);
			g2d.translate(freezeX, freezeY);
			player.velX = 0;
		}
		else
			g2d.translate(freezeX, freezeY);
	    
		g.setColor(new Color(175, 60, 0));
		//player generation
		player.draw(g, map);
		map.draw(g);
		//enemy generation
		//for extra random fun
		for(int i = 0; i < xEnemy1.size(); i++) {
			makeEnemy(g, xEnemy1.get(i), yEnemy1.get(i));
			if(xEnemy1.get(i) < 0) 
				left = false;
			if(xEnemy1.get(i) > LevelMap.width * 60) 
				left = true;
			xEnemy1.set(i, left ? xEnemy1.get(i) - 1 : xEnemy1.get(i) + 1);
		}
		
		
	}
}