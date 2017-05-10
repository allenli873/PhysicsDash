import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
//the GamePanel which holds the game.
public class GamePanel extends JPanel {
	
	private PhysicsDash app;
	private Game game;
	private Notepad notepad;
	private InfoPanel info;
	public boolean onCheckpoint;
	public boolean checkpointJump;

	public GamePanel(PhysicsDash p) {
		app = p;
		game = new Game(p);
		notepad = new Notepad();
		info = new InfoPanel(app, game.player);
		setLayout(null);
		game.setBackground(new Color(60, 60, 80));
		setSize(960, 540);	
		game.setBounds(0, 0, 960, 400);
		info.setBounds(0, 400, 480, 140);
		notepad.setBounds(480, 400, 480, 140);
		add(game);
		add(notepad);
		add(info);
	}
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(!onCheckpoint) {
	        info.velX.setText(String.format("%.2f", game.player.velX));
	        info.velY.setText(String.format("%.2f", game.player.velY));
	        info.vel.setText(String.format("%.2f", Math.hypot(game.player.velX, game.player.velY)));
	        info.posX.setText(String.format("%.2f", game.player.x / Player.PPM));
	        info.posY.setText(String.format("%.2f", game.player.y / Player.PPM));
        }
	}
	
	public void landed() {
		checkpointJump = false;
		game.shouldRequest = true;
		onCheckpoint = false;
		info.reset();
	}
	
	public void checkpointHit() {
		game.shouldRequest = false;
		game.player.left = false;
		game.player.right = false;
//        info.velX.setEnabled(true);
//        info.velY.setEnabled(true);
//        info.posX.setEnabled(true);
//        info.posY.setEnabled(true);
//        info.gapWidth.setEnabled(true);
//        info.angle.setEnabled(true);
		if(!onCheckpoint) {
			info.submit.setEnabled(true);
			info.velX.setText("0.0");
			info.velY.setText("0.0");
			info.vel.setText("0.0");
			info.gapWidth.setText("4.0m");
			info.angle.setText("30 deg");
		}
        onCheckpoint = true;
        //PhysicsDash.loop.stop();
	}
}
