import java.io.IOException;
import java.util.ArrayList;

/**
 * A dobozok céljait reprezentáló osztály, ha olyan doboz érkezik rá, ami hozzá van rendelve,
 * akkor elnyeli azt
 */
public class Target extends Tile {

	// A célhez rendelt dobozok tárolója
	private ArrayList<Entity> boxes = new ArrayList<Entity>();

	private WareHouse wh;
	
	// Default konstruktor
	public Target() throws IOException {

	}

	// Doboz hozzárendelése a célhoz
	public void AddBox(Box b) {
		boxes.add(b);
	}

	// A célra érkező entity-ket kezeli
	public boolean Accept(Entity e, Direction d, Worker w) throws IOException {
		if(entity == null) {
			if(e.getTarget() == this) {
				e.SetTile(this);
				//System.out.println("Sikeres Accept");
				//System.out.println("Sikeres Target");
				entity = null;
				e.Die();
				w.AddPlacedBox();
				return true;
			}
			else return super.Accept(e, d, w);
		}
		
		else {
			if(entity.Move(e, d, w)) {
				e.SetTile(this);
				//System.out.println("Sikeres Accept");
				//System.out.println("Sikeres Target");
				entity = null;
				e.Die();
				w.AddPlacedBox();
				return true;
			}
			else return false;
		}
			
	}

	// Debug fv
	public void Hi() {
		if(entity == null) System.out.print("D");
		else entity.Hi();
	}
}
