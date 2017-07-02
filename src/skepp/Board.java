package skepp;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.swing.SwingUtilities;

public final class Board {

    private static final ShipType[] shipTypes = new ShipType[]{
        ShipType.CRUISER, ShipType.DESTROYER, ShipType.DESTROYER, ShipType.FRIGATE, ShipType.FRIGATE, ShipType.FRIGATE, ShipType.SUBMARINE, ShipType.SUBMARINE, ShipType.SUBMARINE, ShipType.SUBMARINE
    };

    private static final Random random = new Random();

    private static final int SIZE = 10;
    private final int[][] matrix;

    private final Set<Ship> shipSet;
    private final HashSet<Coordinate> availableCoordinateSet;

    Board() {
        matrix = new int[SIZE][SIZE];

        shipSet = new HashSet<Ship>();
        availableCoordinateSet = new HashSet<Coordinate>();
        for (int row = 0; row < matrix.length; row++) {

            

            for (int col = 0; col < matrix[row].length; col++) {
                

               availableCoordinateSet.add(new Coordinate(row, col));

            }}System.out.println("Hur många koordinater finns i availableCoordinateSet: " + availableCoordinateSet.size());
            
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
        return ". ";
    }

    public void addShip(Ship ship) {

        // TODO here we need to somehow make sure that the new ship doesn't collide
        // with an already existing one
        shipSet.add(ship);
    }

    void generateShips() {
        for (ShipType shipType : shipTypes) {

            boolean horizontal = random.nextBoolean();
            List<Coordinate> list = getPossibleCoordinates(shipType, horizontal);
            if (!list.isEmpty()) {
                Coordinate coordinate = list.get(0);
                addShip(new Ship(shipType, coordinate, horizontal));
            }
        }
    }

    private List<Coordinate> getPossibleCoordinates(ShipType shipType, boolean horizontal) {
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

            }else if (shipType == ShipType.FRIGATE){
                int column = random.nextInt(9);
                list.add(new Coordinate(row, column));
            }else if (shipType == ShipType.SUBMARINE) {
                int column = random.nextInt(10);
                list.add(new Coordinate(row, column));
                
            }
        } else {
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
            }else if (shipType == ShipType.SUBMARINE) {
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
