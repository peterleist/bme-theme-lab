import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * A lyuk grafikus megjeleniteset valositja meg.
 */
public class GHole extends Drawable {
	/**
	 * A lyukat reprezentalo kep.
	 */
	private Image img;

	/**
	 * Az osztaly konstruktora.
	 * @param tile a mezo, ahol van
	 */
	public GHole(Tile tile){
		
		super((tile.pos().getX()-1)*100,(tile.pos().getY()-1)*100,100,100);
		img = new Image("file:hole.jpg");
		setFill(new ImagePattern(img));
		resize(100,100);

	}
	

}
