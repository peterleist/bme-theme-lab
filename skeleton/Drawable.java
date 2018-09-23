import java.io.IOException;
import javafx.scene.shape.Rectangle;

public abstract class Drawable extends Rectangle {
	public Drawable(int i, int j, int k, int l) {
		super(i,j,k,l);
	}
	
	/**
	 * Az entitas frissiteseert felelos.
	 * @throws IOException
	 */
	public void draw() throws IOException {
		
	}
	
	/**
	 * Visszaadja, hogy az entitas el-e.
	 * @return az entitas el-e
	 */
	public boolean getAlive() {
		return true;
	}
}
