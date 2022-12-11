
package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

	Ocean ocean;

	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;

	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testEmptyOcean() {

		// tests that all locations in the ocean are "empty"

		Ship[][] ships = ocean.getShipArray();

		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];

				assertEquals("empty", ship.getShipType());
			}
		}

		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());

		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());

		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}

	@Test
	void testPlaceAllShipsRandomly() {

		// tests that the correct number of each ship type is placed in the ocean

		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();

		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;

		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}

		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}

		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);

		// calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE;
		int occupiedSpaces = (NUM_BATTLESHIPS * 4) + (NUM_CRUISERS * 3) + (NUM_DESTROYERS * 2) + (NUM_SUBMARINES * 1);

		// test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.isOccupied(1, 5));

		// TODO
		// test case 2
		Battleship battleship = new Battleship();
		battleship.placeShipAt(4, 5, true, ocean);
		assertTrue(ocean.isOccupied(4, 5));
		assertFalse(ocean.isOccupied(4, 3));
		assertFalse(ocean.isOccupied(4, 0));

		// test case 3 - test EmptySea
		EmptySea emptySea = new EmptySea();
		emptySea.placeShipAt(7, 7, false, ocean);
		assertFalse(ocean.isOccupied(7, 7));

	}

	@Test
	void testShootAt() {

		assertFalse(ocean.shootAt(0, 1));

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));

//		// TODO
//		//test case 2
//		Submarine submarine = new Submarine();
//		int rowS = 0;
//		int columnS = 0;
//		boolean horizontalS= false;
//		submarine.placeShipAt(rowS, columnS, horizontalS, ocean);
//		
//		assertTrue(ocean.shootAt(0, 0));		
//		assertEquals(2, ocean.getHitCount());
//		
//		//test case 3
//		
//		Cruiser cruiser = new Cruiser();
//		int rowC = 8;
//		int columnC = 8;
//		boolean horizontalC = false;
//		cruiser.placeShipAt(rowC, columnC, horizontalC, ocean);
//
//		assertFalse(ocean.shootAt(4, 8));
//		assertTrue(ocean.shootAt(7, 8));
//		assertTrue(ocean.shootAt(8, 8));
//		assertEquals(5, ocean.getHitCount());
		
	}

	@Test
	void testGetShotsFired() {

		// should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertFalse(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());

		// TODO
		// test case 2 - submarine (above) - shots fired always increase even if the
		// shot is miss (not at a location with ship)
		assertTrue(ocean.shootAt(0, 0));
		assertTrue(submarine.isSunk());
		assertEquals(7, ocean.getShotsFired());

		// test case 3 - add a cruiser and shoot at it
		Cruiser cruiser = new Cruiser();
		int rowC = 8;
		int columnC = 8;
		boolean horizontalC = false;
		cruiser.placeShipAt(rowC, columnC, horizontalC, ocean);

		assertFalse(ocean.shootAt(4, 8));
		assertTrue(ocean.shootAt(6, 8));
		assertTrue(ocean.shootAt(8, 8));
		assertEquals(10, ocean.getShotsFired());
		assertFalse(cruiser.isSunk());

		assertTrue(ocean.shootAt(7, 8));
		assertTrue(cruiser.isSunk());
		assertEquals(11, ocean.getShotsFired());

	}

	@Test
	void testGetHitCount() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());

		// TODO
		// test case 2
		Destroyer destroyer2 = new Destroyer();
		int row2 = 1;
		int column2 = 1;
		boolean horizontal2 = true;
		destroyer2.placeShipAt(row2, column2, horizontal2, ocean);

		// shoot at its location and check if it's sunk, increase hit count if hit but
		// not sunk
		assertTrue(ocean.shootAt(1, 1));
		assertFalse(destroyer2.isSunk());
		assertEquals(2, ocean.getHitCount());

		assertTrue(ocean.shootAt(1, 0));
		assertTrue(destroyer2.isSunk());
		assertEquals(3, ocean.getHitCount());

		// after it's sunk, hit count does not increase
		assertTrue(ocean.shootAt(1, 0));
		assertEquals(3, ocean.getHitCount());

		// test case 3 - emptySea (doesn't increase hit count since miss)
		EmptySea emptySea = new EmptySea();
		int rowE = 8;
		int columnE = 8;
		boolean horizontalE = true;
		emptySea.placeShipAt(rowE, columnE, horizontalE, ocean);

		// after trying to shoot at EmptySea, miss, hit count is still the same
		assertFalse(ocean.shootAt(8, 8));
		assertEquals(3, ocean.getHitCount());

	}

	@Test
	void testGetShipsSunk() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());

		// TODO
		// test case 2 - sunk a submarine and get shipsSunk

		Submarine submarine = new Submarine();
		int rowS = 3;
		int columnS = 4;
		boolean horizontalS = true;
		submarine.placeShipAt(rowS, columnS, horizontalS, ocean);

		assertTrue(ocean.shootAt(3, 4));
		assertTrue(submarine.isSunk());
		assertEquals(2, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());

		// test case 3 - for EmptySea ship, since it is not a real ship, shooting at the
		// location always returns false, and since isSunk is always false, ships sunk
		// count doesn't not increase

		EmptySea emptySea = new EmptySea();
		int rowE = 7;
		int columnE = 7;
		boolean horizontalE = true;
		emptySea.placeShipAt(rowE, columnE, horizontalE, ocean);

		assertFalse(ocean.shootAt(7, 7));
		assertFalse(emptySea.isSunk());
		assertEquals(2, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());

	}

	@Test
	void testGetShipArray() {

		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);

		assertEquals("empty", shipArray[0][0].getShipType());

		// TODO
		// test case 2 - place a submarine in array

		Submarine submarine = new Submarine();
		int rowS = 3;
		int columnS = 4;
		boolean horizontalS = true;
		submarine.placeShipAt(rowS, columnS, horizontalS, ocean);

		assertEquals("empty", shipArray[1][0].getShipType());
		assertEquals("submarine", shipArray[3][4].getShipType());

		// test case 3 - place a destroyer in array

		Destroyer destroyer = new Destroyer();
		int rowD = 1;
		int columnD = 5;
		boolean horizontalD = false;
		destroyer.placeShipAt(rowD, columnD, horizontalD, ocean);

		assertEquals("empty", shipArray[1][1].getShipType());
		assertEquals("destroyer", shipArray[1][5].getShipType());
	}

}
