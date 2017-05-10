import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.ImageIO;

//
//loads map, handles collisions
public class LevelMap {

	private Tile[][] map; //2d array of tiles makes up the map
	private Graphics g;
	private Game game;
	private Image brick, cpImage;
	private Player player;
	private PhysicsDash app;
	private ArrayList<Tile> checkpoints;
	public LevelMap(Player _player, int level, Game game, PhysicsDash p) {
		checkpoints = new ArrayList<Tile>();
		app = p;
		this.game = game;
		player = _player;
		loadLevel("levels/map" + level + ".lvl");
		loadImages();
	}
	
	public void loadImages() {
		try {
			brick = ImageIO.read(new File("basic128.png"));
			cpImage = ImageIO.read(new File("flag.png"));
		}
		catch(IOException e) { 
			e.printStackTrace();
		}
	}
	
	public void loadLevel(String name) 
	{
		Scanner in = null; 
		try 
		{
			in = new Scanner(new File(name));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			System.exit(1);
		}
		int width = in.nextInt(); //read in width and height of level
		int height = in.nextInt();
		in.nextLine();
		
		map = new Tile[height][width];
		for(int i = 0; i < height; i++) {
			Arrays.fill(map[i], new Tile());
		}
		int row = -1;
		try
		{
			while(in.hasNextLine())
			{ 
				row++;
				//row is ycoord
				String line = "";
				if(in.hasNextLine()) {
					line = in.nextLine();
//					line = line.length() < width ? line : line.substring(0, width);
				}
				else
					break;
				System.out.println(line);
				System.out.println(line.length());
				for(int col = 0; col < line.length(); col++) 
				 { 
					//col is xcoord
					char cTile = line.charAt(col);
					//set x and y, add to map
					Tile t = new Tile();
					t.bounds.x = col * Tile.WIDTH;
					t.bounds.y = row * Tile.HEIGHT;
					if(cTile == '#') 
					{
						t.type = Tile.BRICK;
					}
					else if(cTile == 'P') 
					{
						player.x = col * Tile.WIDTH;
						player.y = row * Tile.HEIGHT;
					}
					else if(cTile == '|') 
					{
						t.type = Tile.CHECKPOINT;
					}
					else if(cTile == '1') 
					{
						app.xEnemy1.add(col * Tile.WIDTH);
						app.yEnemy1.add(row * Tile.HEIGHT + 35);
					}
					System.out.println(col);
					map[row][col] = t;
					//assign image type based on char
				}
			}
		} catch(StringIndexOutOfBoundsException e) {
			System.err.println("Error: You're retarded and you made the level wrong."); 
			System.exit(1);
		}
		
		for(int col = 0; col < width; col++) {
			for(int row = 0; row < height; row++) {
				if(map[row][col].type == Tile.CHECKPOINT) {
					checkpoints.add(map[row][col]);
				}
			}
		}
	}
	
	private boolean inBounds(int row, int col) 
	{
		return row >= 0 && row < map.length && col >= 0 && col < map[0].length;
	}
	private boolean intersects(int row, int col, int dx, int dy) 
	{
		g.setColor(Color.GREEN);
		//g.fillRect(col * 60, row * 60, 60, 60);
		if(!inBounds(row, col)) return false;
		Tile t = map[row][col];
		boolean int1 = t.type == Tile.BRICK && t.bounds.intersects(player.x, player.y, player.w, player.h);
		if(int1) {
			//System.out.println("INT1");
			return true;
		}
		if(!inBounds(row + dy, col + dx)) return false;
		t = map[row + dy][col + dx];
		g.setColor(Color.RED);
		//g.fillRect((col+dx) * 60, (row+dy) * 60, 60, 60);
		boolean int2 = t.type == Tile.BRICK && t.bounds.intersects(player.x, player.y, player.w, player.h);
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
		
		if(intersects(playerRow + 1, playerCol, 1, 0) && player.velY > 0) {
			player.jumped = false;
			player.velY = 0;
			player.y = playerRow * Tile.HEIGHT;
		}
		if(intersects(playerRow2 - 1, playerCol, 1, 0) && player.velY < 0) { //up
			player.velY = 0;
			player.y = playerRow2 * Tile.HEIGHT;
		}
		if(intersects(playerRow, playerCol2 - 1, 0, 1) && player.velX < 0) { //left
			player.velX = 0;
			player.x = playerCol2 * Tile.WIDTH;
		}
		if(intersects(playerRow, playerCol + 1, 0, 1) && player.velX > 0) { //right
			player.velX = 0;
			player.x = playerCol * Tile.WIDTH;
		}
		
		//for(int i = 0; i < checkpoints.size(); i++) {
		if(checkpoints.size() > 0) {
			Tile ct = checkpoints.get(0);//.get(i);
			if(pBounds.intersects(ct.bounds)) { //snap to checkpoint
				if(!app.game.onCheckpoint) {
					player.x = ct.bounds.x;
					player.y = ct.bounds.y;
					player.velX = 0;
					player.velY = 0;
				}
				checkpoints.remove(0);
				app.game.checkpointHit();
			}
		}
	}
	
	public void draw(Graphics g) {
		for(int row = 0; row < map.length; row++) {
			for(int col = 0; col < map[0].length; col++) {
				Image i = null;
				if(map[row][col].type == Tile.BRICK) {
					i = brick;
				}
				if(map[row][col].type == Tile.CHECKPOINT) {
					i = cpImage;
				}
				g.drawImage(i, col * Tile.WIDTH, row * Tile.HEIGHT, Tile.WIDTH, Tile.HEIGHT, null);
			}
		}		
	}
}