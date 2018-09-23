import java.io.IOException;


/**
 * Ladat reprezentalo osztaly.
 */
public class Box extends Entity {


	boolean canMove = true;
	/**
	 * A ladahoz tartozo celmezo.
	 */
	private Target target;
	/**
	 * Az aktualis mezo.
	 */
	private Tile tile;
	


	// Doboz mozog
	/**
	 * Adott iranyba lep egyet, ha sikerult lepni, akkor igazzal, ha nem sikerult lepni, akkor hamissal ter vissza.
	 * @param e entitas
	 * @param d irany
	 * @return sikerult-e elmozogni
	 */
	@Override
	public boolean Move(Entity e, Direction d, Worker w) throws IOException {
		Tile temp = tile;
		
		if(w.getPower() != 0 && temp.GetNbTile(d).Accept(this, d, w)){

			double res;

			switch (tile.getRes()) {
				case REGULAR:
					res = 1;
					break;
				case OIL:
					res = 0.5;
					break;
				case HONEY:
					res = 2;
					break;
					default:
						res = 1;
			}
			w.setPower(w.getPower() - res);

			temp.Remove(/*this*/);
			return true;
		}
		else {

			return false;
		} 
	}
	
	

	// Dobozt elmozgatjak
	/**
	 * Visszaadja, hogy el tudtak-e tolni.
	 * @param e entitas, mely tolja
	 * @return el tudtak-e tolni
	 */
	@Override
	public boolean MovedBy(Entity e) throws IOException {

		return true;
	}
	
	// A lada hat a kapcsolora
	/**
	 * A lada hatassal van a kapcsolora, igazzal ter vissza.
	 * @return hatassal van-e a kapcsolora
	 */
	@Override
	public boolean SwitchAction() throws IOException {

		return true;
	}
	
	/**
	 * Grafikus feluletnek adja vissza a tipust
	 */
	public String Hello() {
		return "B";
	}
		
	/**
	 * Beallitja azt a mezot, amelyiken a lada van.
	 * @param t beallitando mezo
	 */
	public void SetTile(Tile t) {
		tile = t;	
	}
	/**
	 * Oszlopnak utkozeskor van e teendo a boxnak(meghalhat-e).
	 * @return hamis
	 */
	public boolean ToPillar() {
		return false;
	}

	/**
	 * Visszadja a box targetjet.
	 */
	@Override
	public Target getTarget(){
		return target;
	}
	/**
	 * Beallitja a box targetjet.
	 * @param t Target
	 */
	public void setTarget(Target t){
		target = t;
	}

	/**
	 * Doboz eltunik(meghal).
	 */
	public void Die() {
		try {
			super.Die();
			Game.getCurrentWH().reduceNumOfMovableBoxes();
			Game.getCurrentWH().RemoveBox(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Visszadja a Tile-t amin a box van.
	 */
	public Tile getTile() {
		return tile;
	}

	/**
	 * Azt vizsgalja, hogy a box mozgathato-e meg.
	 */
	public boolean MovableCheck() throws IOException {
		//Ha mar tudjuk, hogy nem tud mozogni, nem kell csinálni semmit.

		// lekerdezzuk a szomszedos tileokat
		Tile tUP = tile.GetNbTile(Direction.UP);
		Tile tDOWN = tile.GetNbTile(Direction.DOWN);
		Tile tLEFT = tile.GetNbTile(Direction.LEFT);
		Tile tRIGHT = tile.GetNbTile(Direction.RIGHT);

		boolean up,down,left,right;
		up = !tUP.Accept(Direction.UP);
		down = !tDOWN.Accept(Direction.DOWN);
		left = !tLEFT.Accept(Direction.LEFT);
		right = !tRIGHT.Accept(Direction.RIGHT);
		
		
		//sarokban vagyunk?
		if ((up && right) || (up && left) || (down && right) || (down && left)) {
			canMove = false;
			Game.getCurrentWH().reduceNumOfMovableBoxes();
			return false;
		}
		
		return true;
	}

	/**
	 * Visszadja, hogy a box mozgathato-e meg.
	 */
	public boolean getCanMove() {
		return canMove;
	}






}
