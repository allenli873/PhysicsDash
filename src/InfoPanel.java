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
	private JLabel v, height;
	public JTextField velX, velY, posX, posY, gapWidth, angle;
	public JTextField vel, hei;
	public JButton submit;
	public long shouldDie;
	public static boolean flying;
	
	public InfoPanel(PhysicsDash p, Player c) {
		app = p;
		player = c;
		shouldDie = 0;
		setSize(490, 140);
		flying = false;
		vx = new JLabel("Vel X:");
		vy = new JLabel("Vel Y:");
		px = new JLabel("Pos X:");
		py = new JLabel("Pos Y:");
		gw = new JLabel("Gap Width:");
		a = new JLabel("Angle:");
		v = new JLabel("Velocity:");
		height = new JLabel("Jump Height:");
				
		velX = new JTextField("0.0", 5);
		velY = new JTextField("0.0", 5);
		posX = new JTextField("0.0", 5);
		posY = new JTextField("0.0", 5);
		gapWidth = new JTextField("0.0", 6);
		angle = new JTextField("0.0", 8);
		vel = new JTextField("0.0", 5);
		hei = new JTextField("0.0", 5);
		submit = new JButton("Done");
		
		velX.setEnabled(false);
		velY.setEnabled(false);
		posX.setEnabled(false);
		posY.setEnabled(false);
		gapWidth.setEnabled(false);
		angle.setEnabled(false);
		hei.setEnabled(false);
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
		add(height);
		add(hei);
		add(v);
		add(vel);
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
		float velInput = Float.parseFloat(vel.getText());
		float mag = velInput - 0.1f;
		float theta = (float) Math.toRadians(Double.parseDouble(aText));
		player.velX = mag * (float) Math.cos(theta);
		player.velY = -mag * (float) Math.sin(theta);
		app.game.onCheckpoint = false;
		app.game.checkpointJump = true;
		player.jumped = true;
		flying = true;
		double gw = Double.parseDouble(gapWidth.getText());
		float answer = (float) Math.sqrt((9.8*gw)/(2*Math.sin(theta)*Math.cos(theta)));
		System.out.println(answer);
		if(Math.abs(velInput - answer) > 0.1) {
			shouldDie = System.currentTimeMillis();
		}
		else {
			LevelMap.checkpointsCompleted++;
		}
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
