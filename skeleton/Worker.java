import java.io.IOException;


/**
 * Egy munkast reprezental. A jatekos ennek iranyitasaval tolhatja a ladakat a megfelelo iranyba.
 */
public class Worker extends Entity {
	/**
	 * A jatekos neve
	 */
	public String name;
	/**
	 * A munkas altal helyere tett ladak szama.
	 */
	private int numOfPlacedBoxes = 0;
	Tile tile;
	private double power;
	

	/**
	 * Default konstruktor
	 * @param name
	 * @throws IOException
	 */
	public Worker(String name, int pow) throws IOException {
		this.name = name;
		power = pow;
	}

	
	/**
	 * Tile beallitasa
	 * @param t
	 * @throws IOException
	 */
	public void SetTile(Tile t) throws IOException {
		tile = t;
	}
	
	public double getSpeed() {
		return power/got_resistance;
	}
	

	/**
	 * A munkas mozgatasa.
	 * @param e
	 * @param d
	 * @return A munkás el tud-e mozogni.
	 * @throws IOException
	 */
	@Override
	public boolean Move(Entity e, Direction d, Worker w) throws IOException {
		Tile temp = tile;
		double pow_temp = power;
		if(e == w) return false;
		if(temp.GetNbTile(d).Accept(this, d, w)){
			temp.Remove();
			power = pow_temp;
			return true;
		}
		else {

			return false;
		} 
		
				
	}

	/**
	 * A munkas nem hat a kapcsolora.
	 * @return Mindig hamis.
	 * @throws IOException
	 */
	@Override
	public boolean SwitchAction() throws IOException {

		return false;
	}

	/**
	 * A munkas tolhatosaganak ellenorzese.
	 * @param e
	 * @return A munkast el lehet-e tolni.
	 * @throws IOException
	 */
	@Override
	public boolean MovedBy(Entity e) throws IOException {
		if(e == null) return false;
		return true;
		
	}

	/**
	 * A munkas meghal.
	 */
	public void Die() {
		tile = null;
		try {
			super.Die();
			Game.getCurrentWH().reduceNumOfWorkers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Egy lada helyere lokesekor noveli a munkás helyre tett ladainak szamat.
	 */
	public void AddPlacedBox() {
		numOfPlacedBoxes++;
	}

	/**
	 * Debug fuggveny.
	 */
	public void Hi() {
		System.out.print("W");
	}
	
	public String Hello() {
		return "W";
	}
	
	public void setPower(double d) {
		power = d;
	}
	
	public double getPower() {
		return power;
	}

	/**
	 * Csokkenti az aktualisan a Warehouse-ban levo Worker-ek szamat.
	 * @throws IOException
	 */
	@Override
	public void reduceNum() throws IOException {
		Game.getCurrentWH().reduceNumOfWorkers();
		
	}

	/**
	 * Pillarnak lepes.
	 * @return
	 * @throws IOException
	 */
	@Override
	public boolean ToPillar() throws IOException {
		
		return true;
	}

	@Override
	public Target getTarget() {
		return null;
	}
	
	public void setTileMod(Modifier m) {
		tile.setRes(m);
	}



	@Override
	public int getScore() {
		// TODO Auto-generated method stub
	    return numOfPlacedBoxes;
	}
	
	public Tile getTile() {
		return tile;
	}
	
	public String getName() {
		return name;
	}

}
