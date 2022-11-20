//Name: Chau Tran
//Penn ID: 58664366
//Statement of work:
//-Resources: lecture notes and videos from module 9,10,11 on Coursera
//-I worked alone without help

package battleship;

import java.util.Scanner;

/**
 * This class contains the main method. It will set up the game; accept ”shots”
 * from the user; display the results; and print final scores. All input/output
 * is done here. All computation will be done in the Ocean class and the various
 * Ship classes.
 * 
 * @author chaut
 *
 */
public class BattleshipGame {

	public static void main(String[] args) {

		// welcome message
		System.out.println("Welcome to Battleship Game!");

		// create the ocean object
		Ocean ocean = new Ocean();

		// place all ships randomly on the ocean
		ocean.placeAllShipsRandomly();

		// for debugging only
//		ocean.printWithShips();

		// print the ocean interface
		ocean.print();

		// create a scanner to take in user input
		Scanner sc = new Scanner(System.in);

		// while loop for while the game is still playing (not over)

		while (ocean.isGameOver() == false) {			
 
			
			// ask for user's input for row
			System.out.println("Enter a row: ");

			// parse the user's input to integer
			int row = Integer.parseInt(sc.next());

			// ask for user's input for column
			System.out.println("Enter a column: ");

			// parse the user's input to integer
			int column = Integer.parseInt(sc.next());

			System.out.println();

			// put in try-catch block, call shootAt the ocean and print out the result (if
			// hit but not sunk, print "hit", if "sunk a ship", print out the ship type,
			// else, print "miss"

			try {

				// create a variable for shootAt method from ocean class
				boolean shootSuccess = ocean.shootAt(row, column);

				// if shoot is successfully (shoot at a coordinate that has a ship/part of a
				// ship), it will check if the ship is sunk and print out the type of the ship

				if (shootSuccess) {

					System.out.println("hit");

					// get ships array and ship
					Ship[][] shipArray = ocean.getShipArray();

					Ship ship = shipArray[row][column];

					// check if the ship is sunk by calling the the Ship class method isSunk
					if (ship.isSunk()) {

						System.out.println("You just sunk a ship ( " + ship.getShipType() + " )");
					}
				}

				// if not shot at a coordinate with a ship, print out "miss"
				else {
					System.out.println("miss");
				}
			}

			// catch NumberFormatException
			catch (NumberFormatException e) {

				e.printStackTrace();
			}
			
			ocean.print();

		}

		// otherwise, if the game is over, print out how many shots were fired
		System.out.println("The game is over!");

		System.out.println("Total shots to sink all ships are: " + ocean.getShotsFired() + " shots.");

		// close the scanner object
		sc.close();

	}

}
