import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

//file to be run
public class PhysicsDash extends JFrame implements ActionListener
{
	//field variables
	protected final int WIDTH = 960;
	protected final int HEIGHT = 540;
	protected int level;
	protected int maxLevel;
	protected int frameAt;
	protected String charName;
	protected Image character;
	//the panels of our game
	public JPanel home, credits, levelSelect, game, instructions;
	//constructor
	public PhysicsDash()
	{
		//sets size to 960x540
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		//initialize things
		level = 1;
		maxLevel = 1;
		charName = "deltVdeltT.png";
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
			character = ImageIO.read(new File(charName));
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void playerDies() {
		//put something actual here later
		System.exit(0);
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
