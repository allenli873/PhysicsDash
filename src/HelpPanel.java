import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class HelpPanel extends JFrame {
	
	private PhysicsDash app;
	private ImageButton next;
	private JTextArea content;
	private int y;
	
	public HelpPanel(PhysicsDash p) {
		super("Help");
		app = p;
		y = 20;
		setLayout(null);
		addText("First, split up your velocity into its x and y components.");
		addText("V<sub>x</sub> = Vcos(θ), V<sub>y</sub> = sin(θ)");
	}
	
	public void addText(String text) {
		JLabel label = new JLabel("<html>" + text + "</html>");
		label.setBounds(20, y, WIDTH, 20);
		add(label);
		y += 25;
		repaint();
	}
	
}
