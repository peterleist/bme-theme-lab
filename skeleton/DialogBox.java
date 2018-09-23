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
			rules.setText("Egy rakt�r�p�letben l�d�kat t�rolnak. A rakt�r�p�let padl�ja n�gyzetekre van osztva, ezeken �llnak a n�gyzetekkel megegyez� alapter�let� l�d�k. A l�d�k eltolhat�k, de egy mozgat�ssal mindig csak egy szomsz�dos n�gyzetre ker�lhetnek. \r\n" + 
					"\r\n" + 
					"\r\n" + 
					"A rakt�rban t�bben dolgoznak, a j�t�kosok �ket ir�ny�tj�k. A c�l a l�d�k el��rt helyre tologat�sa. A rakt�rnak falai �s oszlopai is vannak, ezeken a l�d�k nem tolhat�k �t. A l�d�k egym�st el tudj�k tolni. Ha egy munk�sra l�d�t tolunk, akkor a munk�s automatikusan szomsz�dos n�gyzetre tol�dik. Ha nem tud eltol�dni (mert pl. fal van mellette), a munk�s meghal. A l�d�k nem nyomhat�k �ssze.\r\n" + 
					"\r\n" + 
					"A munk�sok b�r t�bb l�d�t is eltolhatnak egyszerre, minden munk�s r� jellemz� er�vel tol. Ha a l�d�k egy�ttes tapad�si s�rl�d�si ereje enn�l nagyobb, akkor a tol�s nem siker�l.\r\n" + 
					"\r\n" + 
					"A padl�ra k�l�nb�z� ken�anyagokat tehetnek a munk�sok: olajat, amit�l cs�sz�sabb lesz (cs�kken a tapad�sa) �s m�zet, amit�l ragacsos (n� a tapad�sa). \r\n" + 
					"\r\n" + 
					"A padl�n egyes helyeken lyukak tal�lhat�k, amelyekre l�d�t tolva a l�da leesik (elt�nik). Ha munk�s l�p r�, meghal. N�melyik lyuk csak akkor viselkedik lyukk�nt, ha egy kapcsol�n l�da �ll, egy�bk�nt padl�nak t�nik. A kapcsol�n l�d�nak kell �llnia, hogy kinyissa a lyukat, ha munk�s �ll a kapcsol�ra, akkor nem kapcsol.\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"A j�t�k v�get �r, ha az �sszes l�da a hely�n van, vagy ha m�r nem lehet t�bbet tolni. Az nyer, aki ekkorra a legt�bb l�d�t a hely�re tolta.\r\n" + 
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
