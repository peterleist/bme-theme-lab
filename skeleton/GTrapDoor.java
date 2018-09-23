import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * A csapoajto grafikus megjeleniteset valositja meg.
 */
public class GTrapDoor extends Drawable {
	/**
	 * A mezo, ahol van.
	 */
	private Tile tile;
	/**
	 * A csapoajtot reprezentalo kep.
	 */
	private Image img;
	
	/**
	 * Az osztaly konstruktora.
	 * @param tile a mezo, ahol van
	 */
	public GTrapDoor(Tile tile){
		
		super((tile.pos().getX()-1)*100,(tile.pos().getY()-1)*100,100,100);
		this.tile = tile;
		img = new Image("file:trapdoor.png");
		setFill(new ImagePattern(img));
		resize(100,100);
	}
	
	/**
	 * A csapoajto kirajzolasa az allapotatol fuggoen
	 */
	@Override
	public void draw() {
		if(tile.getState()) {
			img = new Image("file:hole.jpg");
			setFill(new ImagePattern(img));
		}
		else {
			img = new Image("file:trapdoor.png");
			setFill(new ImagePattern(img));
		}
		
	}

}
