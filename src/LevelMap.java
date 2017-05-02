import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

//
//loads map, handles collisions
public class LevelMap {

	private Tile[][] map; //2d array of tiles makes up the map
	private Graphics g;
	
	private Image brick;
	private Character player;

	public LevelMap(Character _player, int level) {
		player = _player;
		loadLevel("levels/map" + level + ".lvl");
		loadImages();
	}
	
	public void loadImages() {
		try {
			brick = ImageIO.read(new File("basic128.png"));
		}
		catch(IOException e) { 
			e.printStackTrace();
		}
	}
	
	public void loadLevel(String name) {
		Scanner in = null;
		try {
			in = new Scanner(new File(name));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int width = in.nextInt(); //read in width and height of level
		int height = in.nextInt();
		in.nextLine();
		
		map = new Tile[height][width];
		for(int row = 0; row < height; row++) { //row is ycoord
			String line = in.nextLine();
			for(int col = 0; col < width; col++) { //col is xcoord
				char cTile = line.charAt(col);
				//set x and y, add to map
				Tile t = new Tile();
				t.bounds.x = col * Tile.WIDTH;
				t.bounds.y = row * Tile.HEIGHT;
				if(cTile == '#') {
					t.type = Tile.BRICK;
				}
				if(cTile == 'P') {
					player.x = col * Tile.WIDTH;
					player.y = row * Tile.HEIGHT;
				}
				map[row][col] = t;
				//assign image type based on char
			}
		}
	}
	
	private boolean inBounds(int row, int col) {
		return row >= 0 && row < map.length && col >= 0 && col < map[0].length;
	}
	private boolean intersects(int row, int col, int dx, int dy) {
		g.setColor(Color.GREEN);
		//g.fillRect(col * 60, row * 60, 60, 60);
		if(!inBounds(row, col)) return false;
		Tile t = map[row][col];
		boolean int1 = t.type != Tile.NONE && t.bounds.intersects(player.x, player.y, player.w, player.h);
		if(int1) {
			//System.out.println("INT1");
			return true;
		}
		if(!inBounds(row + dy, col + dx)) return false;
		t = map[row + dy][col + dx];
		g.setColor(Color.RED);
		//g.fillRect((col+dx) * 60, (row+dy) * 60, 60, 60);
		boolean int2 = t.type != Tile.NONE && t.bounds.intersects(player.x, player.y, player.w, player.h);
		//System.out.println("int2: " + int2);
		return int2;
	}
	 
	//COLLISION DETECTION:
	//1. get player coords
	//2. convert to map coords
	//3. compare intersection to tiles around player
	//4. set velX or velY to 0 based on intersection
	public void step(Graphics g) { //simulate collisions 
		this.g = g;
		Rectangle pBounds = new Rectangle((int) player.x, (int) player.y, player.w, player.h);
		int playerRow = (int) (player.y / Tile.HEIGHT);
		int playerRow2 = (int) Math.ceil(player.y / Tile.HEIGHT);
		int playerCol = (int) (player.x / Tile.WIDTH);
		int playerCol2 = (int) Math.ceil(player.x / Tile.WIDTH);
		int dX = player.x % Tile.WIDTH > Tile.WIDTH/2 ? 1 : -1;
		int dY = player.y % Tile.HEIGHT > Tile.HEIGHT/2 ? 1 : -1;
		
		if(intersects(playerRow + 1, playerCol, dX, 0) && player.velY > 0) {
			player.jumped = false;
			player.velY = 0;
			player.y = playerRow * Tile.HEIGHT;
		}
		if(intersects(playerRow2 - 1, playerCol, dX, 0) && player.velY < 0) {
			player.velY = 0;
			player.y = playerRow2 * Tile.HEIGHT;
		}
		if(intersects(playerRow, playerCol2 - 1, 0, dY) && player.velX < 0) {
			player.velX = 0;
			player.x = playerCol2 * Tile.WIDTH;
		}
		if(intersects(playerRow, playerCol + 1, 0, dY) && player.velX > 0) {
			player.velX = 0;
			player.x = playerCol * Tile.WIDTH;
		}
	}
	
	public void draw(Graphics g) {
		for(int row = 0; row < map.length; row++) {
			for(int col = 0; col < map[0].length; col++) {
				if(map[row][col].type == Tile.BRICK) {
					g.drawImage(brick, col * Tile.WIDTH, row * Tile.HEIGHT, Tile.WIDTH, Tile.HEIGHT, null);
					//g.setColor(Color.RED);
					//g.fillRect(col * Tile.WIDTH, row * Tile.HEIGHT, Tile.WIDTH, Tile.HEIGHT);
					//System.out.println("brick");
				}
			}
		}		
	}
}
