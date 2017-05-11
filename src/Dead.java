import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Dead extends JPanel implements ActionListener {
	protected static Timer timer;
	protected PhysicsDash app;
	protected String currentText;
	protected int frame;
	private final int DELAY = 8;
	
	public Dead(PhysicsDash p) {
		currentText = "";
		frame = 0;
		app = p;
		timer = new Timer(100, this);
		setSize(960, 540);
		setBackground(Color.BLACK);
	}
	public static void dead() {
		timer.start();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Font gameOver = null;
		try {
			 gameOver = Font.createFont(Font.TRUETYPE_FONT, new File("gameover.ttf")).deriveFont(80f);
		     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("gameover.ttf")));
		} catch (IOException | FontFormatException e) {
		     System.err.println("Your font has gone wrong in the hood");
		     System.exit(1);
		}
		g.setColor(Color.WHITE);
		g.setFont(gameOver);
		g.drawString("GAME OVER", 300, 100);
		gameOver = gameOver.deriveFont(40f);
		g.setFont(gameOver);
		g.drawString(currentText, 50, 300);
		
	}
	public void actionPerformed(ActionEvent e) {
		frame++;
		if(frame >= DELAY && frame < app.deathText.length() + DELAY) {
			currentText += app.deathText.charAt(frame - DELAY);
		}
	}
}
