import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
		setBackground(Color.BLUE);
		setLayout(null);
		add(new Levels());
		JButton back = new JButton("Back");
		add(back);
		back.addActionListener(this);
		back.setBounds(50, 475, 150, 40);
	}
	public void actionPerformed(ActionEvent e) {
		app.setContentPane(app.home);
		
	}
	//nested class for the buttons panel
	class Levels extends JPanel implements ActionListener
	{
		public Levels() 
		{
			setBackground(Color.BLUE);
			//sets where the button panel is
			setBounds((app.WIDTH - LEVELS_WIDTH)/2, (app.HEIGHT - LEVELS_WIDTH)/2, LEVELS_WIDTH, LEVELS_WIDTH);
			//adds in buttons and their action listeners
			for(int i = 0; i < 9; i++) 
			{
				JButton btn = new JButton(Integer.toString(i + 1));
				btn.addActionListener(this);
				btn.setBackground(Color.YELLOW);
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
