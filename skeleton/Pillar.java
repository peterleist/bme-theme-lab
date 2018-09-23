import java.io.IOException;


/**
 * Az oszlopokat reprezentalo osztaly
 */
public class Pillar extends Tile {

	/**
	 * Default konstruktor
	 * @throws IOException
	 */
	public Pillar() throws IOException {

	}
	
	@Override
	public boolean Accept(Direction d) {
		return false;
	}

	/**
	 * Az ososztaly Accept fuggvenyet definialja felul oly modon, hogy sosem fogad el ra erkezo entitast.
	 * @param e	az entity
	 * @return
	 * @throws IOException
	 */
	public boolean Accept(Entity e, Direction d, Worker w) throws IOException {
		if(e != w && e.ToPillar()) {
			e.Die();
			return true;
		}
		return false;
	}

	public Entity GetEntityAt() {
		return null;
	}

	/**
	 * Debug fuggveny
	 */
	public void Hi() {
		System.out.print("X");
	}
	
	@Override
	public String Hello() {
		return "P";
	}
}
