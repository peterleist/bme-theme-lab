import java.io.IOException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Univerzalis dialogusablakot valositja meg.
 */
public class DialogBox {
	/**
	 * Az aktualis raktar.
	 */
	private static int cwh = 0;
	/**
	 * Az aktualis lista a dialogusablakban.
	 */
	private static ListView<String> list;
	/**
	 * Szovegdoboz, amelyben a szabaly leirasa van.
	 */
	private static TextArea rules;
	
	/**
	 * A dialogusablak megjeleniteset valositja meg.
	 * @param title a dialogusablak cime
	 * @return az aktualis raktar sorszama
	 * @throws IOException
	 */
	public static int display(String title) throws IOException {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		Button button = new Button("Submit");
		Button okbutton = new Button("Ok");
		
		
		if(title.equals("New Game")) {

			list = new ListView<String>();
			
			for(int i= 0; i < Game.ShowWHs(); i++) {
				list.getItems().add("WareHouse - "+i);
			}
			
			list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			
			button.setOnAction(e -> {
					String[] temp = list.getSelectionModel().getSelectedItem().split(" ");
					cwh = Integer.parseInt(temp[2]);
					window.close();
					
			});
			
			VBox layout = new VBox(20);
			layout.setPadding(new Insets(20,20,20,20));
			layout.getChildren().addAll(list,button);
			
			Scene scene = new Scene(layout,300,250);
			window.setScene(scene);
			window.showAndWait();
			
			
		}
		
		
		else if(title.equals("Worker Number")) {
			list = new ListView<String>();
			
			list.getItems().addAll("1 player", "2 player", "3 player");
			list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			
			button.setOnAction(e -> {
				try {
				String[] temp = list.getSelectionModel().getSelectedItem().split(" ");
				int nw = Integer.parseInt(temp[0]);
				
				for(int i = 0; i<nw; i++) {
					Worker w;
					
						w = new Worker(i+1 + ". Player",5);
						Game.getCurrentWH().AddWorker(w, 1, i+1);
					
				}
				window.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			}
		});
		
			VBox layout = new VBox(20);
			layout.setPadding(new Insets(20,20,20,20));
			layout.getChildren().addAll(list,button);
			
			Scene scene = new Scene(layout,300,250);
			window.setScene(scene);
			window.showAndWait();
		 
		}
		
		else if(title.equals("Game Rule"))
		{
			rules = new TextArea();
			rules.setText("Egy raktárépületben ládákat tárolnak. A raktárépület padlója négyzetekre van osztva, ezeken állnak a négyzetekkel megegyezõ alapterületû ládák. A ládák eltolhatók, de egy mozgatással mindig csak egy szomszédos négyzetre kerülhetnek. \r\n" + 
					"\r\n" + 
					"\r\n" + 
					"A raktárban többen dolgoznak, a játékosok õket irányítják. A cél a ládák elõírt helyre tologatása. A raktárnak falai és oszlopai is vannak, ezeken a ládák nem tolhatók át. A ládák egymást el tudják tolni. Ha egy munkásra ládát tolunk, akkor a munkás automatikusan szomszédos négyzetre tolódik. Ha nem tud eltolódni (mert pl. fal van mellette), a munkás meghal. A ládák nem nyomhatók össze.\r\n" + 
					"\r\n" + 
					"A munkások bár több ládát is eltolhatnak egyszerre, minden munkás rá jellemzõ erõvel tol. Ha a ládák együttes tapadási súrlódási ereje ennél nagyobb, akkor a tolás nem sikerül.\r\n" + 
					"\r\n" + 
					"A padlóra különbözõ kenõanyagokat tehetnek a munkások: olajat, amitõl csúszósabb lesz (csökken a tapadása) és mézet, amitõl ragacsos (nõ a tapadása). \r\n" + 
					"\r\n" + 
					"A padlón egyes helyeken lyukak találhatók, amelyekre ládát tolva a láda leesik (eltûnik). Ha munkás lép rá, meghal. Némelyik lyuk csak akkor viselkedik lyukként, ha egy kapcsolón láda áll, egyébként padlónak tûnik. A kapcsolón ládának kell állnia, hogy kinyissa a lyukat, ha munkás áll a kapcsolóra, akkor nem kapcsol.\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"A játék véget ér, ha az összes láda a helyén van, vagy ha már nem lehet többet tolni. Az nyer, aki ekkorra a legtöbb ládát a helyére tolta.\r\n" + 
					"\r\n" + 
					"");
			
			okbutton.setOnAction(e -> {
				window.close();
			});
			
			VBox layout = new VBox(20);
			layout.setPadding(new Insets(20,20,20,20));
			layout.getChildren().addAll(rules,okbutton);
			
			Scene scene = new Scene(layout,600,400);
			window.setScene(scene);
			window.showAndWait();
		}
		
		return cwh;


		
	}


}
