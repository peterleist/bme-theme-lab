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

	/*
	public boolean WorkerToPillar(Worker w) throws IOException {
		Szkeleton.kiir(">", "Pillar", "Accept()");
		System.out.println("Ezt a playert fel kell kanalazni");
		Szkeleton.kiir("<", "Pillar", "Accept(): true");
		return true;
	}*/

	// Nem tartózkodhaz rajta enitity
	public Entity GetEntityAt() {
		return null;
	}

	/**
	 * Debug fuggveny
	 */
	public void Hi() {
		System.out.print("X");
	}
}
