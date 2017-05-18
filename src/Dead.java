import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;
public class Dead extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
	protected static Timer timer;
	protected PhysicsDash app;
	protected static String currentText;
	protected static int frame;
	//delay before text starts going
	private final int DELAY = 16;
	//width and height of the letter
	private final int LETTER_WIDTH = 18;
	private final int LETTER_HEIGHT = 22;
	//the frame I allow the user to do something
	private final int ALLOW_ACTION = 75;
	private Player player;
	private Color homeColor;
	private Color tryColor;
	private static String msg;
	
	public Dead(PhysicsDash p, Player player) {
		this.player = player;
		currentText = "";
		msg = "";
		frame = 0;
		app = p;
		timer = new Timer(75 / 2, this);
		setSize(960, 540);
		setBackground(Color.BLACK);
		homeColor = Color.WHITE;
		tryColor = Color.WHITE;		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	public static void dead(String _msg) {
		currentText = "";
		msg = _msg;
		frame = 0;
		timer.start();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Font gameOver = null;
		try {
			 gameOver = Font.createFont(Font.TRUETYPE_FONT, new File("gameover.ttf")).deriveFont(80f);
		     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("gameover.ttf")));
		} catch (IOException | FontFormatException e) {
		     System.err.println("Your font has gone wrong in the hood");
		     System.exit(1);
		}
		g.setColor(Color.WHITE);
		g.setFont(gameOver);
		g.drawString("GAME OVER", 300, 100);
		gameOver = gameOver.deriveFont(30f);
		g.setFont(gameOver);
		g.setColor(Color.RED);
		g.drawString(msg, 375, 160);
		gameOver = gameOver.deriveFont(40f);
		g.setColor(Color.WHITE);
		g.setFont(gameOver);
		g.drawString(currentText, 50, 300);
		if(frame > ALLOW_ACTION) {
			g.setColor(homeColor);
			g.drawString("Home", 275, 450);
			g.setColor(tryColor);
			g.drawString("Try Again", 575, 450);
		}
	}
	public void actionPerformed(ActionEvent e) {
		requestFocusInWindow();
		frame++;
		if(frame >= DELAY && frame < app.deathText.length() + DELAY) {
			currentText += app.deathText.charAt(frame - DELAY);
		}
	}
	/*
	 * Checks if clicked inside of home or try again
	 */
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if(frame > ALLOW_ACTION) {
			//check if they clicked inside of home
			if(x > 275 && x < 4 * LETTER_WIDTH + 275) 
				if(y > 450 - LETTER_HEIGHT && y < 450) {
					app.setContentPane(app.home);
					Game.dying = false;
					LevelMap.stepOn = true;
				}
			//check if clicked inside of try again
			if(x > 575 && x < 9 * LETTER_WIDTH + 575)
				if(y > 450 - LETTER_HEIGHT && y < 450) {
					app.setContentPane(app.game);
					
					Game.dying = false;
					LevelMap.stepOn = true;
					app.charName = "deltVdeltT";
					app.getMyImage();
					Player.character = app.character;
					player.velX = 0;
					player.left = false;
					player.right = false;
					player.velY = 0;
					if(LevelMap.checkpointsCompleted <= 0) {
						LevelMap.checkpointsCompleted = 0;
						Player.x = LevelMap.initPosX;
						Player.y = LevelMap.initPosY;
					} else {
						Tile tile = app.checkpoints.get(LevelMap.checkpointsCompleted - 1);
						Player.x = tile.bounds.x;
						Player.y = tile.bounds.y;
					}
					app.game.game.map.checkNum = LevelMap.checkpointsCompleted;
					
				}
		}
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		homeColor = Color.WHITE;
		tryColor = Color.WHITE;
		if(frame > ALLOW_ACTION)
			if(x > 275 && x < 4 * LETTER_WIDTH + 275) 
				if(y > 450 - LETTER_HEIGHT && y < 450)
					homeColor = Color.YELLOW;
		if(frame > ALLOW_ACTION)
			if(x > 575 && x < 9 * LETTER_WIDTH + 575)
				if(y > 450 - LETTER_HEIGHT && y < 450)
					tryColor = Color.YELLOW;
				
	}
	
}
