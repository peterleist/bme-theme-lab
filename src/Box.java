import java.io.IOException;

/**
 * Ladat reprezentalo osztaly.
 */
public class Box extends Entity {
	/**
	 * A ladahoz tartozo celmezo.
	 */
	private Target target;
	/**
	 * A tolast megkezdo munkas.
	 */
	private Worker worker;
	
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
			//System.out.println("Sikeres mozgas");
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
			//System.out.println("Sikertelen mozgas");
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

	// Debug fv
	@Override
	public void Hi() {
		System.out.print("B");
	}

	public String Hello() {
		return "Box";
	}
	
	// Doboz eltünésekor
	@Override
	public void reduceNum() throws IOException {



	}
	// Tile beállítása
	/**
	 * Beallitja azt a mezot, amelyiken a lada van.
	 * @param t beallitando mezo
	 */
	public void SetTile(Tile t) {
		tile = t;	
	}

	// Oszlopnak ütközik
	/**
	 * Oszlopnak utkozik.
	 * @return hamis
	 */
	@Override
	public boolean ToPillar() {
		return false;
	}

	@Override
	public Target getTarget(){
		return target;
	}
	
	public void setTarget(Target t){
		target = t;
	}

	@Override
	public void Die() {
		//System.out.println("Eltuntem...");
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}





}
