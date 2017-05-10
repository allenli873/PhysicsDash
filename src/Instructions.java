import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Instructions extends JPanel implements ActionListener {
	
	public static final int SLIDES = 2;
	
	//declare field variables
	private JButton nextButton, backButton;
	private boolean lastSlide = false;
	private Image[] images;
	private Image done;
	private int slideNum;
	private PhysicsDash app;

	public Instructions(PhysicsDash p) {
		app = p;
		setSize(960, 540);
		setBackground(new Color(255, 100, 0));
		backButton = new JButton("Back");
		nextButton = new JButton("Next");
		
		//use array to store images of instructions
		images = new Image[SLIDES + 1];
		loadImages();
		slideNum = 1;
		add(backButton);
		add(nextButton);
		backButton.addActionListener(this);
		nextButton.addActionListener(this);
	}
	
	public void loadImages() {
		//loads images into array
		for(int i = 1; i <= SLIDES; i++) {
			try {
				images[i] = ImageIO.read(new File("Instructions/slide" + i + ".png"));
			}
			catch (IOException e) { 
				e.printStackTrace();
			}
		}
//		try {
//			done = ImageIO.read(new File("Instructions/done.png"));
//		}
//		catch(IOException e) { 
//			e.printStackTrace();
//		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//draw appropriate image based on index slideNum, take up whole screen
		g.drawImage(images[slideNum], 0, 0, getWidth(), getHeight(), this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Finish")) {
			app.setContentPane(app.home);
			lastSlide = false;
		}
		
		if(e.getActionCommand().equals("Next")) {
			if(slideNum >= SLIDES) {
				nextButton.setText("Finish");
				lastSlide = true;
			} else {
				slideNum++; //go to next image
			}
		} 
		if(e.getActionCommand().equals("Back")) {
			slideNum--;
			lastSlide = false;
			nextButton.setText("Next");
			if(slideNum < 1) {
				app.setContentPane(app.home);
				slideNum = 1;
			}
		}
		repaint(); //update graphics
	}
}
