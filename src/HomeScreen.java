import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class HomeScreen extends JPanel implements ActionListener {
	
	private PhysicsDash app;
	private JButton play, instructions, credits;
	
	public HomeScreen(PhysicsDash app) {
		this.app = app;
		
		setSize(PhysicsDash.WIDTH, PhysicsDash.HEIGHT);
		setLayout(null);
		
		int width = 400;
		int height = 275;
		
		JPanel container = new JPanel();
		container.setLayout(new GridLayout(0, 1, 0, 30));
		container.setBounds((PhysicsDash.WIDTH - width)/2, 60 + (PhysicsDash.HEIGHT - height)/2, width, height);
		
		play = new JButton("Play");
		instructions = new JButton("Instructions");
		credits = new JButton("Credits");
		play.addActionListener(this);
		instructions.addActionListener(this);
		credits.addActionListener(this);
		
		container.add(play);
		container.add(instructions);
		container.add(credits);
		add(container);
	}
	
	public void actionPerformed(ActionEvent e) {
		String text = e.getActionCommand();
		if(text.equals("Play")) {
			app.setContentPane(app.levelSelect);
		}
		else if(text.equals("Instructions")) {
			System.out.println("hi");
			app.setContentPane(app.instructions);
		}
		else if(text.equals("Credits")) {
			app.setContentPane(app.credits);
		}
	}
}
