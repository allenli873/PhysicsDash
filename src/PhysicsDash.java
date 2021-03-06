import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

//file to be run
public class PhysicsDash extends JFrame implements ActionListener
{
	//field variables
	public static Timer loop;
	protected final int PPM = 120;
	protected final static int WIDTH = 960;
	protected final static int HEIGHT = 540;
	protected int level, maxLevel, frameAt;
	//statistic variables
	protected int numChecks;
	protected int numJumps;
	public static boolean instructionsViewed;
	public static int totalChecks;
	protected ArrayList<Tile> checkpoints;
	protected String charName, deathText;
	protected Image character;
	protected int finishX;
	//the panels of our game
	public JPanel home, credits, levelSelect, instructions, dead, levelComplete;
	public GamePanel game;
	//constructor
	public PhysicsDash()
	{
		totalChecks = 0;
		numJumps = 0;
		numChecks = 0;
		//sets size to 960x540
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		//initialize things
		level = 1;
		maxLevel = 3;
		charName = "deltVdeltT";
		character = null;
		//makes class objects
		credits = new CreditsAndStats(this);
		levelSelect = new LevelSelector(this);
		game = new GamePanel(this);
		home = new HomeScreen(this);
		instructions = new Instructions(this, (HomeScreen) home); 
		dead = new Dead(this, game.game.player);
		levelComplete = new LevelComplete(this, game.game.player);
		
		//sets the content pane
		setContentPane(home);
	}
	
	public void getMyImage() {
		try 
		{
			character = ImageIO.read(new File(charName + ".png"));
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void levelComplete() {
		LevelComplete.completed("Level " + level + " Completed!");
		setContentPane(levelComplete);
	}
	public void playerDies(String msg) {
		//put something actual here later
		Scanner in = null;
		try {
			in = new Scanner(new File("deathText.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		deathText = in.nextLine();
		//starts the death animating
		Dead.dead(msg);
		setContentPane(dead);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		repaint();
	}
	
	public static void main(String[] args)
	{
		PhysicsDash p = new PhysicsDash();
		loop = new Timer(16, p);
		loop.start();
	}
}
