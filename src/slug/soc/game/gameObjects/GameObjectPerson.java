package slug.soc.game.gameObjects;

import java.awt.Color;

import slug.soc.game.gameObjects.tiles.faction.TilePerson;

public class GameObjectPerson extends GameObject {

	private String lastName;
	private Integer troopNumber;
	
	public GameObjectPerson(Color color, Faction owner) {
		super(new TilePerson(color), owner);
		lastName = owner.toString();
		troopNumber = 1;
	}

	@Override
	public String[] getStringDesc() {
		String[] desc = new String[2];
		desc[0] = "Bob " + lastName;
		desc[1] = "Travels with a troop of " + troopNumber;
		return desc;
	}
	
	public String toString(){
		return "Family Member";
	}

}
