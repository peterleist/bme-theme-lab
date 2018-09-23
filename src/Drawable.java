import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public abstract class Drawable extends Rectangle {
	public Drawable(int i, int j, int k, int l) {
		super(i,j,k,l);
	}

	public abstract void draw();
	
	public boolean getisDead() {
		return false;
	}
}
