import com.sun.prism.paint.Color;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

@SuppressWarnings("restriction")
public class GTrapDoor extends Drawable {
	private Tile tile;
	private Image img;
	private int z_index;
	
	public GTrapDoor(Tile tile){
		
		super((tile.pos().getX()-1)*100,(tile.pos().getY()-1)*100,100,100);
		this.tile = tile;
		z_index = 2;
		img = new Image("file:trapdoor.png");
		setFill(new ImagePattern(img));
		resize(100,100);
		//System.out.println(tile.pos().getX());
		
		//setStroke(Color.BLACK);
	}

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
	
	
	public void change() {
		
	}

}
