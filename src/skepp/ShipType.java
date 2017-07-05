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
        Collection<Coordinate> Occupied = new HashSet<>();
        int y = coordinate.getRow();
        int x = coordinate.getColumn();
        int length = getShipLength(shipType);
        if (shipType == ShipType.CRUISER) {
            if (horizontal) {
                occupiedCoordinatesHorizontal(Occupied, y, x, length);
            } else {
                occupiedCoordinatesVertical(Occupied, y, x, length);

            }
            // TODO!!!
            // Create a set of coordinates which would be occupied by this kind of
            // shipType and direction if added to the specified coordinate starting point
        } else if (shipType == ShipType.DESTROYER) {
            if (horizontal) {
                occupiedCoordinatesHorizontal(Occupied, y, x, length);
            } else {
                occupiedCoordinatesVertical(Occupied, y, x, length);

            }
        }
        return Collections.EMPTY_SET;
    }

    private static void occupiedCoordinatesHorizontal(Collection<Coordinate> Occupied, int y, int x, int length) {
        for (int i = y - 1; i <= y + 1; y++) {
            for (int j = x; i < (x + length); x++) {
                Occupied.add(new Coordinate(i, j));
            }
        }
        Occupied.add(new Coordinate(y, x - 1));
        Occupied.add(new Coordinate(y, x + length));
    }

    private static void occupiedCoordinatesVertical(Collection<Coordinate> Occupied, int y, int x, int length) {
        for (int i = x - 1; i <= x + 1; x++) {
            for (int j = y; i > (y + length); x++) {
                Occupied.add(new Coordinate(j, i));
            }
        }
        Occupied.add(new Coordinate(y - 1, x));
        Occupied.add(new Coordinate(y + length, x));
    }
}
