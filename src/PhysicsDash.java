import javax.swing.JFrame;
import javax.swing.JPanel;

//file to be run
public class PhysicsDash extends JFrame
{
	//field variables
	public static final int WIDTH = 960;
	public static final int HEIGHT = 540;
	public static int level = Integer.MIN_VALUE;
	//the panels of our game
	public JPanel home, credits, levelSelect, game, instructions;
	//constructor
	public PhysicsDash()
	{
		//sets size to 960x540
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		//makes class objects
		credits = new CreditsAndStats(this);
		levelSelect = new LevelSelector();
		instructions = new Instructions();
		home = new HomeScreen(this);
		//sets the content pane
		setContentPane(home);
	}
	public static void main(String[] args)
	{
		PhysicsDash p = new PhysicsDash();
	}
}
