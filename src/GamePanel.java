import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
//the GamePanel which holds the game.
public class GamePanel extends JPanel {
	
	private PhysicsDash app;
	private Game game;

	public GamePanel(PhysicsDash p) {
		app = p;
		game = new Game(p);
		setLayout(new BorderLayout());
		game.setBackground(Color.GREEN);
		setSize(960, 540);	
		add(game);
	}

}
