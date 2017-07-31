package skepp;

import java.util.*;

// Maybe it would be better to make this class abstract and replace the "ShipType" enumeration with
// explicit "ship classes" which extends this one? E g "ShipSubmarine" and "ShipDestroyer"?
public class Ship {

    private final ShipType shipType;
    

    private final int lineStart;
    private final int lineEnd;

    private final int columnStart;
    private final int columnEnd;
    
    
    
    private final boolean horizontal;

    Ship(ShipType shipType, int lineStart, int columnStart, boolean horizontal) {
	this.shipType = shipType;
	this.lineStart = lineStart;
	this.columnStart = columnStart;
        this.horizontal = horizontal;

	if (horizontal) {
	    this.lineEnd = lineStart;
	    this.columnEnd = columnStart + ShipType.getShipLength(shipType) - 1;
	} else {
	    this.lineEnd = lineStart + ShipType.getShipLength(shipType) - 1;
	    this.columnEnd = columnStart;
	}
    }

    Ship(ShipType shipType, Coordinate coordinate, boolean horizontal) {
	this(shipType, coordinate.getRow(), coordinate.getColumn(), horizontal);
    }

    ShipType getShipType() {
	return shipType;
    }
    
    Coordinate getInitialCoordinate() {
        return new Coordinate(lineStart, columnStart);
    }

    String getIndicator(int line, int column) {
	if (line >= lineStart && line <= lineEnd && column >= columnStart && column <= columnEnd) {
	    // yes yes yes, this is our ship!
	    switch (shipType) {

	    case CRUISER:
		return "C";

	    case DESTROYER:
		return "D";

	    case FRIGATE:
		return "F";

	    case SUBMARINE:
		return "S";

	    default:
		return "M";
	    }

	}
	return Util.EMPTY_STRING;
    }

    /**
     * This method will return the coordinates used by this ship
     */
    Collection<Coordinate> getCoordinatesShip() {
	List<Coordinate> list = new LinkedList<Coordinate>();
	for (int i = lineStart; i <= lineEnd; i++) {
	    for (int j = columnStart; j <= columnEnd; j++) {
		Coordinate c = new Coordinate(i, j);              
		 //System.out.println("Adding c: "+c);
		list.add(c);
	    }
	}

	return list;
    }

    boolean isHorizontal() {
        return horizontal;
    }

}
