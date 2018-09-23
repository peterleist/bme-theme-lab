import java.io.IOException;

/**
 * A csapóajtókat állító mező
 */
public class Switch extends Tile {
	
	// kapcsolohoz tartozo csapoajto
	protected TrapDoor trapdoor;

	// Default konstruktor
	public Switch(TrapDoor recent) throws IOException {
		trapdoor = recent;
	}

	// A csapóajtó beállitása
	public void SetTrapDoor(TrapDoor td) {
		trapdoor = td;
	}
	
	// entitas tipsatol fuggoen megvaltoztatja a csapoajto allapotat
	public boolean Accept(Entity e, Direction d, Worker w) throws IOException {
		if(entity == null || entity.Move(e, d, w)) {
			entity = e;
			e.SetTile(this);
			//System.out.println("Sikeres Accept");
			
			if(entity.SwitchAction() && !trapdoor.GetState()) {
				trapdoor.SetState(true);
				
			}
			else if (entity.SwitchAction() && trapdoor.GetState()) {
				trapdoor.SetState(false);
			}
			else trapdoor.SetState(false);
			
			System.out.println(trapdoor.GetState());
			return true;
		}
		else return false;
	}
	


	// Debug fuggveny
	public void Hi() {
		System.out.print("S");
	}
	
	public String Hello() {
		return "Switch";
	}
	@Override
	public TrapDoor getTD() {
		return trapdoor;
	}
}
