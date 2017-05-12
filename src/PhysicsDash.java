import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
	protected final int WIDTH = 960;
	protected final int HEIGHT = 540;
	protected int level, maxLevel, frameAt;
	//statistic variables
	protected int numJumps;
	
	protected String charName, deathText;
	protected Image character;
	protected List<Integer> xEnemy1, yEnemy1;
	//the panels of our game
	public JPanel home, credits, levelSelect, instructions, dead;
	public GamePanel game;
	//constructor
	public PhysicsDash()
	{
		numJumps = 0;
		
		xEnemy1 = new ArrayList<Integer>();
		yEnemy1 = new ArrayList<Integer>();
		//sets size to 960x540
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		//initialize things
		level = 1;
		maxLevel = 2;
		charName = "deltVdeltT";
		character = null;
		//makes class objects
		credits = new CreditsAndStats(this);
		levelSelect = new LevelSelector(this);
		instructions = new Instructions(this);
		game = new GamePanel(this);
		home = new HomeScreen(this);
		dead = new Dead(this);
		
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
	
	public void playerDies() {
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
		Dead.dead();
		setContentPane(dead);
	}
	
	public void actionPerformed(ActionEvent e) {
		
//		System.out.println(frameAt);
		frameAt++;
		repaint();
	}
	
	public static void main(String[] args)
	{
		PhysicsDash p = new PhysicsDash();
		loop = new Timer(16, p);
		loop.start();
	}
}
