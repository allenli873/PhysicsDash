import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
//the GamePanel which holds the game.
public class GamePanel extends JPanel {
	
	private PhysicsDash app;
	private Game game;
	private Notepad notepad;
	private InfoPanel info;
	private boolean onCheckpoint;

	public GamePanel(PhysicsDash p) {
		app = p;
		game = new Game(p);
		notepad = new Notepad();
		info = new InfoPanel();
		setLayout(null);
		game.setBackground(new Color(60, 60, 80));
		setSize(960, 540);	
		//sets game panel, info panel, and notepad panel
		game.setBounds(0, 0, 960, 400);
		info.setBounds(0, 400, 480, 140);
		notepad.setBounds(480, 400, 480, 140);
		//adds them to the JPanel
		add(game);
		add(notepad);
		add(info);
	}
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //checks if on the checkpoint or not, if not, repeatedly change the values
        if(!onCheckpoint) {
	        info.velX.setText(String.format("%.2f", game.player.velX));
	        info.velY.setText(String.format("%.2f", game.player.velY));
	        info.posX.setText(String.format("%.2f", game.player.x / Player.PPM));
	        info.posY.setText(String.format("%.2f", game.player.y / Player.PPM));
        }
	}
	//checks if currently touching a checkpoint
	public void checkpointHit() {
		game.shouldRequest = false;
		onCheckpoint = true;
		//sets the info boxes to how the problem is layed out
        info.velX.setEnabled(true);
        info.velY.setEnabled(true);
        info.submit.setEnabled(true);
        info.velX.setText("0.0");
        info.velY.setText("0.0");
        info.gapWidth.setText("4.0m");
        info.angle.setText("30 deg");
        PhysicsDash.loop.stop();
	}
}
