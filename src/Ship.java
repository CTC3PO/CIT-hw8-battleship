//Name: Chau Tran
//Penn ID: 58664366
//Statement of work:
//-Resources: lecture notes and videos from module 9,10,11 on Coursera
//-I worked alone without help

package battleship;

public abstract class Ship {

	// instance variables

	// The row that contains the bow (front part of the ship)
	private int bowRow;

	// The column that contains the bow (front part of the ship)
	private int bowColumn;

	// The length of the ship
	private int length;

	// A boolean that represents whether the ship is going to be placed horizontally
	// or vertically
	private boolean horizontal;

	// An array of booleans that indicate whether that part of the ship has been hit
	// or not
	private boolean[] hit;

	// Constructor

	/**
	 * sets the length property of the particular ship and initializes the hit array
	 * based on that length
	 * 
	 * @param length
	 */
	public Ship(int length) {
		this.length = length;
		this.hit = new boolean[length];

	}

	// Methods
	// Getters and Setters

	/**
	 * this methods returns the ship length
	 * 
	 * @return the length
	 */
	public int getLength() {
		return length;

	}

	/**
	 * this methods returns the ships bowRow
	 * 
	 * @return the bowRow
	 */
	public int getBowRow() {
		return bowRow;
	}

	/**
	 * this method returns the bow column
	 * 
	 * @return the bowColumn
	 */
	public int getBowColumn() {
		return bowColumn;
	}

	/**
	 * this method returns the hit array (with boolean values)
	 * 
	 * @return the hit
	 */
	public boolean[] getHit() {
		return hit;
	}

	/**
	 * this method returns if the position of ship is horizontal or not
	 * 
	 * @return the horizontal
	 */
	public boolean isHorizontal() {
		return horizontal;
	}

	/**
	 * this method sets the bowRow location
	 * 
	 * @param bowRow the bowRow to set
	 */
	public void setBowRow(int row) {
		this.bowRow = row;
	}

	/**
	 * this method sets the bowColumn location
	 * 
	 * @param bowColumn the bowColumn to set
	 */
	public void setBowColumn(int column) {
		this.bowColumn = column;
	}

	/**
	 * This method sets the horizontal position (true/false)
	 * 
	 * @param horizontal the horizontal to set
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	// Abstract methods

	/**
	 * Returns the type of ship as a String. To be overridden by subclasses
	 * 
	 * @return
	 */
	public abstract String getShipType();

	// Other Methods

	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {

		// condition where it is horizontally placed
		if (horizontal) {

			// return false if it sticks out beyond (bow column + length of ship > 10)
			if (column + getLength() > 10) {
				return false;
			}

			// looping through (all surrounding coordinates around the given row,column
			// coordinate, if any of those square is occupied, return false)
			// we count from column - 1 position to column + 1 position, and find if each of
			// the 3
			// of the row in that column is occupied

			for (int c = column - 1; c <= column + getLength(); c++) {
				if (ocean.isOccupied(row - 1, c) || ocean.isOccupied(row, c) || ocean.isOccupied(row + 1, c)) {
					return false;
				}
			}

			// otherwise, return true
			return true;
		}

		// condition where it is vertically placed
		else {

			// return false if it sticks out beyond (bow row + length of ship > 10)
			if (row + getLength() > 10) {
				return false;
			}

			// looping through (all surrounding coordinates around the given row,column
			// coordinate, if any of those square is occupied, return false)

			for (int r = row - 1; r <= row + getLength(); r++) {
				if (ocean.isOccupied(r, column - 1) || ocean.isOccupied(r, column) || ocean.isOccupied(r, column + 1)) {
					return false;
				}
			}

			// otherwise, return true
			return true;
		}

	}

	/**
	 * “Puts” the ship in the ocean. This involves giving values to the bowRow,
	 * bowColumn, and horizontal instance variables in the ship, and it also
	 * involves putting a reference to the ship in each of 1 or more locations (up
	 * to 4) in the ships array in the Ocean object. horizontal: east, vertical
	 * south (location of bow)
	 * 
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {

		// set bowRow, bowColumn and Horizontal
		this.setBowRow(row);
		this.setBowColumn(column);
		this.setHorizontal(horizontal);

		// get the ship array from the ocean class:

		Ship[][] shipArray = ocean.getShipArray();

		// if conditions to decide where to place the ship at (horizontal, vertical)

		if (horizontal) {

			// looping through the length of the ship for the columns
			for (int i = column; i < column + this.getLength(); i++) {

				shipArray[row][i] = this;
			}
		}

		// condition where it is vertical
		else {

			// looping through the length of the ship and place in rows
			for (int j = row; j < row + this.getLength(); j++) {

				shipArray[j][column] = this;
			}
		}
	}

	/**
	 * If a part of the ship occupies the given row and column, and the ship hasn’t
	 * been sunk, mark that part of the ship as “hit” (in the hit array, index 0
	 * indicates the bow) and return true; otherwise return false.
	 * 
	 * @param row
	 * @param column
	 * @return
	 */

	boolean shootAt(int row, int column) {

		// if ship is not sunk, mark that part of the ship as "hit" (true) for either
		// horizontal or vertical options
		if (this.isSunk() == false) {

			// if horizontal, mark that part as "hit"
			if (this.horizontal) {

				hit[column - this.bowColumn] = true;
				return true;

				// if it is vertical, mark that part as "hit"
			} else {

				hit[row - this.bowRow] = true;
				return true;
			}
		}

		// if it is sunk, return false
		else {
			return false;
		}
	}

	/**
	 * Return true if every part of the ship has been hit, false otherwise
	 * 
	 * @return
	 */
	boolean isSunk() {

		// looping through the length of the ship and check if every part has been hit,
		// if not return false, otherwise, return true
		for (int i = 0; i < this.length; i++) {

			if (this.hit[i] == false) {
				return false;
			}

		}
		// return true if is sunk (all parts of the ship are hit)
		
		return true;

	}

	/**
	 * Returns a single-character String to use in the Ocean’s print method. This
	 * method should return ”s” if the ship has been sunk and ”x” if it has not been
	 * sunk. This method can be used to print out locations in the ocean that have
	 * been shot at;
	 */
	@Override
	public String toString() {

		// if it is sunk, return "s"
		if (this.isSunk()) {
			return "s";

			// if not yet sunk, return "x"
		} else {
			return "x";
		}

	}

}
