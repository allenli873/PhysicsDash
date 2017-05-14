import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

//the level selector portion
public class LevelSelector extends JPanel implements ActionListener
{
	//field variables
	private static final int LEVELS_WIDTH = 400;
	private PhysicsDash app;
	//constructor
	public LevelSelector(PhysicsDash app) 
	{
		this.app = app;
		setSize(app.WIDTH, app.HEIGHT);
		setBackground(new Color(255, 100, 0));
		setLayout(null);
		
		JLabel title = new JLabel("Level Select");
		Font font = new Font(Font.MONOSPACED, Font.BOLD, 50);
		title.setFont(font);
		title.setBounds(5, 5, 960, 50);
		Levels lvls = new Levels();
		ImageButton back = new ImageButton("", "home_press.png");
		back.setPressedImage("home_unpress.png");
		add(title);
		add(new Levels());
		add(back);
		back.addActionListener(this);
		back.setBounds(850, 440, 50, 50);
	}
	public void actionPerformed(ActionEvent e) {
		app.setContentPane(app.home);
		
	}
	//nested class for the buttons panel
	class Levels extends JPanel implements ActionListener
	{
		public Levels() 
		{
			setBackground(new Color(255, 100, 0));
			//sets where the button panel is
			setBounds((app.WIDTH - LEVELS_WIDTH)/2, 75, LEVELS_WIDTH, LEVELS_WIDTH);
			//adds in buttons and their action listeners
			for(int i = 0; i < 9; i++)
			{
				ImageButton btn = new ImageButton(Integer.toString(i + 1), "background_up.png");
				btn.setPressedImage("background_down.png");
				btn.addActionListener(this);
				btn.setEnabled(i < app.maxLevel);
				add(btn);
			}
			//sets as a grid layout
			setLayout(new GridLayout(3, 3, 45, 45));
		}
		//when button is pressed
		public void actionPerformed(ActionEvent e) 
		{
			String str = e.getActionCommand();
			app.level = Integer.parseInt(str);
			app.game = new GamePanel(app);
			app.setContentPane(app.game);
			System.out.println(app.level);
		}
	}
	
}