//
//loads map, handles collisions
public class LevelMap {

	private Tile[][] map; //2d array of tiles makes up the map
	
	private Image brick;

	public LevelMap(int level) {
		loadLevel("levels/map" + level + ".lvl");
	}
	
	public void loadImages() {
		try {
			brick = ImageIO.read("images/basic128.png");
		}
		catch(IOException e) { }
	}
	
	public void loadLevel(String name) {
		Scanner in = new Scanner(new File(name));
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
				if(cTile == '#') t.type = Tile.BRICK;
				map[row][col] = t;
				//assign image type based on char
			}
		}
	}
	 
	//COLLISION DETECTION:
	//1. get player coords
	//2. convert to map coords
	//3. compare intersection to tiles around player
	//4. set velX or velY to 0 based on intersection
	public void step(Character player) { //simulate 
		Rectangle pBounds = new Rectangle((int) player.x, (int) player.y, player.w, player.h);
		for(int row = 0; row < map.length; row++) {
			for(int col = 0; col < map[0].length; col++) {
				//check intersection
				int playerRow = player.bounds.y / Tile.HEIGHT;
				int playerCol = player.bounds.x / Tile.WIDTH;
				if(pBounds.intersects(map[playerRow - 1][playerCol].bounds) || pBounds.intersects(map[playerRow + 1][playerCol].bounds)) {
					player.velY = 0;
				}
				if(pBounds.intersects(map[playerRow][playerCol - 1].bounds) || pBounds.intersects(map[playerRow][playerCol + 1].bounds) {
					player.velX = 0;
				}
			}
		}
	}
	
	public void draw(Graphics g) {
		for(int row = 0; row < map.length; row++) {
			for(int col = 0; col < map[0].length; col++) {
				if(map[row][col].type == Tile.BRICK) {
					g.drawImage(brick, col * Tile.WIDTH, row * Tile.HEIGHT, Tile.WIDTH, Tile.HEIGHT, null);
				}
			}
		}		
	}
}
