package skepp;

import java.util.*;

public final class Board {

private static final ShipType[] shipsAtGameStart = new ShipType[]{ShipType.CRUISER, ShipType.DESTROYER, ShipType.DESTROYER, ShipType.FRIGATE, ShipType.FRIGATE, ShipType.FRIGATE, 
    ShipType.SUBMARINE, ShipType.SUBMARINE, ShipType.SUBMARINE, ShipType.SUBMARINE};
     //private static final ShipType[] shipsAtGameStart = new ShipType[]{ShipType.CRUISER};

    private static final int BOARDSIZE = 10;
    private static final int SIZE_MATRIX = BOARDSIZE * BOARDSIZE;

    private static final Random random = new Random();

    private final int[][] matrix;

    // this set contains all ships of this board
    private final Set<Ship> shipSet;

    // this set keeps track of available coordinates
    // (useful when adding a new ship)
    private final HashSet<Coordinate> availableCoordinateSet;

    Board() {
        matrix = new int[BOARDSIZE][BOARDSIZE];
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
            System.out.println("Adding ship: " + shipType);
            

            boolean horizontal = random.nextBoolean();
            if(shipType==ShipType.SUBMARINE){
                System.out.println("Hoppsam, en submarine ska läggas till " + horizontal);
                
            }

            List<Coordinate> list = getAvailableCoordinates(shipType, horizontal);
            if (!list.isEmpty()) {
                int randomCoordinate = random.nextInt(list.size());
                Coordinate coordinate = list.get(randomCoordinate);
                addShip(new Ship(shipType, coordinate, horizontal));
            }else{
                System.out.println("VARNING");
            }
        }
    }

    /**
     * This method will return a list of coordinates which are possible to use
     * when adding a specified type of ship in a specified direction.
     */
    private List<Coordinate> getAvailableCoordinates(ShipType shipType, boolean horizontal) {

        // here we need to make sure that the new ship doesn't collide with an already existing one
        List<Coordinate> list = new LinkedList<Coordinate>();
        
        
        

        // iterate over the set of available coordinates and add all that are legal for this kind of ship
        for (Coordinate coordinate : availableCoordinateSet) {
            System.out.println("Allkoordinat: " + coordinate);
            if (isLegalCoordinate(shipType, horizontal, coordinate)) {
                list.add(coordinate);
                System.out.println("Koordinat:" + coordinate);
            }
        }

        return list;
    }

    /**
     * Return true if the provided ship type can successfully be added to the
     * provided coordinate.
     */
    private boolean isLegalCoordinate(ShipType shipType, boolean horizontal, Coordinate coordinate) {
        if (horizontal && coordinate.getColumn() + ShipType.getShipLength(shipType) > BOARDSIZE) {
            return false;
        } else if (coordinate.getRow() + ShipType.getShipLength(shipType) > BOARDSIZE) {
            return false;
        }

        // let's check that coordinate is not too close to another ship
        if (availableCoordinateSet.size() < SIZE_MATRIX) { // don't bother to check if no other ships are added

            Collection<Coordinate> shipArea = ShipType.getArea(shipType, horizontal, coordinate);
            for (Coordinate c : shipArea) {
                if (!availableCoordinateSet.contains(c)) {
                    return false;
                }
            }
        }

        return true;
    }

    void addShip(Ship ship) {

        shipSet.add(ship);
        System.out.println("Jag la till ett skepp (" + ship.getShipType() + ") size: " + availableCoordinateSet.size());

        // make sure to reduce the set of available coordinates
        Collection<Coordinate> occupied = ShipType.getAreaPerimeter(ship.getShipType(), ship.isHorizontal(), ship.getInitialCoordinate());
        System.out.println("Occupied: " + occupied.size());
        availableCoordinateSet.removeAll(occupied);
        System.out.println(
                "Added ship " + ship.getShipType() + ", reduced available set: " + availableCoordinateSet.size());
    }

    private Coordinate getRandomCoordinate() {
        List<Coordinate> list = new LinkedList<Coordinate>(availableCoordinateSet);
        int randomCoordinateIndex = random.nextInt(list.size());
        return list.get(randomCoordinateIndex);
    }

}
