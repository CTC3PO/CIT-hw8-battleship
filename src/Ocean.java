
package battleship;

import java.util.Random;

public class Ocean {

	// Instance variables

	// Used to quickly determine which ship is in any given location
	private Ship[][] ships = new Ship[10][10];

	// add boolean fired array to keep track of which coordinate has been fired upon
	boolean[][] fired = new boolean[10][10];

	// The total number of shots fired by the user
	private int shotsFired;

	// The number of times a shot hit a ship. If the user shoots the same part of a
	// ship
	// more than once, every hit is counted, even though additional “hits” don’t do
	// the
	// user any good.
	private int hitCount;

	// The number of ships sunk (10 ships in all)
	private int shipsSunk;

	// private final variables for the number of each ship type
	private static final int BATTLESHIP_QUANTITY = 1;
	private static final int CRUISER_QUANTITY = 2;
	private static final int DESTROYER_QUANTITY = 3;
	private static final int SUBMARINE_QUANTITY = 4;

	// Constructor

	/**
	 * Creates an ”empty” ocean (and fills the ships array with EmptySea objects).
	 * uses a private helper method to do this. also initializes any game variables
	 * (shots have been fired, etc.)
	 */
	public Ocean() {

		shotsFired = 0;
		hitCount = 0;
		shipsSunk = 0;

		// call helper method to fill ocean with empty sea
		this.fillOceanWithEmptySea();

	}

	// Methods

	// Helper method (to create an array of 10 x 10 emptySea objects

	/**
	 * This method is a helper method to fill the ships array in the ocean class
	 * with EmptySea objects
	 */

