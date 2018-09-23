import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A palyat reprezentalo osztaly.
 */
public class WareHouse {
	/**
	 * L�d�k sz�ma.
	 */
	private int numOfBoxes = 0;
	/**
	 * Celmezok szama.
	 */
	private int numOfTargets = 0;
	/**
	 * Munkasok szama.
	 */
	private int numOfWorkers = 0;
	/**
	 * Mozgathato ladak szama.
	 */
	private int numOfMovableBoxes = 0;
	/**
	 * Raktar magassaga (mezo darab).
	 */
	private int height;
	/**
	 * Raktar szelessege (mezo darab).
	 */
	private int width;

	/**
	 * Mezok, melyek a raktarat alkotjak.
	 */
	private Tile[][] tiles;

	/**
	 * Az osztaly konstruktora.
	 * @param map Egy '+' jelig beolvasott palya.
	 * @throws IOException
	 */
	public WareHouse(ArrayList<String> map) throws IOException {
		String[] dimension = map.get(0).split(" ");
		width = Integer.parseInt(dimension[0])+2;
		height = Integer.parseInt(dimension[1])+2;
		map.remove(0);
		
		//resistance set-----

		//--------------------
		
		//Generikus tomb beallitasa
		tiles = (Tile[][]) Array.newInstance(Tile.class, Integer.parseInt(dimension[0]) + 2,Integer.parseInt(dimension[1]) + 2);
		
		//Alap feltoltes Pillarokkal es Tileokkal. Pillarok a szelen
		for(int i= 0; i<height; i++) {
			for(int j = 0; j<width; j++) {
				if(i == 0 || i == height-1 || j == 0 || j == width-1) tiles[i][j] = new Pillar();
				else tiles[i][j] = new Tile();
				tiles[i][j].setRes(Modifier.REGULAR);
				tiles[i][j].position = new Vec2D(i,j);
			}
		}
		
		
		
		/*
		 * A trapdoorokat es a switcheket a fajlban egymas utan taroljuk
		 * es a beolvasasnal a trapdor alatti switch tartozik hozza.
		 */
		TrapDoor recenttrap = null;
		Target recenttarget = null;
		//Tile tipusok hozzaadasa
		for(int i = 0; i < map.size(); i++) {
			String[] temp_tile = map.get(i).split(" ");
			int x,y;
			
			y = Integer.parseInt(temp_tile[1]);
			x = Integer.parseInt(temp_tile[2]);
			
			if(temp_tile[0].equals("Hole")) tiles[x][y] = new Hole();
			
			else if(temp_tile[0].equals("Trapdoor")) {
				recenttrap = new TrapDoor();
				tiles[x][y] = recenttrap;
				tiles[x][y].position = new Vec2D(x,y);
			}
			
			else if(temp_tile[0].equals("Switch")) {
				tiles[x][y] = new Switch(recenttrap);
				recenttrap = null;
				tiles[x][y].position = new Vec2D(x,y);
			}
			
			else if(temp_tile[0].equals("Target")) {
				recenttarget = new Target();
				tiles[x][y] = recenttarget;
				numOfTargets++;
				tiles[x][y].position = new Vec2D(x,y);
			}
			else if(temp_tile[0].equals("Pillar")) {
				tiles[x][y] = new Pillar();
				tiles[x][y].position = new Vec2D(x,y);
			}
			
			else if(temp_tile[0].equals("Box")) {
				Box currentBox = new Box();
				tiles[x][y].SetEntity(currentBox);
				currentBox.SetTile(tiles[x][y]);
				currentBox.setTarget(recenttarget);
				recenttarget.AddBox(currentBox);
				numOfBoxes++;
				numOfMovableBoxes++;
				tiles[x][y].position = new Vec2D(x,y);
			}
			else if(temp_tile[0].equals("setTileResistance")) {
				Modifier resist = null;
				switch(temp_tile[3]) {
				case "0.5": resist = Modifier.OIL; 
					break;
				case "1.0": resist = Modifier.REGULAR; 
					break;
				case "1.5": resist = Modifier.HONEY; 
					break;
				}
				
				
				tiles[Integer.parseInt(temp_tile[1])][Integer.parseInt(temp_tile[2])].setRes(resist);
				tiles[x][y].position = new Vec2D(x,y);
			}

		}
		
		
		//Szomszedok beallitasa a perem kivetelevel
		for(int i= 1; i<height-1; i++) {
			for(int j = 1; j<width-1; j++) {
				tiles[i][j].setNeighbor(tiles[i+1][j], tiles[i-1][j], tiles[i][j-1], tiles[i][j+1]);
				tiles[i][j].setRes(Modifier.REGULAR);
			}
		}
		
		
	}
	/**
	 * Kirajzol a konzolra.
	 */
	public void draw() {
		for(int i= height-1; i>=0; i--) {
			for(int j = 0; j<width; j++) {
				tiles[i][j].Hi();
				}
			System.out.println("");
			}
	}

	/**
	 * Hozzaadja a munkast a palyahoz.
	 * @param w munkas
	 * @param x x koordinata
	 * @param y y koordinata
	 * @throws IOException
	 */
	public void AddWorker(Worker w, int x, int y) throws IOException { 
		tiles[x][y].SetEntity(w);
		w.SetTile(tiles[x][y]);
	}

	/**
	 * Visszaadja a megkapott koordinatara eso mezot.
	 * @param v koordinata
	 * @return a koordinatan levo mezo
	 */
	public Tile GetTileAt(Vec2D v) {
		return tiles[v.getX()][v.getY()];
		
	}
	/**
	 * Visszaadja a raktar meretet (N x M).
	 * @return raktar merete
	 */
	public Vec2D GetDimension() {
		return new Vec2D(width, height);
		
	}

	/**
	 * A raktarban levo ladak szama.
	 * @return ladak szama
	 */
	public int GetNumOfBoxes() {
		return numOfBoxes;
	}

	/**
	 * A palyan levo ladak szamat csokkenti.
	 * @throws IOException
	 */
	public void reduceNumOfBoxes() throws IOException {
		numOfBoxes--;
		
	}

	/**
	 * A palyan levo munkasok szamat csokkenti.
	 * @throws IOException
	 */
	public void reduceNumOfWorkers() throws IOException {
		numOfWorkers--;
		
	}

	/**
	 * A palyan levo mozgathato ladak szamat csokkenti.
	 * @throws IOException
	 */
	public void reduceNumOfMovableBoxes() throws IOException {
		numOfMovableBoxes--;
	}
}
