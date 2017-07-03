package skepp;

import java.util.*;

public final class Board {

    private static final ShipType[] shipsAtGameStart = new ShipType[] { ShipType.CRUISER, ShipType.DESTROYER,
	    ShipType.DESTROYER, ShipType.FRIGATE, ShipType.FRIGATE, ShipType.FRIGATE, ShipType.SUBMARINE,
	    ShipType.SUBMARINE, ShipType.SUBMARINE, ShipType.SUBMARINE };

    private static final Random random = new Random();

    private static final int SIZE = 10;
    private final int[][] matrix;

    // this set contains all ships of this board
    private final Set<Ship> shipSet;

    // this set keeps track of available coordinates
    // (useful when adding a new ship)
    private final HashSet<Coordinate> availableCoordinateSet;

    Board() {
	matrix = new int[SIZE][SIZE];
	shipSet = new HashSet<Ship>();

	availableCoordinateSet = new HashSet<Coordinate>();
	for (int row = 0; row < matrix.length; row++) {
	    for (int col = 0; col < matrix[row].length; col++) {
		availableCoordinateSet.add(new Coordinate(row, col));
	    }
	}
    }

    public String toString() {
	StringBuilder sb = new StringBuilder(200);

	sb.append("  a b c d e f g h i j\n");

	for (int row = 0; row < matrix.length; row++) {
	    sb.append(row);
	    sb.append(" ");

	    for (int col = 0; col < matrix[row].length; col++) {
		String shipIndicator = getShipIndicator(row, col);
		sb.append(shipIndicator);
	    }

	    sb.append("\n");
	}

	return sb.toString();
    }

    private String getShipIndicator(int row, int column) {

	// System.out.println(" checking " + lineIndex + " : " + column);
	for (Ship ship : shipSet) {
	    String shipIndicator = ship.getIndicator(row, column);
	    if (!shipIndicator.isEmpty()) {
		return shipIndicator + " ";
	    }
	}

	// no ship found in this position
	return ". ";
    }

    void generateShips() {
	for (ShipType shipType : shipsAtGameStart) {

	    boolean horizontal = random.nextBoolean();
	    List<Coordinate> list = getAvailableCoordinates(shipType, horizontal);
	    if (!list.isEmpty()) {
		int randomCoordinate = random.nextInt(list.size());
		Coordinate coordinate = list.get(randomCoordinate);
		addShip(new Ship(shipType, coordinate, horizontal));
	    }
	}
    }

    /**
     * This method will return a list of coordinates which are possible to use
     * when adding a specified type of ship in a specified direction.
     */
    private List<Coordinate> getAvailableCoordinates(ShipType shipType, boolean horizontal) {

	// here we need to make sure that the new ship doesn't collide with an
	// already existing one
	Set<Coordinate> mySet = new HashSet<Coordinate>();

	// iterate over the set of available coordinates and add all that are
	// legal for this kind of ship
	for (Coordinate coordinate : availableCoordinateSet) {
	    if (isLegalCoordinate(shipType, horizontal, coordinate)) {
		mySet.add(coordinate);
	    }
	}

	return new LinkedList<Coordinate>(mySet);
    }

    /**
     * Return true if the provided ship type can successfully be added to
     * the provided coordinate.
     */
    private boolean isLegalCoordinate(ShipType shipType, boolean horizontal, Coordinate coordinate) {
	if (horizontal && coordinate.getColumn() + ShipType.getShipLength(shipType) > SIZE) {
	    return false;
	}

	if (coordinate.getRow() + ShipType.getShipLength(shipType) > SIZE) {
	    return false;
	}

	// TODO Tobias - ShipType.getArea() is not yet implemented!!!
	Collection<Coordinate> shipArea = ShipType.getArea(shipType, horizontal, coordinate);
	for (Coordinate c : shipArea) {
	    if (!availableCoordinateSet.contains(c)) {
		return false;
	    }
	}

	return true;
    }

    void addShip(Ship ship) {

	shipSet.add(ship);

	// make sure to reduce the set of available coordinates
	Collection<Coordinate> occupied = ship.getCoordinatesShip();
	availableCoordinateSet.removeAll(occupied);
	System.out.println(
		"Added ship " + ship.getShipType() + ", reduced available set: " + availableCoordinateSet.size());
    }

    private List<Coordinate> getPossibleCoordinatesOld(ShipType shipType, boolean horizontal) {
	List<Coordinate> list = new LinkedList<Coordinate>();
	Coordinate coordinate = getRandomCoordinate();
	System.out.println("Random Coordinate: " + coordinate);

	if (horizontal) {
	    int row = random.nextInt(10);
	    if (shipType == ShipType.CRUISER) {
		int column = random.nextInt(7);
		list.add(new Coordinate(row, column));

	    } else if (shipType == ShipType.DESTROYER) {
		int column = random.nextInt(8);
		list.add(new Coordinate(row, column));

	    } else if (shipType == ShipType.FRIGATE) {
		int column = random.nextInt(9);
		list.add(new Coordinate(row, column));

	    } else if (shipType == ShipType.SUBMARINE) {
		int column = random.nextInt(10);
		list.add(new Coordinate(row, column));
	    }
	}

	else {
	    int column = random.nextInt(10);
	    if (shipType == ShipType.CRUISER) {
		int row = random.nextInt(7);
		list.add(new Coordinate(row, column));

	    } else if (shipType == ShipType.DESTROYER) {
		int row = random.nextInt(8);
		list.add(new Coordinate(row, column));

	    } else if (shipType == ShipType.FRIGATE) {
		int row = random.nextInt(9);
		list.add(new Coordinate(row, column));

	    } else if (shipType == ShipType.SUBMARINE) {
		int row = random.nextInt(10);
		list.add(new Coordinate(row, column));
	    }
	}

	return list;
    }

    private Coordinate getRandomCoordinate() {
	List<Coordinate> list = new LinkedList<Coordinate>(availableCoordinateSet);
	int randomCoordinateIndex = random.nextInt(list.size());
	return list.get(randomCoordinateIndex);
    }
}