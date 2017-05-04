import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

//file to be run
public class PhysicsDash extends JFrame implements ActionListener
{
	//field variables
	protected final int WIDTH = 960;
	protected final int HEIGHT = 540;
	protected int level, maxLevel, frameAt;
	//statistic variables
	protected int numJumps;
	protected String charName;
	protected Image character;
	protected List<Integer> xEnemy1, yEnemy1;
	//the panels of our game
	public JPanel home, credits, levelSelect, instructions;
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
		maxLevel = 1;
		charName = "deltVdeltT";
		character = null;
		//makes class objects
		credits = new CreditsAndStats(this);
		levelSelect = new LevelSelector(this);
		instructions = new Instructions();
		game = new GamePanel(this);
		home = new HomeScreen(this);
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
		System.out.println("player dies");
	}
	
	public void actionPerformed(ActionEvent e) {
//		System.out.println(frameAt);
		frameAt++;
		repaint();
	}
	
	public static void main(String[] args)
	{
		PhysicsDash p = new PhysicsDash();
		Timer loop = new Timer(16, p);
		loop.start();
	}
}
