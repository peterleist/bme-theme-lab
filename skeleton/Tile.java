

import java.io.IOException;

/**
 * A palya alapelemeit reprezentalo osztaly
 */
public class Tile {
	protected Vec2D position;
	protected Entity entity = null;
	
	protected Tile tUP;
	protected Tile tDOWN;
	protected Tile tLEFT;
	protected Tile tRIGHT;
	
	protected Modifier res;
	/**
	 * Default konstruktor
	 * @throws IOException
	 */
	public Tile() throws IOException {
		res = Modifier.REGULAR;
	}

	/**
	 *
	 * @param trap
	 */
	public Tile(TrapDoor trap) {
	}
	
	public void setRes(Modifier resistance) {
		res = resistance;
	}
	public Modifier getRes() {
		return res;
	}
	
	public Tile GetNbTile(Direction d) {
		switch(d){
		case UP: return tUP; 
		case DOWN: return tDOWN; 
		case LEFT: return tLEFT; 
		case RIGHT: return tRIGHT;
		default: return null;
		}
	}


	/**
	 * A Tile-on levo Entity beallitása
	 * @param e az entitiy
	 * @throws IOException
	 */
	public void SetEntity(Entity e) throws IOException {
		entity = e;
		e.SetTile(this);
	}
	
	//Szomszedos Tile keri ezt az Acceptet

	/**
	 * Kezeli a ra erkezo entityket
	 * @param up 
	 * @param e	az entity
	 * @return	ide mozoghat-e az entity
	 * @throws IOException
	 */
	public boolean Accept(Direction d) throws IOException {
		//Ha a tile on egy korabbi entity volt, lek�rdezzuk hogy tud-e mozogni
		if(entity != null) return entity.getCanMove();
		//Ha nem volt entity akkor oda lehet mozogni
		return true;
	}
	
	//Worker vagy Box keri ezt az Acceptet
	public boolean Accept(Entity e, Direction d, Worker w) throws IOException { // TODO Ide nem kene mas fuggvenyhivas? (bence)
		if(entity == null || entity.Move(e, d, w)) {
			entity = e;
			
			e.SetTile(this);

			double pushres;
			switch (res) {
			case REGULAR:
				pushres = 1;
				break;
			case OIL:
				pushres = 0.5;
				break;
			case HONEY:
				pushres = 2;
				break;
				default:
					pushres = 1;
		}
			w.PushTileResistance(pushres);
			//System.out.println("Sikeres Accept");
			//Remove();
			//entity.MovableCheck();
			return true;
		}
		else return false;
	}
		

	/**
	 * Kezeli az adott iránybol érkező ra erkezo entityket
	 * @param e	az entity
	 * @param d	az irany
	 * @throws IOException
	 */
		

	/**
	 * Entity eltavolítasa a mezorol
	 * @throws IOException
	 */
	public void Remove() throws IOException {
		entity = null;
	}


	/**
	 * Szomszedok beallitasa
	 * @param up	felso szomszed
	 * @param down	also szomszed
	 * @param left	bal szomszed
	 * @param right	jobb szomszed
	 */
	public void setNeighbor(Tile up, Tile down, Tile left, Tile right) {
		tUP = up;
		tDOWN = down;
		tLEFT = left;
		tRIGHT = right;
	}
	

	/**
	 * Visszaadja a rajtal levo enitityt
	 * @return	az enitity
	 * @throws IOException
	 */
	public Entity GetEntityAt() throws IOException {

		return entity;
	}
	
	//Ez a fuggveny, amikor a worker kerdezi az alatta levo mezot, hogy mondja meg a szomszedon van-e Entity

	/**
	 * Megkerdezi egy szomszedjatol az entityjet
	 * @param d	melyik szomszed
	 * @return	a rajta lévő entity
	 * @throws IOException
	 */
	public Entity GetEntityAt(Direction d) throws IOException {
		switch(d) {
		case UP: return tUP.GetEntityAt(); 
		case DOWN: return tDOWN.GetEntityAt(); 
		case LEFT: return tLEFT.GetEntityAt(); 
		case RIGHT: return tRIGHT.GetEntityAt();
		default:
			break;
		}
		return null;
		
	}


	public Vec2D pos() {
		return position;
	}

	public String Hello() {
		return "T";	
	}

	public TrapDoor getTD() {
		return null;
	}

	public boolean getState() {
		return false;
	}
	
}
