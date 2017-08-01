package skepp;

import java.util.*;

public enum ShipType {

    CRUISER, DESTROYER, FRIGATE, SUBMARINE;

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

    /**
     * Create a set of coordinates which would be occupied by this kind of
     * shipType and direction if added to the specified coordinate starting
     * point
     *
     * TODO maybe this way of finding out occupied coordinates is not really
     * very clever... why not return false immediately if a coordinate is
     * already occupied? This would save lots of iterations!
     *
     * TODO maybe it would be better to remove the available coordinates after
     * adding a ship?
     *
     * OBS! Något verkar fortfarande vara fel... Ibland hamnar skeppen väldigt
     * nära varandra ändå? Hmm... Och det är _aldrig_ något skepp som hamnar på
     * rad 9???
     */
    static Collection<Coordinate> getAreaPerimeter(ShipType shipType, boolean horizontal, Coordinate coordinate) {

        // jag flyttar skapandet av HashSet till metoden som faktiskt använder den!
        // System.out.println("getArea " + shipType);
        int row = coordinate.getRow();
        int column = coordinate.getColumn();
        int length = getShipLength(shipType);

        // i det här fallet finns det ingen anledning att dela upp beräkningarna per skepps-typ?
        // du har redan tagit reda på längden/bredden av den aktuella båten!
        if (horizontal) {
            return occupiedCoordinatesHorizontal(row, column, length);
        } else {
            return occupiedCoordinatesVertical(row, column, length);
        }

        // return Collections.EMPTY_SET;
    }

    private static Collection<Coordinate> occupiedCoordinatesHorizontal(int row, int column, int length) {

        // tjoho, snyggt med "HashSet<>" utan typen "<Coordinate>" - det här är det moderna sättet att skriva!
        // jag ändrar "Occupied" till "occupied", variabler börjar alltid med liten bokstav
        Collection<Coordinate> occupied = new HashSet<>();

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column; j < (column + length); j++) {
                if (i >= 0 && j >= 0 && i < 10 && j < 10) {
                    Coordinate c = new Coordinate(i, j);
                    //System.out.println("occ h: " + c);
                    occupied.add(c);
                }
            }
        }
        //if (column > 1 && (column + length) < 10 && row < 10 && row > -1) {

        occupied.add(new Coordinate(row, column - 1));
        occupied.add(new Coordinate(row, column + length));
        //}

        return occupied;
    }

    private static Collection<Coordinate> occupiedCoordinatesVertical(int row, int column, int length) {

        Collection<Coordinate> occupied = new HashSet<>();

        for (int i = column - 1; i <= column + 1; i++) {
            for (int j = row; j < (row + length); j++) {
                if (i >= 0 && j >= 0 && i < 10 && j < 10) {
                    Coordinate c = new Coordinate(j, i);
                    //System.out.println("occ v: " + c);
                    occupied.add(c);
                }
            }
        }
        //if (row > 1 && (row + length) < 10 && column < 10 && column > -1) {
        occupied.add(new Coordinate(row - 1, column));
        occupied.add(new Coordinate(row + length, column));
        // }

        return occupied;
    }

    static Collection<Coordinate> getArea(ShipType shipType, boolean horizontal, Coordinate coordinate) {
        int row = coordinate.getRow();
        int column = coordinate.getColumn();
        int length = getShipLength(shipType);

        if (horizontal) {
            return occupiedCoordinatesHorizontalSimple(row, column, length);
        } else {
            return occupiedCoordinatesVerticalSimple(row, column, length);
        }
    }

    private static Collection<Coordinate> occupiedCoordinatesHorizontalSimple(int row, int column, int length) {
        Collection<Coordinate> occupied = new HashSet<>();

        for (int j = column; j < (column + length); j++) {
            if (row >= 0 && j >= 0 && row < 10 && j < 10) {
                Coordinate c = new Coordinate(row, j);
                //System.out.println("occ h: " + c);
                occupied.add(c);
            }
        }
        return occupied;
    }

    private static Collection<Coordinate> occupiedCoordinatesVerticalSimple(int row, int column, int length) {
        Collection<Coordinate> occupied = new HashSet<>();

        for (int j = row; j < (row + length); j++) {
            if (column >= 0 && j >= 0 && column < 10 && j < 10) {
                Coordinate c = new Coordinate(j, column);
                //System.out.println("occ v: " + c);
                occupied.add(c);
            }
        }

        return occupied;
    }
}
