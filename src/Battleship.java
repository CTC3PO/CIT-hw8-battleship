package battleship;

/**
 * This is a subclass extending from Ship class. 
 * @author chaut
 *
 */

public class Battleship extends Ship {

	// instance variables
	// static final variables for ship's length and type

	static final int shipLength = 4;

	static final String shipType = "battleship";

	// constructor
	// used to set the length variable to the correct value

	public Battleship() {
		super(shipLength);

	}

	// Override method getShipType(), returns "battleship" 
	@Override
	public String getShipType() {
		return Battleship.shipType;
	}

}
