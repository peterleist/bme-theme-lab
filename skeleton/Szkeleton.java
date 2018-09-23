import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Segédosztály a fv hívások követéséhez
 */
public class Szkeleton {
	public static int melyseg = 1;
	//Kiir, illetve, ha inputot varunk, akkor azt bekeri a felhasznalotol.
	// ? eseten: Az osztaly stringbe lehet kerdest feltenni.
	// - esetem: Az  osztaly stringbe lehet beadni a kommentet. Ilyenkor a melyseg NEM novekszik.
	public static String kiir(String tipus, String osztaly, String fuggveny) throws IOException {
		if (tipus == "-") {
			System.out.print(tipus);
			for (int i=0; i<melyseg; i++)
				System.out.print("   ");
			System.out.println(osztaly);
			return "";
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if (tipus == ">") melyseg++;
		System.out.print(tipus);
		for (int i=0; i<melyseg; i++)
			System.out.print("   ");
		if (tipus == ">") System.out.print("->");
		if (tipus == "<") System.out.print("<-");
		if (tipus != "?") System.out.println("["+osztaly+"]."+fuggveny);
		if (tipus == "<") melyseg--;
		if (tipus == "?") {
			System.out.print(osztaly);
			return br.readLine();
		}
		else return "";
	}
}
