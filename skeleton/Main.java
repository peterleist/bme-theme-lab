import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override 
	   public void start(Stage primaryStage) throws FileNotFoundException, IOException, InterruptedException { 
		 new Game("game.txt"); 
		   Parent root = FXMLLoader.load(getClass().getResource("Frame.fxml"));
		   primaryStage.setTitle("Killer Sokoba");
		   
		   Scene rootScene = new Scene(root);
		   rootScene.getRoot().requestFocus();
		   
	       primaryStage.setScene(rootScene);
	       primaryStage.show();
	   }      
	   public static void main(String args[]) throws FileNotFoundException, IOException{ 	 
		  
		   launch(args);
	   } 
}
