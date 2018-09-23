import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage; 
import javafx.scene.shape.Rectangle;

public class GUI extends Application implements Initializable  {

	
	private List<Drawable> drawables;
	private Group gameTable;
	 @FXML private Pane gamePane;
	 
	 private int count = 0 ;

	 @FXML
	 private Label label ;
	 
	 
	 @FXML
	 public void increment() {
		 count++;
	     label.setText("Count: "+count);
	 }

	 
	 @FXML
	 public void Move(KeyEvent key) {
		 //gamePane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
		 
	 }
	 
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initGame();
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	public void initGame() {
		
		gameTable = new Group();
		
		drawables = new ArrayList<Drawable>();
		WareHouse wh = Game.getCurrentWH();
		if(wh == null) System.out.println("A");
		for(int y = 1; y < 7; y++) {
			for(int x = 1; x < 7; x++) {
				Tile tile = wh.GetTileAt(new Vec2D(y,x));
				
				switch(tile.Hello()) {
				case"T": drawables.add(new GTile(tile));
				    break;
				case"H": drawables.add(new GHole(tile));	
					break;
				case"P": drawables.add(new GPillar(tile));	
				  	break;
				case"S": drawables.add(new GSwitch(tile));	
			  		break;
				case"D": drawables.add(new GTarget(tile));	
		  			break;
				case"C": drawables.add(new GTrapDoor(tile));	
	  				break;
				default: 
					System.out.print(tile.Hello());
					break;
				}
				
				
			}
		}
		
		for(Entity b: Game.getCurrentWH().getBoxes()) {
			drawables.add(new GBox(b));
		}
		
		for(Entity w: Game.getCurrentWH().getWorkers()) {
			drawables.add(new GWorker(w));
		}
		System.out.println(drawables);
		
		
		gamePane.getChildren().addAll(drawables);
		
	}
	
	public void onUpdate() {
		for(Drawable dr: drawables) {
			dr.draw();
			//if(!dr.getAlive()) removeable.add(dr);
		}
	}
	
   
} 