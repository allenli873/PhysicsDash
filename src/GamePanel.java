import java.awt.*;
import java.awt.Color;
import javax.swing.JPanel;

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
