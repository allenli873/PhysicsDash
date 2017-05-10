import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InfoPanel extends JPanel implements ActionListener, KeyListener {
	
	private PhysicsDash app;
	private Player player;
	private JLabel vx, vy, px, py, gw, a;	
	private JLabel v, dy, dx;
	public JTextField velX, velY, posX, posY, gapWidth, angle;
	public JTextField vel, deltaX, deltaY;
	public JButton submit;
	
	public InfoPanel(PhysicsDash p, Player c) {
		app = p;
		player = c;
		setSize(490, 140);
		vx = new JLabel("Vel X:");
		vy = new JLabel("Vel Y:");
		px = new JLabel("Pos X:");
		py = new JLabel("Pos Y:");
		gw = new JLabel("Gap Width:");
		a = new JLabel("Angle:");
		v = new JLabel("Velocity:");
		dy = new JLabel("Disp. X");
		dx = new JLabel("Disp. Y");
		
				
		velX = new JTextField("0.0", 5);
		velY = new JTextField("0.0", 5);
		posX = new JTextField("0.0", 5);
		posY = new JTextField("0.0", 5);
		gapWidth = new JTextField("0.0", 6);
		angle = new JTextField("0.0", 8);
		vel = new JTextField("0.0", 5);
		deltaX = new JTextField("0.0", 5);
		deltaY = new JTextField("0.0", 5);
		submit = new JButton("Done");
		
		velX.setEnabled(false);
		velY.setEnabled(false);
		posX.setEnabled(false);
		posY.setEnabled(false);
		gapWidth.setEnabled(false);
		angle.setEnabled(false);
		submit.setEnabled(false);
		reset();
		
		submit.addActionListener(this);
		
		add(vx);
		add(velX);
		add(vy);
		add(velY);
		add(px);
		add(posX);
		add(py);
		add(posY);
		add(gw);
		add(gapWidth);
		add(a);
		add(angle);
		add(v);
		add(vel);
		add(dx);
		add(deltaX);
		add(dy);
		add(deltaY);
		add(submit);
	}
	
	public void reset() {
		velX.setEnabled(false);
		velY.setEnabled(false);
		posX.setEnabled(false);
		posY.setEnabled(false);
		gapWidth.setEnabled(false);
		angle.setEnabled(false);
		submit.setEnabled(false);
	}
	
	public void actionPerformed(ActionEvent e) {
		String aText = angle.getText();
		aText = aText.substring(0, aText.indexOf(' '));
		System.out.println(aText);
		float mag = Float.parseFloat(vel.getText());
		float theta = (float) Math.toRadians(Double.parseDouble(aText));
		player.velX = mag * (float) Math.cos(theta);
		player.velY = -mag * (float) Math.sin(theta);
		app.game.onCheckpoint = false;
		app.game.checkpointJump = true;
		player.jumped = true;
		requestFocusInWindow();
	}
	
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if(c == KeyEvent.VK_1) {
			PhysicsDash.loop.stop();
		}
		if(c == KeyEvent.VK_2) {
			PhysicsDash.loop.start();
		}
	}
	public void keyReleased(KeyEvent e) {
		
	}
	public void keyTyped(KeyEvent w) {
		
	}
	
}
