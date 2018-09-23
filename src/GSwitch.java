import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class GSwitch extends Drawable {
	private Tile tile;
	private Image img;
	private int z_index;
	
	public GSwitch(Tile tile){
		
		super((tile.pos().getX()-1)*100,(tile.pos().getY()-1)*100,100,100);
		this.tile = tile;
		z_index = 2;
		img = new Image("file:switch.png");
		setFill(new ImagePattern(img));
		resize(100,100);
		//System.out.println(tile.pos().getX());
		
		//setStroke(Color.BLACK);
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
}