	private void fillOceanWithEmptySea() {

		// looping through the 2d array of the ocean and fill it with emptySea objects,
		// set the fired boolean at that locaiton as false (hasn't been fired at)
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				ships[i][j] = new EmptySea();
				fired[i][j] = false;
			}
		}
	}

	/**
	 * this method place all ten ships randomly on the (initially empty) ocean it
	 * achieves it by checking if it's okay to place ship at the random location, if
	 * not, it will keep looking for a location, then place it. It will place 1
	 * battleship, 2 cruisers, 3 destroyers and 4 submarines in the ocean.
	 */

	void placeAllShipsRandomly() {

		// create a random object to generate random numbers
		Random random = new Random();

		// initialize local variables row, column, horizontal
		int row;
		int column;
		boolean horizontal;

		// looping through each type of ships for the number of ships in that type,
		// generate random number for row, column, horizontal and try to place the
		// ships,
		// if not okay to place the ship at that location, keep generate random row,
		// column
		// and horizontal until it is good. finally, place the ship at that location

		// looping through to place 1 Battleship

		for (int i = 0; i < Ocean.BATTLESHIP_QUANTITY; i++) {

			// create new ship in this type
			Ship battleship = new Battleship();

			// generate random row, column and horizontal value
			row = random.nextInt(10);
			column = random.nextInt(10);
			horizontal = random.nextInt(2) == 0 ? false : true;

			// while loop to keep generate new value to place ship it's not okay to place it
			// in that location

			while (!battleship.okToPlaceShipAt(row, column, horizontal, this)) {

				row = random.nextInt(10);
				column = random.nextInt(10);
				horizontal = random.nextInt(2) == 0 ? false : true;
			}

			// then found a location, place it at that location
			battleship.placeShipAt(row, column, horizontal, this);
		}

		// looping through to place 2 Cruisers

		for (int i = 0; i < Ocean.CRUISER_QUANTITY; i++) {

			// create new ship in this type
			Ship cruiser = new Cruiser();

			// generate random row, column and horizontal value
			row = random.nextInt(10);
			column = random.nextInt(10);
			horizontal = random.nextInt(2) == 0 ? false : true;

			// while loop to keep generate new value to place ship it's not okay to place it
			// in that location

			while (!cruiser.okToPlaceShipAt(row, column, horizontal, this)) {

				row = random.nextInt(10);
				column = random.nextInt(10);
				horizontal = random.nextInt(2) == 0 ? false : true;
			}

			// then found a location, place it at that location
			cruiser.placeShipAt(row, column, horizontal, this);
		}

		// looping through to place 3 Destroyers

		for (int i = 0; i < Ocean.DESTROYER_QUANTITY; i++) {

			// create new ship in this type
			Ship destroyer = new Destroyer();

			// generate random row, column and horizontal value
			row = random.nextInt(10);
			column = random.nextInt(10);
			horizontal = random.nextInt(2) == 0 ? false : true;

			// while loop to keep generate new value to place ship it's not okay to place it
			// in that location

			while (!destroyer.okToPlaceShipAt(row, column, horizontal, this)) {

				row = random.nextInt(10);
				column = random.nextInt(10);
				horizontal = random.nextInt(2) == 0 ? false : true;
			}

			// then found a location, place it at that location
			destroyer.placeShipAt(row, column, horizontal, this);
		}

		// looping through to place 4 Submarines

		for (int i = 0; i < Ocean.SUBMARINE_QUANTITY; i++) {

			// create new ship in this type
			Ship submarine = new Submarine();

			// generate random row, column and horizontal value
			row = random.nextInt(10);
			column = random.nextInt(10);
			horizontal = random.nextInt(2) == 0 ? false : true;

			// while loop to keep generate new value to place ship it's not okay to place it
			// in that location

			while (!submarine.okToPlaceShipAt(row, column, horizontal, this)) {

				row = random.nextInt(10);
				column = random.nextInt(10);
				horizontal = random.nextInt(2) == 0 ? false : true;
			}

			// then found a location, place it at that location
			submarine.placeShipAt(row, column, horizontal, this);
		}

	}

	/**
	 * Returns true if the given location contains a ship, false if it does not
	 * 
	 * @param row
	 * @param column
	 * @return
	 */

	boolean isOccupied(int row, int column) {

		// check if the coordinate is out of bound (row, column), if yes, return false
		if (row < 0 || row >= 10 || column < 0 || column >= 10) {
			return false;
		}

		// if the coordinate is an instance of the EmptySea class, also return false
		if (ships[row][column] instanceof EmptySea) {
			return false;
		}

		// otherwise, return true
		return true;
	}

	/**
	 * Returns true if the given location contains a ”real” ship, still afloat, (not
	 * an EmptySea), false if it does not. In addition, this method updates the
	 * number of shots that have been fired, and the number of hits. Note: If a
	 * location contains a “real” ship, shootAt should return true every time the
	 * user shoots at that same location. Once a ship has been ”sunk”, additional
	 * shots at its location should return false.
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	boolean shootAt(int row, int column) {

		// if shot at the coordinate, set the fired boolean at that location as true
		// (already fired at, this will help marked as "-")
		fired[row][column] = true;

		// increase the shots fired at that location

		Ship[][] ships = this.getShipArray();

		Ship ship = ships[row][column];

		boolean flag = false;

		if (!ship.isSunk()) {

			if (ship.shootAt(row, column)) {
				hitCount++;
				System.out.print(this.getHitCount() + " ");

				if (ship.isSunk()) {
					shipsSunk++;
				}

			}
			
			if (this.isOccupied(row, column)) {
				flag = true;
			}

		}

		shotsFired++;
		return flag;
	}

	/**
	 * Returns the number of shots fired (in the game)
	 * 
	 * @return
	 */
	int getShotsFired() {

		return shotsFired;

	}

	/**
	 * Returns the number of hits recorded (in the game). All hits are counted, not
	 * just the first time a given square is hit.
	 * 
	 * @return
	 */
	int getHitCount() {

		return hitCount;

	}

	/**
	 * Returns the number of ships sunk (in the game)
	 * 
	 * @return
	 */
	int getShipsSunk() {

		return shipsSunk;

	}

	/**
	 * Returns true if all ships have been sunk, otherwise false
	 * 
	 * @return
	 */
	boolean isGameOver() {

		// set the count = 0
		int coordinateCount = 0;

		// looping through the ships array, if the ship is sunk there, increase the
		// count
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (ships[i][j].isSunk()) {
					coordinateCount++;
				}
			}
		}

		// if the total count = 20, return true ("game is over")
		if (coordinateCount == 20) {
			return true;

			// otherwise, return false, game is not over
		} else
			return false;
	}

	/**
	 * Returns the 10x10 array of Ships. The methods in the Ship class that take an
	 * Ocean parameter need to be able to look at the contents of this array; the
	 * placeShipAt() method even needs to modify it.
	 * 
	 * @return
	 */
	Ship[][] getShipArray() {
		return ships;

	}

	/**
	 * Prints the Ocean. ‘x’ to indicate a location that you have fired upon and hit
	 * a (real) ship. ‘-’ to indicate a location that you have fired upon and found
	 * nothing there.‘s’ to indicate a location containing a sunken ship. ‘.’ (a
	 * period) to indicate a location that you have never fired upon. This methods
	 * is only called from the BattleshipGame class.
	 */

	void print() {

		// print the number of the board for easily referenced by user
		System.out.println("    0 1 2 3 4 5 6 7 8 9");

		// looping through and print out the character of the coordinate in the ocean
		for (int i = 0; i < 10; i++) {
			System.out.print(i + " |");

			// if a location hasn't been fired at, print as "."
			for (int j = 0; j < 10; j++) {
				if (!fired[i][j]) {
					System.out.print(" .");

					// if a location's already been fired at, call the toString method to print out
					// the character for that location
				} else {
					System.out.print(" " + ships[i][j].toString());
				}
			}
			System.out.println(" |");
		}
	}

	/**
	 * Used for debugging only this method prints the Ocean. This method can be used
	 * during development and debugging, to see where ships are actually being
	 * placed in the Ocean. Use ‘ b’ to indicate a Battleship. Use ‘c’ to indicate a
	 * Cruiser. Use ‘d ’ to indicate a Destroyer. Use ‘ s ’ to indicate a Submarine.
	 * Use ‘ ’ (single space) to indicate an EmptySea.
	 */

	// still needs to make the 9x9 grid for user's reference
	void printWithShips() {

		// print the number of the board for easily referenced by user
		System.out.println("    0 1 2 3 4 5 6 7 8 9");

		// looping through and print out the ship type of the coordinates in the ocean
		for (int i = 0; i < 10; i++) {

			System.out.print(i + " |");

			for (int j = 0; j < 10; j++) {

//				Ship ship = ships[i][j];

				// if the coordinate is an instance of the Battleship class, print "b"
				if (ships[i][j] instanceof Battleship) {
					System.out.print(" b");
				}

				// if the coordinate is an instance of the Cruiser class, print "c"
				if (ships[i][j] instanceof Cruiser) {
					System.out.print(" c");
				}

				// if the coordinate is an instance of the Destroyer class, print "d"
				if (ships[i][j] instanceof Destroyer) {
					System.out.print(" d");
				}

				// if the coordinate is an instance of the Submarine class, print "s"
				if (ships[i][j] instanceof Submarine) {
					System.out.print(" s");
				}

				// if the coordinate is an instance of the EmptySea class, print " "
				if (ships[i][j] instanceof EmptySea) {
					System.out.print("  ");
				}
			}

			System.out.println(" |");
		}
	}

}
