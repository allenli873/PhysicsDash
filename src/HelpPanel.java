import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HelpPanel extends JFrame {
	
	private PhysicsDash app;
	private Image img, hint1;
	private ImageButton next;
	private int y;
	private String[] lines;
	private int hintNum;
	
	public HelpPanel(PhysicsDash p) {
		super("Help");
		setBounds(300, 300, 400, 400);
		app = p;
		y = 20;
		lines = new String[] {
				"First, split up your velocity into its x and y components.",
				"V<sub>x</sub> = Vcos(θ), V<sub>y</sub> = sin(θ)"
		};
		lines = new String[]{};
		try {
			img = ImageIO.read(new File("background_down.png"));
			hint1 = ImageIO.read(new File("hint1.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 0, 0, 400, 400, this);
		g.drawImage(hint1, 20, 20, this);
	}
	
	public void addText(String text) {
		JLabel label = new JLabel("<html>" + text + "</html>");
		label.setBounds(20, y, WIDTH, 20);
		add(label);
		System.out.println(text);
		y += 25;
		repaint();
	}
	
	public void nextHint() {
		/*if(hintNum < lines.length) {
			addText(lines[hintNum]);
			hintNum++;
		}*/
	}
}
