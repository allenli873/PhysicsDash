import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
//the level selector portion
public class LevelSelector extends JPanel 
{
	//field variable
	public static final int LEVELS_WIDTH = 400;
	//constructor
	public LevelSelector() 
	{
		setSize(PhysicsDash.WIDTH, PhysicsDash.HEIGHT);
		setBackground(Color.BLUE);
		setLayout(null);
		add(new Levels());
	}
	//nested class for the buttons panel
	class Levels extends JPanel implements ActionListener
	{
		public Levels() 
		{
			setBackground(Color.BLUE);
			//sets where the button panel is
			setBounds((PhysicsDash.WIDTH - LEVELS_WIDTH)/2, (PhysicsDash.HEIGHT - LEVELS_WIDTH)/2, LEVELS_WIDTH, LEVELS_WIDTH);
			//adds in buttons and their action listeners
			for(int i = 0; i < 9; i++) 
			{
				JButton btn = new JButton(Integer.toString(i + 1));
				btn.addActionListener(this);
				btn.setBackground(Color.YELLOW);
				add(btn);
			}
			//sets as a grid layout
			setLayout(new GridLayout(3, 3, 45, 45));
		}
		//when button is pressed
		public void actionPerformed(ActionEvent e) 
		{
			String str = e.getActionCommand();
			PhysicsDash.level = Integer.parseInt(str);
			System.out.println(PhysicsDash.level);
		}
	}
}
