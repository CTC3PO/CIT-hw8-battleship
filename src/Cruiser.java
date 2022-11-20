package battleship;

/**
 * This is a subclass extending from Ship class. 
 * @author chaut
 *
 */

public class Cruiser extends Ship {

	// instance variables
	// static final variables for ship's length and type
	
	static final int shipLength = 3;

	static final String shipType = "cruiser";

	// constructor
	// used to set the length variable to the correct value

	public Cruiser() {
		super(shipLength);

	}

	// Override method getShipType(), returns "cruiser" 
	@Override
	public String getShipType() {
		return Cruiser.shipType;
	}

}