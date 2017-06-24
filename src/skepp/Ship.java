package skepp;

import javax.swing.SwingUtilities;

public class Ship {

  public static final boolean HORIZONTAL = true;

  // private final ShipType shipType;
  private final int lineStart;
  private final int lineEnd;

  private final int columnStart;
  private final int columnEnd;

  // private final int orientation;

  Ship(ShipType shipType, int lineStart, int columnStart, int orientation) {

    // this.shipType = shipType;

    this.lineStart = lineStart;
    this.columnStart = columnStart;
    // this.orientation = orientation;

    if (orientation == SwingUtilities.HORIZONTAL) {
      this.lineEnd = lineStart;
      this.columnEnd = columnStart + ShipType.getShipLength(shipType) - 1;

    }
    else { // SwingUtilities.VERTICAL
      this.lineEnd = lineStart + ShipType.getShipLength(shipType) - 1;
      this.columnEnd = columnStart;
    }
  }

  String getIndicator(int line, int column) {
    if (line >= lineStart && line <= lineEnd && column >= columnStart && column <= columnEnd) {
      // yes yes yes, this is our ship!

      // TODO maybe we could return "C" for CRUISER, "D" for DESTROYER etc?
      return "M ";      
    }
    return Util.EMPTY_STRING;
  }

}
