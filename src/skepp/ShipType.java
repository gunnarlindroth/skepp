package skepp;

import java.util.*;

public enum ShipType {

    CRUISER,

    DESTROYER,

    FRIGATE,

    SUBMARINE;

    static final int getShipLength(ShipType shipType) {
	switch (shipType) {

	case CRUISER:
	    return 4;

	case DESTROYER:
	    return 3;

	case FRIGATE:
	    return 2;

	case SUBMARINE:
	    return 1;

	default:
	    return 0;
	}
    }

    static Collection<Coordinate> getArea(ShipType shipType, boolean horizontal, Coordinate coordinate) {

	// TODO!!!
	// Create a set of coordinates which would be occupied by this kind of
	// shipType and direction if added to the specified coordinate starting point

	return Collections.EMPTY_SET;
    }
}
