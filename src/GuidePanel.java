import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GuidePanel {
	
	private Image background;
	private int border;
	private int x, y, width, height;
	
	public GuidePanel() {
		loadImages();
		border = 2;
		width = 200;
		height = 90;
		x = (PhysicsDash.WIDTH - width)/2;		
		y = 70;
	}
	
	public void loadImages() {
		try {
			background = ImageIO.read(new File("background_up.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g, InfoPanel info) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
		g.drawImage(background, x + border, y + border, width - 2*border, height - 2*border, null);
		g.drawString("Checkpoint", x + 6, y + 14);
		g.drawString("Try to jump across!", x + 6, y + 35);
		g.drawString("Calculate the inital velocity required:", x + 6, y + 56);
		g.drawString("Angle = " + info.angle.getText() + ", ", x + 6, y + 77);
	}
	
	
}
