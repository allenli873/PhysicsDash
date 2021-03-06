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
	protected int checkNum;
	private Tile[][] map; //2d array of tiles makes up the map
	private Graphics g;
	private Game game;
	private Image brick, cpImage, fImage;
	private Player player;
	private PhysicsDash app;
	public static int width, height;
	protected int levelHeight;
	public static int initPosX, initPosY;
	public static int checkpointsCompleted;
	public static boolean stepOn = true;
	public LevelMap(Player player, int level, Game game, PhysicsDash p) 
	{
		checkpointsCompleted = 0;
		checkNum = 0;
		app = p;
		app.checkpoints = new ArrayList<Tile>();
		this.game = game;
		this.player = player;
		loadLevel("levels/map" + level + ".lvl");
		loadImages();
	}
	
	public void loadImages() 
	{
		try 
		{
			brick = ImageIO.read(new File("basic128.png"));
			cpImage = ImageIO.read(new File("flag.png"));
			fImage = ImageIO.read(new File("finish.png"));
		}
		catch(IOException e) 
		{ 
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
		width = in.nextInt(); //read in width and height of level
		height = in.nextInt();
		in.nextLine();
		levelHeight = (height * 120) + 240;
		map = new Tile[height][width];
		for(int i = 0; i < height; i++) 
		{
			Arrays.fill(map[i], new Tile());
		}
		int row = -1;
		app.numChecks = 0;
		while(in.hasNextLine()){ 
			row++;
			//row is ycoord
			String line = "";
			if(in.hasNextLine()) {
				line = in.nextLine();
				line = line.length() >= width ? line.substring(0, width) : line;
			}
			else
				break;
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
					initPosX = col * Tile.WIDTH;
					initPosY = row * Tile.HEIGHT;
				}
				else if(cTile == '|') 
				{
					t.type = Tile.CHECKPOINT;
					t.bounds.width = 60 * 4;
					t.bounds.height = 60 * 4;
				}
				else if(cTile == '1') 
				{
					game.xEnemy1.add(col * Tile.WIDTH);
					game.yEnemy1.add(row * Tile.HEIGHT + 35);
				}
				else if(cTile == 'F') 
				{
					t.type = Tile.FINISH;
					app.finishX = col * Tile.WIDTH;
				}
				map[row][col] = t;
				//assign image type based on char
			}
		}
		
		for(int col = 0; col < width; col++) 
		{
			for(int r = 0; r < height; r++) 
			{
				if(map[r][col].type == Tile.CHECKPOINT) 
				{
					//aight so this is the part where our pandas eat oreos
					if(app.level == 1 && app.numChecks == 0) 
					{
						map[r][col].gapWidth = 2.5f;
						map[r][col].angle = 30f;
					}
					
					if(app.level == 1 && app.numChecks == 1) 
					{
						map[r][col].gapWidth = 3.5f;
						map[r][col].angle = 45f;
					}
					
					if(app.level == 1 && app.numChecks == 2) 
					{
						map[r][col].gapWidth = 6.0f;
						map[r][col].angle = 55f;
					}
					if(app.level == 1 && app.numChecks == 3) 
					{
						map[r][col].gapWidth = 3.0f;
						map[r][col].angle = 65f;
					}
					if(app.level == 2 && app.numChecks == 0) 
					{
						map[r][col].gapWidth = 2.5f;
						map[r][col].angle = 75f;
						map[r][col].gapHeight = 3f;
						
					}
					if(app.level == 2 && app.numChecks == 1) 
					{
						map[r][col].gapWidth = 2.5f;
						map[r][col].angle = 85f;
						map[r][col].gapHeight = 1.5f;
						
					}
					if(app.level == 2 && app.numChecks == 2) 
					{
						map[r][col].gapWidth = 8f;
						map[r][col].angle = 70f;
						map[r][col].gapHeight = 3.5f;
					}
					if(app.level == 2 && app.numChecks == 3) 
					{
						map[r][col].gapWidth = 7.5f;
						map[r][col].angle = 75f;
						map[r][col].gapHeight = 3.5f;
					}
					if(app.level == 3 && app.numChecks == 0) 
					{
						map[r][col].gapWidth = 3.5f;
						map[r][col].angle = 30f;
						map[r][col].gapHeight = -2.5f;
					}
					if(app.level == 3 && app.numChecks == 1) 
					{
						map[r][col].gapWidth = 3f;
						map[r][col].angle = 75f;
						map[r][col].gapHeight = -2.5f;
					}
					if(app.level == 3 && app.numChecks == 2) 
					{
						map[r][col].gapWidth = 4f;
						map[r][col].angle = 60f;
						map[r][col].gapHeight = -6.5f;
					}
					if(app.level == 3 && app.numChecks == 3) 
					{
						map[r][col].gapWidth = 45.5f;
						map[r][col].angle = 5f;
						map[r][col].gapHeight = -3.5f;
					}
					app.numChecks++;
					app.checkpoints.add(map[r][col]);
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
		if(!inBounds(row, col)) return false;
		Tile t = map[row][col];
		boolean int1 = t.type == Tile.BRICK && t.bounds.intersects(player.x, player.y, player.w, player.h);
		if(int1) 
		{
			return true;
		}
		if(!inBounds(row + dy, col + dx)) return false;
		t = map[row + dy][col + dx];
		g.setColor(Color.RED);
		boolean int2 = t.type == Tile.BRICK && t.bounds.intersects(player.x, player.y, player.w, player.h);
		return int2;
	}
	//COLLISION DETECTION:
	//1. get player coords
	//2. convert to map coords
	//3. compare intersection to tiles around player
	//4. set velX or velY to 0 based on intersection
	public void step(Graphics g) 
	{ //simulate collisions 
		if(stepOn) 
		{
			this.g = g;
			Rectangle pBounds = new Rectangle((int) Player.x, (int) Player.y, player.w, player.h);
			int playerRow = (int) (Player.y / Tile.HEIGHT);
			int playerRow2 = (int) Math.ceil(Player.y / Tile.HEIGHT);
			int playerCol = (int) (Player.x / Tile.WIDTH);
			int playerCol2 = (int) Math.ceil(Player.x / Tile.WIDTH);
			
			if(intersects(playerRow + 1, playerCol, 1, 0) && player.velY > 0) 
			{ //down
				player.jumped = false;
				InfoPanel.flying = false;
				player.velY = 0;
				Player.y = playerRow * Tile.HEIGHT;
			}
			if(intersects(playerRow2 - 1, playerCol, 1, 0) && player.velY < 0) 
			{ //up
				player.velY = 0;
				Player.y = playerRow2 * Tile.HEIGHT;
			}
			if(intersects(playerRow, playerCol2 - 1, 0, 1) && player.velX < 0) 
			{ //left
				player.velX = 0;
				Player.x = playerCol2 * Tile.WIDTH;
			}
			if(intersects(playerRow, playerCol + 1, 0, 1) && player.velX > 0) 
			{ //right
				player.velX = 0;
				Player.x = playerCol * Tile.WIDTH;
			}
			
			//for(int i = 0; i < checkpoints.size(); i++) {
			if(checkNum < app.checkpoints.size()) 
			{
				Tile ct = app.checkpoints.get(checkNum);//.get(i);
				if(pBounds.intersects(ct.bounds)) 
				{ //snap to checkpoint
					if(!app.game.onCheckpoint) 
					{
						Player.x = ct.bounds.x;
						Player.y = ct.bounds.y;
						player.velX = 0;
						player.velY = 0;
					}
					checkNum++;
					app.game.checkpointHit();
				}
				
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
				if(map[row][col].type == Tile.FINISH) {
					i = fImage;
				}
				g.drawImage(i, col * Tile.WIDTH, row * Tile.HEIGHT, Tile.WIDTH, Tile.HEIGHT, null);
			}
		}		
	}
}