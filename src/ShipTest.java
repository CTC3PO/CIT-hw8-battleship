//Name: Chau Tran
//Penn ID: 58664366
//Statement of work:
//-Resources: lecture notes and videos from module 9,10,11 on Coursera
//-I worked alone without help

package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;

	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());

		// TODO
		// test case 1 - get length of cruiser, should return 3
		Ship cruiser = new Cruiser();
		assertEquals(3, cruiser.getLength());

		// test case 2 - get length of destroyer, should return 2
		Ship destroyer = new Destroyer();
		assertEquals(2, destroyer.getLength());

		// test case 3 - get length of submarine, not equal 2
		Ship submarine = new Submarine();
		assertNotEquals(2, submarine.getLength());

		// test case 4 - get length of emptySea, should be 1
		Ship emptySea = new EmptySea();
		assertEquals(1, emptySea.getLength());

	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());

		// TODO
		// test case 2 - place a destroyer and get the bowColumn
		Ship destroyer = new Destroyer();

		int rowD = 5;
		int columnD = 2;
		boolean horizontalD = true;
		destroyer.placeShipAt(rowD, columnD, horizontalD, ocean);
		assertEquals(rowD, destroyer.getBowRow());

		// test case 3 - place a submarine and get the bowRow,
		Ship submarine = new Submarine();

		int rowS = 1;
		int columnS = 7;
		boolean horizontalS = true;
		submarine.placeShipAt(rowS, columnS, horizontalS, ocean);
		assertEquals(rowS, submarine.getBowRow());

	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());

		// TODO
		// test case 2 - place a cruiser vertically and get the bow column

		Ship cruiser = new Cruiser();
		int rowS = 4;
		int columnS = 2;
		boolean horizontalS = false;
		cruiser.placeShipAt(rowS, columnS, horizontalS, ocean);
		cruiser.setBowColumn(columnS);
		assertEquals(columnS, cruiser.getBowColumn());

		// test case 3 - place a destroyer horizontally and get bow column

		Ship destroyer = new Destroyer();
		int rowD = 6;
		int columnD = 8;
		boolean horizontalD = true;
		destroyer.placeShipAt(rowD, columnD, horizontalD, ocean);
		destroyer.setBowColumn(columnD);
		assertEquals(columnD, destroyer.getBowColumn());

	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);

		// TODO

		// test case 2 - create new cruiser
		Ship cruiser = new Cruiser();
		boolean[] hitsC = new boolean[3];
		assertArrayEquals(hitsC, cruiser.getHit());
		assertFalse(cruiser.getHit()[0]);
		assertFalse(cruiser.getHit()[1]);

		// turn the first element = true and test again
		cruiser.getHit()[0] = true;
		assertTrue(cruiser.getHit()[0]);

		// test case 3- create new destroyer and test

		Ship destroyer = new Destroyer();
		boolean[] hitsD = new boolean[2];
		assertArrayEquals(hitsD, destroyer.getHit());
		assertFalse(destroyer.getHit()[0]);
		assertFalse(destroyer.getHit()[1]);

		// turn the second element = true and test again
		destroyer.getHit()[1] = true;
		assertTrue(destroyer.getHit()[1]);

		// test case 4 - create new emptySea and test (should always be false)

		Ship emptySea = new EmptySea();
		boolean[] hitsE = new boolean[1];
		assertArrayEquals(hitsE, emptySea.getHit());
		assertFalse(emptySea.getHit()[0]);

	}

	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());

		// TODO

		// test case 2, submarine
		Ship submarine = new Submarine();
		assertEquals("submarine", submarine.getShipType());

		// test case 3, cruiser (case sensitive)
		Ship cruiser = new Cruiser();
		assertEquals("cruiser", cruiser.getShipType());
		assertNotEquals("Cruiser", cruiser.getShipType());

		// test case 4, emptySea (with "empty" and different input)
		Ship emptySea = new EmptySea();
		assertEquals("empty", emptySea.getShipType());
		assertNotEquals(" ", emptySea.getShipType());
	}

	@Test
	void testIsHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());

		// TODO
		// test case 2 - create new cruiser and test for horizontal (first false, then
		// set to true)
		Ship cruiser = new Cruiser();
		int rowC = 0;
		int columnC = 4;
		boolean horizontalC = false;
		cruiser.placeShipAt(rowC, columnC, horizontalC, ocean);
		assertFalse(cruiser.isHorizontal());

		// set horizontal to true and test again
		cruiser.setHorizontal(true);
		assertTrue(cruiser.isHorizontal());

		// test case 3 - edge case - test new emptySea (length = 1)
		Ship emptySea = new EmptySea();
		int rowE = 0;
		int columnE = 4;

		emptySea.setHorizontal(false);
		assertFalse(emptySea.isHorizontal());

	}

	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());

		// TODO
		// test case 2 - test for destroyer
		Ship destroyer = new Destroyer();
		int rowD = 2;
		int columnD = 4;
		boolean horizontalD = false;
		destroyer.setBowRow(rowD);
		assertEquals(rowD, destroyer.getBowRow());

		//// test case 3 - test for outofbound column for submarine
		Ship submarine = new Submarine();
		int rowS = 2;
		int columnS = 11;
		boolean horizontalS = true;
		submarine.setBowRow(rowS);
		assertEquals(rowS, submarine.getBowRow());

	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());

		// TODO
		// test case 2 - test for destroyer
		Ship destroyer = new Destroyer();
		int rowD = 2;
		int columnD = 4;
		boolean horizontalD = false;
		destroyer.setBowColumn(columnD);
		assertEquals(columnD, destroyer.getBowColumn());

		//// test case 3 - test for outofbound column for submarine
		Ship submarine = new Submarine();
		int rowS = 2;
		int columnS = 11;
		boolean horizontalS = true;
		submarine.setBowColumn(columnS);
		assertEquals(columnS, submarine.getBowColumn());
	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());

		// TODO
		// test case 2 - cruiser
		Ship cruiser = new Cruiser();
		int rowC = 5;
		int columnC = 7;
		boolean horizontalC = false;
		cruiser.setHorizontal(horizontalC);
		assertFalse(cruiser.isHorizontal());

		// test case 3 - emptySea
		Ship emptySea = new EmptySea();
		int rowE = 5;
		int columnE = 7;
		boolean horizontalE = true;
		emptySea.setHorizontal(horizontalE);
		assertTrue(emptySea.isHorizontal());

	}

	@Test
	void testOkToPlaceShipAt() {

		// test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");

		// TODO

		// test case 2 - test for out of bound location ( index 10)

		Ship submarine = new Submarine();

		int rowS = 10;
		int columnS = 10;
		boolean horizontalS = true;
		boolean okS = submarine.okToPlaceShipAt(rowS, columnS, horizontalS, ocean);
		// not okay, touching the battleship diagonally
		assertFalse(okS, "OK to place ship here.");

		// test case 3 - test for when touching another ship

		battleship.placeShipAt(row, column, horizontal, ocean);

		Ship submarine2 = new Submarine();

		int rowS2 = 1;
		int columnS2 = 1;
		boolean horizontalS2 = true;
		boolean okS2 = submarine2.okToPlaceShipAt(rowS2, columnS2, horizontalS2, ocean);
		// not okay, touching the battleship vertically (below)
		assertFalse(okS, "OK to place ship here.");

		// test case 2 - create destroyer and place and test
		battleship.placeShipAt(row, column, horizontal, ocean);

		Ship destroyer = new Destroyer();

		int rowD2 = 1;
		int columnD2 = 6;
		boolean horizontalD2 = true;
		boolean okD2 = destroyer.okToPlaceShipAt(rowD2, columnD2, horizontalD2, ocean);
		// not okay, touching the battleship diagonally
		assertFalse(okD2, "OK to place ship here.");

		// pick another valid location, should return true
		int rowD3 = 2;
		int columnD3 = 6;
		boolean horizontalD3 = true;
		boolean okD3 = destroyer.okToPlaceShipAt(rowD3, columnD3, horizontalD3, ocean);
		assertTrue(okD3, "OK to place ship here.");

		// test case 3 - place the destroyer, create a cruiser and try to place it
		// vertically
		destroyer.placeShipAt(rowD3, columnD3, horizontalD3, ocean);

		Ship cruiser = new Cruiser();

		int rowC = 3;
		int columnC = 5;
		boolean horizontalC = false;
		boolean okC = cruiser.okToPlaceShipAt(rowC, columnC, horizontalC, ocean);
		// not okay, touching the destroyer
		assertFalse(okC, "OK to place ship here.");

		// okay, not touching any ships
		int rowC2 = 6;
		int columnC2 = 4;
		boolean horizontalC2 = false;
		boolean okC2 = cruiser.okToPlaceShipAt(rowC2, columnC2, horizontalC2, ocean);
		// not okay, diagonally from destroyer
		assertTrue(okC2, "OK to place ship here.");

	}

	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {

		// test when other ships are in the ocean

		// place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		// test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");

		// TODO
		// test case 2 - create destroyer and place and test

		Ship destroyer = new Destroyer();

		int rowD2 = 1;
		int columnD2 = 6;
		boolean horizontalD2 = true;
		boolean okD2 = destroyer.okToPlaceShipAt(rowD2, columnD2, horizontalD2, ocean);
		// not okay, touching the battleship diagonally
		assertFalse(okD2, "OK to place ship here.");

		// pick another valid location, should return true
		int rowD3 = 2;
		int columnD3 = 6;
		boolean horizontalD3 = true;
		boolean okD3 = destroyer.okToPlaceShipAt(rowD3, columnD3, horizontalD3, ocean);
		assertTrue(okD3, "OK to place ship here.");

		// test case 3 - place the destroyer, create a cruiser and try to place it
		// vertically
		destroyer.placeShipAt(rowD3, columnD3, horizontalD3, ocean);

		Ship cruiser = new Cruiser();

		int rowC = 3;
		int columnC = 5;
		boolean horizontalC = false;
		boolean okC = cruiser.okToPlaceShipAt(rowC, columnC, horizontalC, ocean);
		// not okay, touching the destroyer
		assertFalse(okC, "OK to place ship here.");

		// okay, not touching any ships
		int rowC2 = 6;
		int columnC2 = 4;
		boolean horizontalC2 = false;
		boolean okC2 = cruiser.okToPlaceShipAt(rowC2, columnC2, horizontalC2, ocean);
		// not okay, diagonally from destroyer
		assertTrue(okC2, "OK to place ship here.");

	}

	@Test
	void testPlaceShipAt() {

		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());

		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);

		// TODO
		// test case 2 - destroyer
		Ship destroyer = new Destroyer();
		int rowD = 4;
		int columnD = 2;
		boolean horizontalD = false;
		destroyer.placeShipAt(rowD, columnD, horizontalD, ocean);
		assertEquals(rowD, destroyer.getBowRow());
		assertEquals(columnD, destroyer.getBowColumn());
		assertFalse(destroyer.isHorizontal());

		assertEquals("empty", ocean.getShipArray()[4][1].getShipType());
		assertEquals("destroyer", ocean.getShipArray()[4][2].getShipType());
		assertEquals("destroyer", ocean.getShipArray()[3][2].getShipType());

		// test case 3 - submarine
		Ship submarine = new Submarine();
		int rowS = 6;
		int columnS = 6;
		boolean horizontalS = true;
		destroyer.placeShipAt(rowS, columnS, horizontalS, ocean);
		assertEquals(rowS, submarine.getBowRow());
		assertEquals(columnS, submarine.getBowColumn());
		assertFalse(submarine.isHorizontal());

		assertEquals("submarine", ocean.getShipArray()[6][6].getShipType());
		// test for case sensitive
		assertNotEquals("Submarine", ocean.getShipArray()[6][6].getShipType());

	}

	@Test
	void testShootAt() {

		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);

		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = { false, false, false, false };
		assertArrayEquals(hitArray0, battleship.getHit());

		// TODO
		// test case 2 -
		Ship destroyer = new Destroyer();
		int rowD = 5;
		int columnD = 5;
		boolean horizontalD = true;
		destroyer.placeShipAt(rowD, columnD, horizontalD, ocean);

		assertFalse(destroyer.shootAt(5, 7));
		boolean[] hitArray1 = { false, false };
		assertArrayEquals(hitArray1, destroyer.getHit());

		assertTrue(destroyer.shootAt(5, 5));
		assertTrue(destroyer.shootAt(5, 4));
		boolean[] hitArray2 = { true, true };
		assertArrayEquals(hitArray2, destroyer.getHit());

		// test case 3 - EmptySea (always return false)
		Ship emptySea = new EmptySea();
		int rowE = 9;
		int columnE = 7;
		boolean horizontalE = false;
		emptySea.placeShipAt(rowE, columnE, horizontalE, ocean);

		assertFalse(emptySea.shootAt(6, 7));

	}

	@Test
	void testIsSunk() {

		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());

		// TODO
		// test case 2 - create a destroyer and test if it is sunk
		Ship destroyer = new Destroyer();
		int rowD = 5;
		int columnD = 5;
		boolean horizontalD = true;
		destroyer.placeShipAt(rowD, columnD, horizontalD, ocean);

		assertFalse(destroyer.isSunk());
		assertFalse(destroyer.shootAt(5, 3));
		assertTrue(destroyer.shootAt(5, 5));
		assertFalse(destroyer.isSunk());

		assertTrue(destroyer.shootAt(5, 4));
		assertTrue(destroyer.isSunk());

		// test case 3 - create an EmptySea and test if it is sunk, it will always
		// return false
		Ship emptySea = new EmptySea();
		int rowE = 6;
		int columnE = 7;
		boolean horizontalE = false;
		emptySea.placeShipAt(rowE, columnE, horizontalE, ocean);

		assertFalse(emptySea.isSunk());

	}

	@Test
	void testToString() {

		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());

		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());

		// TODO
		// test case 2
		Ship submarine = new Submarine();
		assertEquals("x", submarine.toString());

		int rowS = 2;
		int columnS = 3;
		boolean horizontalS = true;
		submarine.placeShipAt(rowS, columnS, horizontalS, ocean);
		submarine.shootAt(2, 3);
		// submarine is sunk since length = 1
		assertEquals("s", submarine.toString());

		// test case 3
		Ship destroyer = new Destroyer();
		int rowD = 5;
		int columnD = 5;
		boolean horizontalD = true;
		destroyer.placeShipAt(rowD, columnD, horizontalD, ocean);

		// after first shot, still "x" since ship hasn't sunk
		destroyer.shootAt(5, 5);
		assertEquals("x", destroyer.toString());

		// after second shot, it is sunk "s"
		destroyer.shootAt(5, 4);
		assertEquals("s", destroyer.toString());

	}

}
