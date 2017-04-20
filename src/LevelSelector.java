import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class LevelSelector extends JPanel {
	
	public static final int LEVELS_WIDTH = 400;
	
	public LevelSelector() {
		setSize(960, 540);
		setBackground(Color.BLUE);
		setLayout(null);
		add(new Levels());
	}
	
	class Levels extends JPanel {
		public Levels() {
			setBackground(Color.BLUE);
			setBounds((PhysicsDash.WIDTH - LEVELS_WIDTH)/2, (PhysicsDash.HEIGHT - LEVELS_WIDTH)/2, LEVELS_WIDTH, LEVELS_WIDTH);
			for(int i = 0; i < 9; i++) {
				JButton btn = new JButton();
				btn.setBackground(Color.YELLOW);
				add(btn);
			}
			setLayout(new GridLayout(3, 3, 45, 45));
		}
	}
}
