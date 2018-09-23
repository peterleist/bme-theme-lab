import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * A munkas grafikus megjeleniteset valositja meg.
 */
public class GWorker extends Drawable {
	/**
	 * Az entitas, melyet megjelenit.
	 */
	private Entity ent;
	/**
	 * Az aktualis mezo, amelyen all.
	 */
	private Tile tile;
	/**
	 * A munkast reprezentalo kep.
	 */
	private Image img;
	
	/**
	 * Az osztaly konstruktora.
	 * @param e entitas
	 */
	public GWorker(Entity e){
		super((e.getTile().pos().getX()-1)*100,(e.getTile().pos().getY()-1)*100,100,100);
		ent = e;
		tile = e.getTile();
		img = new Image("file:worker.png");
		setFill(new ImagePattern(img));
		resize(100,100);
		//System.out.println(tile.pos().getX());
		//setStroke(Color.BLACK);
	}
	
	/**
	 * Az munkas poziciojanak frissiteseert felelos.
	 */
	@Override
	public void draw() {
		tile = ent.getTile();
		this.relocate((tile.pos().getX()-1)*100,(tile.pos().getY()-1)*100);
		
	}
	
	/**
	 * Visszaadja, hogy az entitas el-e.
	 * @return az entitas el-e
	 */
	@Override
	public boolean getAlive() {
		return ent.getAlive();
	}

}
