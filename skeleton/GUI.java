
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage; 

/**
 * A grafikus felulet megjeleniteseert felelos.
 */
public class GUI extends Application implements Initializable  {

	/**
	 * A munkasok allapotai.
	 */
	private List<StatusLabel> workerstatus;
	
	/**
	 * A munkasok.
	 */
	private List<Entity> workers;
	
	/**
	 * Elso munkas.
	 */
	private Worker w1;
	/**
	 * Masodik munkas.
	 */
	private Worker w2;
	/**
	 * Harmadik munkas.
	 */
	private Worker w3;
	
	/**
	 * Kirajzolhato objektumok.
	 */
	private List<Drawable> drawables;
	
	/**
	 * Aktualis raktar.
	 */
	private int cwh;
	

	/**
	 * A jatekpanel
	 */
	@FXML private Pane gamePane;
	/**
	 * A statuszpanel
	 */
	@FXML private Pane statusPane;
	 
	//FXML fajlban benne van hogy ez akkor hivodik meg ha lenyomnak egy billentyut
	//AD-HOC megoldas eventhandlerrel kene
	/**
	 * A mozgast kezeli.
	 * @param key keyevent
	 * @throws IOException
	 */
	@FXML
	public void Move(KeyEvent key) throws IOException {
		//System.out.println(w1);
		if(w1.getAlive()) {
		 
		switch(key.getCode()) {
		//Worker 3
			case UP: w1.Move(null, Direction.LEFT, w1); break;
		 	case DOWN: w1.Move(null, Direction.RIGHT, (Worker)w1);break;
		 	case LEFT: w1.Move(null, Direction.UP, (Worker)w1);break;
		 	case RIGHT: w1.Move(null, Direction.DOWN, (Worker)w1);break;
		 	case N: w1.setTileMod(Modifier.OIL);break;
		 	case M: w1.setTileMod(Modifier.HONEY);break;
		default:
			break;
		 	
		 }
		
	 }
	if(w2 != null)
	{
		if(w2.getAlive()) {
			 
			 switch(key.getCode()) {
			 //Worker 2
			 	case W: w2.Move(null, Direction.LEFT, w2); break;
			 	case S: w2.Move(null, Direction.RIGHT, (Worker)w2);break;
			 	case A: w2.Move(null, Direction.UP, (Worker)w2);break;
			 	case D: w2.Move(null, Direction.DOWN, (Worker)w2);break;
			 	case E: w2.setTileMod(Modifier.OIL);break;
			 	case R: w2.setTileMod(Modifier.HONEY);break;
			default:
				break;
			 	
			 }
			
		 }
	}
	
	if(w3 != null)
	{
		if(w3.getAlive()) {
			 
			 switch(key.getCode()) {
			 //Worker 3
			 	case U: w3.Move(null, Direction.LEFT, w3); break;
			 	case J: w3.Move(null, Direction.RIGHT, (Worker)w3);break;
			 	case H: w3.Move(null, Direction.UP, (Worker)w3);break;
			 	case K: w3.Move(null, Direction.DOWN, (Worker)w3);break;
			 	case I: w3.setTileMod(Modifier.OIL);break;
			 	case O: w3.setTileMod(Modifier.HONEY);break;
			default:
				break;
			 	
			 }
			
		 }
	}	
		onUpdate();
		updateStatus();
	 }
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * A jatek inicializalasa.
	 */
	public void initGame() {
		
		//Eloszor lekerjuk a munkas listat
		workers = Game.getCurrentWH().getWorkers();
		//Utana a rajzolhato dolgoknak csinalunk egy listet
		drawables = new ArrayList<Drawable>();
		WareHouse wh = Game.getCurrentWH();
		
		
		//Vegigmegyunk a wh-n es minden tile-hoz letrehozzuk a megfelelo GTile-t
		for(int y = 1; y < Game.getCurrentWH().GetDimension().getX()-1; y++) {
			for(int x = 1; x < Game.getCurrentWH().GetDimension().getY()-1; x++) {
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
		//Boxok hozzadasa
		for(Entity b: Game.getCurrentWH().getBoxes()) {
			drawables.add(new GBox(b));
		}
		//Workerek hozza adasa a rajzolhato abjektumomk listajahoz
		for(Entity w: Game.getCurrentWH().getWorkers()) {
			drawables.add(new GWorker(w));
		}
		
		//A gamePane-nek adjuk az osszeset es igy mar ki lesz rajzolva
		gamePane.getChildren().addAll(drawables);
		
	}
	
	
	/**
	 * Az ujrarajzolast valositja meg.
	 * @throws IOException
	 */
	public void onUpdate() throws IOException {
		
		gamePane.getChildren().clear();
		if(Game.getCurrentWH().GetNumOfWorkers()==0
				|| !Game.getCurrentWH().hasMovableBox()) {
			gamePane.getChildren().add(new Label("Game Over"));
		}
		else {
		List<Drawable> removeable = new ArrayList<Drawable>();
	
		
		for(Drawable dr: drawables) {
			if(!dr.getAlive()) removeable.add(dr);
			else dr.draw();
			
		}
		
		for(Drawable rm: removeable) {
			drawables.remove(rm);
		}
		
		gamePane.getChildren().addAll(drawables);
		}
		
		
	}
	
	/**
	 * Uj jatek letrehozasat valositja meg. 
	 * @throws IOException
	 */
	public void newGame() throws IOException{
		
		Game.ClearGame();
		cwh = DialogBox.display("New Game");
		Game.NewGame(cwh);
		
		DialogBox.display("Worker Number");
		
		initGame();
		
		initStatus();
		
		initWorkerEvent();
	}
	
	/**
	 * Meghivja a dialogusablakot a jatekszaballyal.
	 * @throws IOException
	 */
	public void gameRule() throws IOException
	{
		DialogBox.display("Game Rule");
	}
	
	/**
	 * Munkasok inicializalasa.
	 */
	private void initWorkerEvent() {
		int l = workers.size();
		if(l >= 1) w1 = (Worker) workers.get(0);
		if(l >= 2) w2 = (Worker) workers.get(1);
		if(l >= 3) w3 = (Worker) workers.get(2);
		
	}


	//------------------- Eredmeny jelzes-------------------------
	
	/**
	 * Statuszpanel inicializalasa.
	 */
	private void initStatus() {
		
		statusPane.getChildren().clear();
		int numWork = Game.getCurrentWH().GetNumOfWorkers();
		workerstatus = new ArrayList<StatusLabel>();

		
		for(int i = 0; i < numWork; i++) {
			Worker act = (Worker) Game.getCurrentWH().getWorkers().get(i);
			workerstatus.add(new StatusLabel(act));
		}
		statusPane.getChildren().addAll(workerstatus);
	}
	
	/**
	 * Statusz frissitese.
	 */
	private void updateStatus() {
		for(StatusLabel st: workerstatus) {
			st.update();
		}
	}

	
	// StatusLabel class az eredmenyekhez
	/**
	 * Statuszpanel megvalositasa, szarmaztatas a Label osztalybol.
	 */
	public class StatusLabel extends Label{
		/**
		 * Munkas.
		 */
		private Worker worker;
		/**
		 * Az osztaly konstruktora.
		 * @param w munkas
		 */
		public StatusLabel(Worker w){
			super(w.getName() + ": " + w.getScore());
			worker = w;
			
			this.setStyle("-fx-font-weight: bold;"
					+ "-fx-font-size: 30;");
		}
		/**
		 * Allapot frissitese.
		 */
		public void update() {
			super.setText(worker.getName() + ": " + worker.getScore());
			if(!worker.getAlive())
			{
				this.setStyle("-fx-text-fill: red;"
						+ "-fx-font-weight: bold;"
						+ "-fx-font-size: 30;");
			}
		}
	}
	
	//----------------------------------------------------------------------
	
	/**
	 * Kilepes, bezarja a programot.
	 */
	public void quit() {
		System.exit(0);
	}
	
   
} 