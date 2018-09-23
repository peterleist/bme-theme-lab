import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Teszter {
	private class Eset {
		protected String bFile, kFile, nev;
		protected ArrayList<String> bemenet, kimenet;
		//konstruktor, ami beolvassa a fajlokat soronkent ket tombbe
		public Eset(String _bFile, String _kFile, String _nev) throws IOException {
			bFile = _bFile;
			kFile = _kFile;
			nev = _nev;
			
			//bemenet beolvasasa
			try (BufferedReader br = new BufferedReader(new FileReader(bFile))) {
			    String line;
			    
			    //parancsok beolvasasa fajlbol
			    bemenet = new ArrayList<String>();
			    while ((line = br.readLine()) != null) {
			    	bemenet.add(line);
			    }
			}
			
			//kimenet beolvasasa
			try (BufferedReader br = new BufferedReader(new FileReader(kFile))) {
			    String line;
			    
			    //kimenet beolvasasa fajlbol
			    kimenet = new ArrayList<String>();
			    while ((line = br.readLine()) != null) {
			    	kimenet.add(line);
			    }
			}
		}
	}
	
	private ArrayList<Eset> esetek = new ArrayList<Eset>();
	
	
	
	//filelista beolvasasa a teszt/in illetve a teszt/out mappakbol
	public Teszter() throws IOException {
		String dir = System.getProperty("user.dir")+ "/tesztek/";
		File inFolder = new File(dir + "in/");
		File[] inFiles = inFolder.listFiles();
		
		for (File f: inFiles) {
			String fn = f.getName();
			int pos = fn.lastIndexOf(".");
			fn = fn.substring(0, pos);
		
			esetek.add(new Eset(dir + "in/" + fn + ".in", dir + "out/" + fn + ".out", fn));
		}
	}
	
	public boolean Osszehasonlit(ArrayList<String> _eredeti_kimenet, ArrayList<String> _generalt_kimenet) {
		//ha a sorok szama nem egyezik, biztosan hiba
		if (_eredeti_kimenet.size() != _generalt_kimenet.size()) {
			System.out.println("A generalt kimenet sorainak a szama nem egyezik meg az elvarteval.");
			System.out.println("---- EREDETI -----");
			for (int i = 0; i < _eredeti_kimenet.size(); i++)
				System.out.println(_eredeti_kimenet.get(i));
			System.out.println("---- GENERALT -----");
			for (int i = 0; i < _generalt_kimenet.size(); i++)
				System.out.println(_generalt_kimenet.get(i));
			//return false;
		}
		//annak ellenorzese, hogy minden sort tudunk-e parositani
		for (int i = 0; i < _eredeti_kimenet.size(); i++) {
			String keresett = _eredeti_kimenet.get(0);
			boolean talalt = false;
			for (int j = 0; j < _generalt_kimenet.size(); j++) {
				if (_generalt_kimenet.get(j).contains(keresett))
					talalt = true;
			}
			//if (!talalt) {
				//System.out.println("Az elvart kimenet ezen sorat nem talaltam a generaltban:");
				//System.out.println(keresett);
				
				//return false;
			//}
				
		}
		System.out.println("---- EREDETI -----");
		for (int g = 0; g < _eredeti_kimenet.size(); g++)
			System.out.println(_eredeti_kimenet.get(g));
		System.out.println("---- GENERALT -----");
		for (int g = 0; g < _generalt_kimenet.size(); g++)
			System.out.println(_generalt_kimenet.get(g));
		//ha fentebb nem volt hiba, akkor siker!
		return true;
	}
	
	public void Teszteles(int _melyik_teszt) throws Exception {
		Eset teszt = esetek.get(_melyik_teszt);
		Game game = new Game("game.txt");
		ArrayList<Worker> workers = new ArrayList<>();
		ArrayList<String> generaltKimenet = new ArrayList<>();
		while (!teszt.bemenet.isEmpty() ) {
			Worker w = null;
			Vec2D d = null;
			int szamol = 0;
			String[] cmd = teszt.bemenet.get(0).split(" ");
			teszt.bemenet.remove(0);
	
			switch (cmd[0]) {
			case "loadWH":
				game.NewGame(Integer.parseInt(cmd[1])-1);
				break;
			case "placeWorker":
				Worker temp_worker = new Worker("W"+cmd[1], 1); 
				workers.add(temp_worker);
				
				w = temp_worker;
				if (w.tile != null)
					w.tile.Remove();
				String[] XY = cmd[2].split(",");

				
				Game.getCurrentWH().AddWorker(w, Integer.parseInt(XY[0]), Integer.parseInt(cmd[3]));
				
				break;
			case "moveWorker":
				Direction direction = null;
				w = workers.get(Integer.parseInt(cmd[1])-1);
				
				System.out.println(w.name);
				
				if (w == null)
					throw new Exception("Nem letezik a mozgatni kivant worker.");
				switch(cmd[2]) {
					case"up": direction =  Direction.UP; break;
					case"down": direction =  Direction.DOWN; break;
					case"left": direction =  Direction.LEFT; break;
					case"right": direction =  Direction.RIGHT; break;
				}
				
				if(w.Move(null, direction, w)) {
					String s = "Sikeres mozgas, sebesseg: 1" ;
					generaltKimenet.add(s);
				}
				else {
					String s = "Sikertelen mozgas";
					generaltKimenet.add(s);
				}
				
				break;
			case "listEntities":
				d = Game.getCurrentWH().GetDimension();
				szamol = 1;
				for (int x = 0; x < d.getX(); x++) {
					for (int y = 0; y < d.getY(); y++) {
						Tile ct = Game.getCurrentWH().GetTileAt(new Vec2D(x,y));
						if (ct.GetEntityAt() != null) {
							String s = szamol + ". " + x + " " + y + " " + ct.GetEntityAt().Hello();
							generaltKimenet.add(s);
							szamol++;
						}
					}
				}
				break;
			case "setWorkerPower":
				w = workers.get(Integer.parseInt(cmd[1]));
				if (w == null)
					throw new Exception("Nem letezik az allitani kivant worker.");
				w.setPower(Integer.parseInt(cmd[2]));
				break;
			case "listTSs":
				d = Game.getCurrentWH().GetDimension();
				szamol = 1;
				for (int x = 0; x < d.getX(); x++) {
					for (int y = 0; y < d.getY(); y++) {
						Tile ct = Game.getCurrentWH().GetTileAt(new Vec2D(x,y));
						if (ct.Hello() == "Switch") {
							String s = szamol + ". " + "TrapDoor " + ct.getTD().position.getX() + ", " + ct.getTD().position.getY() + " ";
							if (ct.getTD().getState() == true)
								s += "nyitott";
							else
								s += "zart";
							s += " Switch: " + ct.position.getX() + ", " + ct.position.getY();
							generaltKimenet.add(s);
							szamol++;
						}
					}
				}
				break;
			default:
				throw new Exception("Hibas parancs: " + cmd[0]);
			}
		}
		
		System.out.println("Teszt futtatasa: "+teszt.nev);
		
		if (Osszehasonlit(teszt.kimenet, generaltKimenet))
			System.out.println("OK");
		else
			System.out.println("fail");
	}
	
	public void OsszesTesztFuttatasa() throws Exception {
		for (int i=0; i<esetek.size(); i++)
			Teszteles(i);
	}
	
	public static void main(String[] args) throws Exception {
		Teszter teszter = new Teszter();
		teszter.Teszteles(1);
	}
	// TODO menu!
}
