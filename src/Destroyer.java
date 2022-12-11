package battleship;

/**
 * This is a subclass extending from Ship class. 
 * @author chaut
 *
 */

public class Destroyer extends Ship {

	// instance variables
	// static final variables for ship's length and type
	
	static final int shipLength = 2;

	static final String shipType = "destroyer";

	// constructor
	// used to set the length variable to the correct value

	public Destroyer() {
		super(shipLength);

	}

	// Override method getShipType(), returns "destroyer"
	@Override
	public String getShipType() {
		return Destroyer.shipType;
	}

}