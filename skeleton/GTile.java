import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

/**
 * A mezo grafikus megjeleniteset valositja meg.
 */
public class GTile extends Drawable{
	/**
	 * A mezot reprezentalo kep.
	 */
	private Image img;
	private Tile tile;
	/**
	 * Az osztaly konstruktora.
	 * @param tile a mezo, ahol van
	 */
	public GTile(Tile tile){
		
		super((tile.pos().getX()-1)*100,(tile.pos().getY()-1)*100,100,100);
		this.tile = tile;
		img = new Image("file:tile.jpg");
		
		System.out.println(tile.getRes());
		if(tile.getRes() == Modifier.REGULAR) setFill(new ImagePattern(img));
		else if(tile.getRes() == Modifier.OIL) setFill(Color.BLACK);
		else if(tile.getRes() == Modifier.HONEY) setFill(Color.YELLOW);

	}
	
	public void draw() {
		if(tile.getRes() == Modifier.OIL) setFill(Color.BLACK);
		if(tile.getRes() == Modifier.HONEY) setFill(Color.YELLOW);
	}
	
	
}
