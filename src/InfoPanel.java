import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InfoPanel extends JPanel implements ActionListener {
	
	private JLabel vx, vy, px, py, gw, a;	
	public JTextField velX, velY, posX, posY, gapWidth, angle;
	public JButton submit;
	
	public InfoPanel() {
		setSize(490, 140);
		vx = new JLabel("Vel X:");
		vy = new JLabel("Vel Y:");
		px = new JLabel("Pos X:");
		py = new JLabel("Pos Y:");
		gw = new JLabel("Gap Width:");
		a = new JLabel("Angle:");
		
		velX = new JTextField("0.0", 5);
		velY = new JTextField("0.0", 5);
		posX = new JTextField("0.0", 5);
		posY = new JTextField("0.0", 5);
		gapWidth = new JTextField("0.0", 6);
		angle = new JTextField("0.0", 8);
		submit = new JButton("Done");
		
		velX.setEnabled(false);
		velY.setEnabled(false);
		posX.setEnabled(false);
		posY.setEnabled(false);
		gapWidth.setEnabled(false);
		angle.setEnabled(false);
		submit.setEnabled(false);
		
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
		add(submit);
	}
	
	public void actionPerformed(ActionEvent e) {
	
	}
	
}