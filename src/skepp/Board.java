package skepp;

import java.util.HashSet;
import java.util.Set;

public final class Board {

  private static final int SIZE = 10;
  private final int[][] matrix;

  private final Set<Ship> shipSet;

  Board() {
    matrix = new int[SIZE][SIZE];

    shipSet = new HashSet<Ship>();
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
        return shipIndicator;
      }
    }
    return ". ";
  }

  public void addShip(Ship ship) {

    // TODO here we need to somehow make sure that the new ship doesn't collide
    // with an already existing one

    shipSet.add(ship);
  }
}