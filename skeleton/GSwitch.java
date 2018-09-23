import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * A kapcsolo grafikus megjeleniteset valositja meg.
 */
public class GSwitch extends Drawable {
	/**
	 * A kapcsolot reprezentalo kep.
	 */
	private Image img;
	
	/**
	 * Az osztaly konstruktora.
	 * @param tile a mezo, ahol van
	 */
	public GSwitch(Tile tile){
		super((tile.pos().getX()-1)*100,(tile.pos().getY()-1)*100,100,100);

		img = new Image("file:switch.png");
		setFill(new ImagePattern(img));
		resize(100,100);
	
	}
	
}
