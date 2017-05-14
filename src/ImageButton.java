import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ImageButton extends JButton implements MouseListener {
	
	private String text;
	private Color c, tmp;
	private Font font;
	private JLabel textLabel;
	private Image image, hoveredImage, pressedImage;
	private boolean hovered, pressed;
	
	public ImageButton(String _text, String name) {
		this(_text, name, 50);
	}
	public ImageButton(String _text, String name, int fontSize) {
		super(_text);
		text = _text;
		c = Color.BLACK;
		tmp = c;
		image = loadImage(name);
		hoveredImage = image;
		pressedImage = image;
		setBorderPainted(false);
		setLayout(null);
		textLabel = new JLabel(text, JLabel.CENTER);
		loadFont(fontSize);
		textLabel.setFont(font);
		
		add(textLabel);
		addMouseListener(this);
	}
	
	public void setColor(Color color) {
		c = color;
		textLabel.setForeground(color);
	}
	
	public void setEnabled(boolean en) {
		super.setEnabled(en);
		if(!en) {
			tmp = Color.GRAY;
			pressedImage = image;
		}
		else {
			tmp = c;
		}
		textLabel.setForeground(tmp);
	}
	
	public void loadFont(float fontSize) {
		try {
			 font = Font.createFont(Font.TRUETYPE_FONT, new File("gameover.ttf")).deriveFont(fontSize);
		     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("gameover.ttf")));
		} catch (IOException | FontFormatException e) {
		     System.err.println("Your font has gone wrong in the hood");
		     System.exit(1);
		}
	}
	
	public void setHoveredImage(String name) {
		hoveredImage = loadImage(name);
	}
	public void setPressedImage(String name) {
		pressedImage = loadImage(name);
	}
	
	public Image loadImage(String name) {
		Image img = null;
		try {
			img = ImageIO.read(new File(name));
		}
		catch(IOException e) { }
		return img;
	}
	
	public void mouseEntered(MouseEvent e) {
		hovered = true;
	}
	public void mouseExited(MouseEvent e) {
		hovered = false;
	}
	public void mousePressed(MouseEvent e) {
		pressed = true;
	}
	public void mouseReleased(MouseEvent e) {
		pressed = false;
	}
	public void mouseClicked(MouseEvent e) { }
	
	public void paintComponent(Graphics g) {
		textLabel.setBounds(0, 0, getWidth(), getHeight());
		Image i = image;
		if(hovered) i = hoveredImage;
		if(pressed) i = pressedImage;
		g.drawImage(i, 0, 0, getWidth(), getHeight(), this);
	}
	
	
}