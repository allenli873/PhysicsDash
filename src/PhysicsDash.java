import javax.swing.JFrame;
import javax.swing.JPanel;
//file to be run
public class PhysicsDash
{
	public static final int WIDTH = 960;
	public static final int HEIGHT = 540;
	
	public JPanel credits, levelSelect, game, instructions;
	public PhysicsDash()
	{
		JFrame frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		credits = new CreditsAndStats();
		levelSelect = new LevelSelector();
		frame.setContentPane(credits);
	}
	public static void main(String[] args)
	{
		PhysicsDash p = new PhysicsDash();
	}
}
