import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class HelpPanel extends JFrame implements ActionListener {
	
	private PhysicsDash app;
	private Image img;
	private Image[] hints;
	private ImageButton next;
	private int y;
	private String[] lines;
	private int hintNum;
	
	public HelpPanel(PhysicsDash p) {
		super("Help");
		setBounds(300, 300, 600, 700);
		setLayout(null);
		app = p;
		y = 20;
		hintNum = 1;
		hints = new Image[7];
		try {
			img = ImageIO.read(new File("background_down.png"));
			for(int i = 1; i <= hints.length; i++)
				hints[i - 1] = ImageIO.read(new File("hint" + i + ".png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		next = new ImageButton("Next Hint", "red_up.png", 30);
		next.setPressedImage("red_down.png");
		next.setBounds(200, 600, 200, 50);
		next.addActionListener(this);
		add(next);
		addWindowListener(new WindowResponse());
	}
	
	public void actionPerformed(ActionEvent e) {
		nextHint();
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 0, 0, 600, 700, this);
		g.drawString("HINTS", 20, 60);
		int y = 70;
		for(int i = 0; i < hintNum; i++) {
			g.drawImage(hints[i], 20, y, this);
			y += hints[i].getHeight(this) + 10;
		}
	}
	
	public void nextHint() {
		hintNum++;
		if(hintNum > hints.length)
			hintNum = hints.length;
	}

	class WindowResponse extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			nextHint();
		}
	}
}
