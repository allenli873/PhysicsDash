import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Game extends JPanel {
	
	public static final int GROUND_HEIGHT = 150;
	
	private PhysicsDash app;
	
	private Character player;
	
	public Game(PhysicsDash p) {
		app = p;
		setSize(960, 540);
		player = new Character(app);
		addKeyListener(player);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		requestFocusInWindow();
		
		g.setColor(new Color(175, 60, 0));
		g.fillRect(0, PhysicsDash.HEIGHT - GROUND_HEIGHT, PhysicsDash.WIDTH, GROUND_HEIGHT);
		player.draw(g);
	}
}
