package battleship;

/**
 * This is a subclass extending from Ship class. It helps simplify game play by
 * putting a non-null value in empty locations in the ship array in Ocean class,
 * denoting the absence of a ship, therefore helps save all null checking.
 * 
 * @author chaut
 *
 */

public class EmptySea extends Ship {

	static final int shipLength = 1;

	static final String shipType = "emptySea";
//	static final boolean[] hit = new boolean[1];

	// constructor
	/**
	 * this zero-argument constructor sets the length variable to 1 by calling the
	 * constructor in the super class
	 */
	public EmptySea() {
		super(shipLength);
	}

	// Methods

	/**
	 * This method overrides shootAt(int row, int column) that is inherited from
	 * Ship, and always returns false to indicate that nothing was hit
	 */

	@Override
	boolean shootAt(int row, int column) {
		return false;

	}

	/**
	 * This method overrides isSunk() that is inherited from Ship, and always
	 * returns false to indicate that you didn’t sink anything
	 */

	@Override
	boolean isSunk() {
		return false;

	}

	/**
	 * Returns the single-character “-“ String to use in the Ocean’s print method.
	 * (Note, this is the character to be displayed if a shot has been fired, but
	 * nothing has been hit.)
	 */

	@Override
	public String toString() {
		return "-";

	}

	/**
	 * This method just returns the string “empty”
	 */

	@Override
	public String getShipType() {
		return "empty";

	}

}
