import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Tester {
	
	public ArrayList<String> test_in = new ArrayList<String>();
	public ArrayList<String> test_out = new ArrayList<String>();
	public ArrayList<String> test_result = new ArrayList<String>();
	public ArrayList<Worker> test_workers = new ArrayList<Worker>();
	
	public void test_in(String file) throws FileNotFoundException, IOException {
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    boolean flag = false;
		    //Elso sor beolvasasa, ami egy szam és megmutatja hany raktar lesz
		    while ((line = br.readLine()) != null) {		 
		    		if(line.equals("+")) {
		    			flag = true;
		    		}
		    		else if(!flag) {
		    			
		    			test_in.add(line);
		    		}
		    		else if(flag) {
		    			test_out.add(line);
		    		}
		    		
		    }
		}
	
	}
	
	public void test_run() throws FileNotFoundException, IOException {
		Game game = new Game("game.txt");
		
		String[] temp;
		
		//game.ShowWHs();
		
		for(int i = 0; i < test_in.size(); i++) {
			
			temp = test_in.get(i).split(" ");
			
			switch(temp[0]) {
				case"loadWH":
					game.NewGame(Integer.parseInt(temp[1])-1);
					//System.out.println("Sikeres load");
					break;
				case"placeWorker":
					Worker w = null;
					w = new Worker("W"+temp[1],1);
					Game.getCurrentWH().AddWorker(w, Integer.parseInt(temp[2]), Integer.parseInt(temp[3]));
					test_workers.add(w);
					break;
				case"moveWorker":
					Direction d = null;
					switch(temp[2]) {
						case"up": d = Direction.UP; break;
						case"down": d = Direction.DOWN; break;
						case"left": d = Direction.LEFT; break;
						case"right": d = Direction.RIGHT; break;
					}
					
					
					Worker w1 = test_workers.get(0);
					int pre_score = w1.getScore();
					if(w1.Move(null, d, w1)) {
						test_result.add("Sikeres mozgas, sebesseg: "+ w1.getSpeed());
					}
					else {
						test_result.add("Sikertelen mozgas");
						
					}
					
					if(pre_score != w1.getScore()) 
						test_result.add("Box a Target mezojere ert. Odamozgatta: "+w1.name+" - Pontszama: "+ w1.getScore());
					
					break;
				case"listEntities":
					WareHouse wh = Game.getCurrentWH();
					
					for(int x = 0; x < wh.GetDimension().getX(); x++)
						for(int y = 0; y < wh.GetDimension().getY(); y++) {
							Tile t = wh.GetTileAt(new Vec2D(x,y));
							if(t.GetEntityAt() != null) {
								String s = x + " " + y + " " + t.GetEntityAt().Hello();
								test_result.add(s);
								
							}
						}
					break;
				
				case "listTSs":
					WareHouse tmp = Game.getCurrentWH();
					
					for (int x = 0; x < tmp.GetDimension().getX(); x++) {
						for (int y = 0; y < tmp.GetDimension().getY(); y++) {
							Tile ct = tmp.GetTileAt(new Vec2D(x,y));
							//System.out.println(ct.Hello());
							if (ct.Hello().equals("Switch")) {
								TrapDoor td = ct.getTD();
								String s = "TrapDoor " + td.position.getX() + ", " + td.position.getY() + " ";
								if (td.GetState() == true)
									s += "nyitott";
								else
									s += "zart";
								s += " Switch: " + ct.position.getX() + ", " + ct.position.getY();
								test_result.add(s);
								
							}
						}
					}
					break;
				case"setWorkerPower":
					test_workers.get(0).setPower(Double.parseDouble(temp[2]));
				
			}
			
			
			
			
			
		}
		System.out.println("-------- ELVART KIMENET --------");
		for(int i= 0; i < test_out.size(); i++) {
			System.out.println(test_out.get(i));
		}
		System.out.println("-------- GENERALT KIMENET --------");
		for(int i= 0; i < test_result.size(); i++) {
			System.out.println(test_result.get(i));
		}
		
		//game.ShowWHs();
	}
	
	public boolean test_validate() {

		boolean contains = false;
		if(test_out.size() != test_result.size()) return false;
		for(int i = 0; i< test_out.size(); i++) {
			for(int j = 0; j < test_result.size(); j++) {
				if(test_out.get(i).equals(test_result.get(j))) contains = true;		
			}
			
			if(!contains) return false;
			else contains = false;
			
		}
		return true;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
		
        System.out.println("Testesetek:");
        System.out.println("0 - osszes teszteset futtatasa");
        System.out.println("1 - worker mozgas teszt");
        System.out.println("2 - doboz eltolas teszt");
        System.out.println("3 - sor eltolasa teszt");
        System.out.println("4 - sebsseg valtoztatas teszt");
        System.out.println("5 - eroproba teszt");
        System.out.println("6 - lyuk teszt");
        System.out.println("7 - switch teszt");
        System.out.println("8 - target teszt");
        System.out.println("9 - death teszt");
        System.out.println("exit");
        
        String s = br.readLine();
        if(s.equals("exit")) break;
        int t = Integer.parseInt(s);
        
        if(t == 0) {
        		for(int i = 1; i < 10; i++) {
        			System.out.println(i + ". test");
        			s = i + ".test";
        			Tester test = new Tester();
        			test.test_in(s);
        			test.test_run();
        			if(test.test_validate()) System.out.println("TEST OK");
        			else System.out.println("TEST NO");
        		}
        }
        else {
        		System.out.println(t + ". test");
        		s += ".test";
			Tester test = new Tester();
			test.test_in(s);
			test.test_run();
			if(test.test_validate()) System.out.println("_--- TEST OK ---_");
			else System.out.println("TEST NO");
        }
		
		
		
		}	

	}

}
