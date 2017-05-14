import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class HelpPanel extends JPanel {
	
	private ImageButton next;
	
	public HelpPanel() {
		//setLayout(new GridLayout(0, 1));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawString("First, split up your velocity into its x and y components.", 20, 20);
		g.drawString("V<sub>x</sub> = Vcos(θ), V<sub>y</sub> = sin(θ)", 20, 40);

	}
	
}
