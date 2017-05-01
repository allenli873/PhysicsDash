import java.awt.Rectangle;

public class Tile {
	//fixed dimensions
	public static final int WIDTH = 60;
	public static final int HEIGHT = 60;
	
	//types
	public static final int NONE = 0;
	public static final int BRICK = 1;
	
	//used for methods like intersects(), hit(), etc.
	public Rectangle bounds;
	
	public int type;
	
	public Tile() {
		type = NONE;
		bounds = new Rectangle();
		//default width and height
		bounds.width = WIDTH;
		bounds.height = HEIGHT;
	}
}
