import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GuidePanel implements MouseListener {
	
	private PhysicsDash app;
	private HelpPanel hp;
	private Image background, up, down, help_image;
	private int border;
	private int x, y, width, height;
	private boolean hpOpen;
	public GuidePanel(PhysicsDash p) {
		hpOpen = false;
		app = p;
		loadImages();
		border = 3;
		width = 200;
		height = 125;
		x = (PhysicsDash.WIDTH - width)/3;		
		y = 50;
		hp = new HelpPanel(app);
	}
	
	public void loadImages() {
		try {
			background = ImageIO.read(new File("background_up.png"));			
			up = ImageIO.read(new File("green_up.png"));
			down = ImageIO.read(new File("green_down.png"));
			help_image = up;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void mousePressed(MouseEvent e) {
		int ex = e.getX();
		int ey = e.getY();
		if(ex > x + 50 && ex < x + 150 && ey > y + 100 && ey < y + 115)
			help_image = down;
	}
	public void mouseReleased(MouseEvent e) {
		int ex = e.getX();
		int ey = e.getY();
		if(ex > x + 50 && ex < x + 150 && ey > y + 100 && ey < y + 115) {
			help_image = up;
			hp.setVisible(true);
			hp.repaint();
		}
	}
	public void mouseClicked(MouseEvent e) {

	}
	
	public void draw(Graphics g, InfoPanel info) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
		g.drawImage(background, x + border, y + border, width - 2*border, height - 2*border, null);
		g.drawString("Checkpoint", x + 6, y + 14);
		g.drawString("Try to jump across!", x + 6, y + 35);
		g.drawString("Calculate the inital velocity required", x + 6, y + 56);
		g.drawString("with the info at the bottom left", x + 6, y + 77);
		g.drawImage(help_image, x + 50, y + 100, 100, 15, null);
		g.drawString("Hints", x + 85, y + 110);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
