import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class HomeScreen extends JPanel implements ActionListener {
	//field variables
	private PhysicsDash app;
	private Image up, down;
	private Image logo;
	
	private ImageButton play, instructions, credits;
	//constructor
	public HomeScreen(PhysicsDash app) {
		//initialize PhysicsDash obj
		this.app = app;
		
		setSize(app.WIDTH, app.HEIGHT);
		setLayout(null);
		setBackground(Color.ORANGE);
		app.charName = "PhysicsDash";
		app.getMyImage();
		app.charName = "deltVdeltT";
		logo = app.character;
		
		int width = 400;
		int height = 275;
		//container panel
		JPanel container = new JPanel();
		container.setBackground(Color.ORANGE);
		container.setLayout(new GridLayout(0, 1, 0, 30));
		container.setBounds((app.WIDTH - width)/2, 60 + (app.HEIGHT - height)/2, width, height);
		//selecting the different options for panels on home screen
		
		play = new ImageButton("Play", "red_up.png");
		instructions = new ImageButton("Instructions", "background_up.png");
		credits = new ImageButton("Credits", "green_up.png");
		play.setColor(new Color(0, 180, 255));
		instructions.setColor(new Color(0, 180, 255));
		credits.setColor(new Color(0, 180, 255));
		play.setPressedImage("red_down.png");
		instructions.setPressedImage("background_down.png");
		credits.setPressedImage("green_down.png");
		play.addActionListener(this);
		instructions.addActionListener(this);
		credits.addActionListener(this);
		//adds the buttons
		container.add(play);
		container.add(instructions);
		container.add(credits);
		//adds the panel
		add(container);
		
	}

	
	//sees which button is selected
	public void actionPerformed(ActionEvent e) {
		String text = e.getActionCommand();
		if(text.equals("Play")) {
			app.levelSelect = new LevelSelector(app);
			app.setContentPane(app.levelSelect);
		}
		
		else if(text.equals("Instructions")) 
			app.setContentPane(app.instructions);
		
		else if(text.equals("Credits")) 
			app.setContentPane(app.credits);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		app.charName = "deltVdeltT";
		app.getMyImage();
		Image len = app.character;
		g.drawImage(len, 750, 250, app.PPM, app.PPM, null);
		g.drawImage(logo, 163, 50, 634, 84, this);
		Graphics2D g2d = (Graphics2D)g;
	}
}