package battleship;

/**
 * This is a subclass extending from Ship class. 
 * @author chaut
 *
 */

public class Submarine extends Ship {

	// instance variables
	// static final variables for ship's length and type
	
	static final int shipLength = 1;

	static final String shipType = "submarine";

	// constructor
	// used to set the length variable to the correct value

	public Submarine () {
		super(shipLength);

	}

	// Override method getShipType(), returns "submarine" 
	@Override
	public String getShipType() {
		return Submarine.shipType;
	}

}