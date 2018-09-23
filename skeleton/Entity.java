import java.io.IOException;

/**
 * Entitast reprezentalo absztrakt osztaly.
 */
public abstract class Entity {
	/**
	 * A mezo, amelyen eppen all.
	 */
	protected Tile tile;
	
	protected double got_resistance = 0;
	
	protected boolean isAlive = true;
	
	public void PushTileResistance(double res) {
		if (res > got_resistance)
			got_resistance = res;
	}
	
	
	public int getScore() {
		return 0;
	}
	
	
	/**
	 * Beallitja a mezot, amelyikre lepett.
	 * @param t mezo
	 * @throws IOException
	 */
	public abstract void SetTile(Tile t) throws IOException;
	
	/**
	 * Adott iranyba lep egyet, ha sikerult lepni, akkor igazzal, ha nem sikerult lepni, akkor hamissal ter vissza.
	 * @param e entitas
	 * @param d irany
	 * @return sikerult-e elmozogni
	 * @throws IOException
	 */
	public abstract boolean Move (Entity e, Direction d, Worker w) throws IOException;
	
	/**
	 * Visszaadja, hogy el tudtak-e tolni.
	 * @param e entitas, mely tolja
	 * @return el tudtak-e tolni
	 * @throws IOException
	 */
	public abstract boolean MovedBy(Entity e) throws IOException;
	
	/**
	 * A lada hatassal van a kapcsolora, igazzal ter vissza.
	 * @return hatassal van-e a kapcsolora
	 * @throws IOException
	 */
	public abstract boolean SwitchAction() throws IOException;
	
	/**
	 * Csokkenti az entitasok szamat.
	 * @throws IOException
	 */
	public void reduceNum() throws IOException{
		
	}
	
	//debug
	
	
	public abstract String Hello();
	
	public abstract Target getTarget();
	
	public abstract Tile getTile();  //itt is meg lehet valositani kesobb

	public abstract boolean ToPillar() throws IOException;
	
	public void Die() {
	isAlive = false;	
	}
	
	public boolean getAlive() {
		return isAlive;
	}
	


	public boolean MovableCheck() throws IOException {
		// TODO Auto-generated method stub
		return true;
	}


	public boolean getCanMove() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void setTileMod(Modifier m) {
		
	}
	
}
