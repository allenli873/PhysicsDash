import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
//class header
public class CreditsAndStats extends JPanel implements ActionListener
{
	
	private PhysicsDash app;
	
	//constructor
	public CreditsAndStats(PhysicsDash p)
	{
		app = p;
		setSize(960, 540);
		setBackground(new Color(255, 100, 0));
		setLayout(new FlowLayout(FlowLayout.LEFT, 50, 40));
		JButton back = new JButton("Back");
		add(new Credits());
		add(new Statistics());
		back.addActionListener(this);
		add(back);
	}
	//back button
	public void actionPerformed(ActionEvent e) {
		app.setContentPane(app.home);
	}
	class Credits extends JPanel
	{
		public Credits()
		{
			setPreferredSize(new Dimension(400, 400));
			setBackground(Color.ORANGE);
			
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setFont(new Font("Sans Serif", Font.PLAIN, 20));
			g.drawString("Credits", 25, 40);
			g.drawString("Game Creators:", 25, 80);
			g.drawString("Allen Li", 25, 110);
			g.drawString("Kirtan Shah", 25, 130);
			g.drawString("Advisor:", 25, 175);
			g.drawString("Mr. Lordan", 25, 205);
			g.drawString("Amazing teacher:", 25, 250);
			g.drawString("Mr. Mueller", 25, 280);
		}
	}
	
	class Statistics extends JPanel
	{
		public Statistics()
		{
			setPreferredSize(new Dimension(400, 400));
			setBackground(Color.ORANGE);
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setFont(new Font("Sans Serif", Font.PLAIN, 20));
			g.drawString("Statistics:", 25, 40);
			g.drawString("Times Jumped: " + app.numJumps, 25, 80);
		}
		
	}
}

