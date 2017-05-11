import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Dead extends JPanel implements ActionListener, MouseListener {
	protected static Timer timer;
	protected PhysicsDash app;
	protected String currentText;
	protected int frame;
	//delay before text starts going
	private final int DELAY = 12;
	//width and height of the level
	private final int LETTER_WIDTH = 18;
	private final int LETTER_HEIGHT = 22;
	//the frame I allow the user to do something
	private final int ALLOW_ACTION = 50;
	public Dead(PhysicsDash p) {
		currentText = "";
		frame = 0;
		app = p;
		timer = new Timer(75, this);
		setSize(960, 540);
		setBackground(Color.BLACK);
		addMouseListener(this);
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
		if(frame > ALLOW_ACTION) {
			g.drawString("Home", 275, 450);
			g.drawString("Try Again", 575, 450);
		}
	}
	public void actionPerformed(ActionEvent e) {
		requestFocusInWindow();
		frame++;
		if(frame >= DELAY && frame < app.deathText.length() + DELAY) {
			currentText += app.deathText.charAt(frame - DELAY);
		}
	}
	public void mouseClicked(MouseEvent e) {
		//check if they clicked inside of home
		int x = e.getX();
		int y = e.getY();
		if(frame > ALLOW_ACTION)
			if(x > 275 && x < 4 * LETTER_WIDTH + 275) 
				if(y > 450 - LETTER_HEIGHT && y < 450)
					app.setContentPane(app.home);
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {}
}
