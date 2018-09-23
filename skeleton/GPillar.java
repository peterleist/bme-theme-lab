import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * A fal grafikus megjeleniteset valositja meg.
 */
public class GPillar extends  Drawable {
	/**
	 * A falat reprezentalo kep.
	 */
	private Image img;
	
	/**
	 * Az osztaly konstruktora.
	 * @param tile a mezo, ahol van
	 */
	public GPillar(Tile tile){
		
		super((tile.pos().getX()-1)*100,(tile.pos().getY()-1)*100,100,100);
		img = new Image("file:pillar.jpg");
		setFill(new ImagePattern(img));
		resize(100,100);
	}

}
