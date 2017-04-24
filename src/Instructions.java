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
	
	public static final int SLIDES = 0;
	
	//declare field variables
	private JButton nextButton;
	private Image[] images;
	private Image done;
	private int slideNum;
	

	public Instructions() {
		setSize(960, 540);
		setBackground(new Color(255, 100, 0));
		nextButton = new JButton("Next");
		
		//use array to store images of instructions
		images = new Image[SLIDES];
		//loadImages();
		
		add(nextButton);
		nextButton.addActionListener(this);
	}
	
	public void loadImages() {
		//loads images into array
		for(int i = 0; i < SLIDES; i++) {
			try {
				images[i] = ImageIO.read(new File("instructions/slide" + i + ".png"));
			}
			catch (IOException e) { 
				e.printStackTrace();
			}
		}
		try {
			done = ImageIO.read(new File("instructions/done.png"));
		}
		catch(IOException e) { 
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//draw appropriate image based on index slideNum, take up whole screen
		//g.drawImage(images[slideNum], 0, 0, getWidth(), getHeight(), this);
	}
	
	public void actionPerformed(ActionEvent e) {
		slideNum++; //go to next image
		if(slideNum >= SLIDES) {
			nextButton.setVisible(false);			
		}
		repaint(); //update graphics
	}
}
