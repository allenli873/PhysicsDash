import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CreditsAndStats extends JPanel
{
	public CreditsAndStats()
	{
		setSize(960, 540);
		setBackground(new Color(255, 100, 0));
		setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
		add(new Credits());
		add(new Statistics());
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
			g.drawString("hi guise", 50, 50);
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
		}
	}
}

