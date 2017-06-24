package skepp;

import javax.swing.SwingUtilities;

// Maybe it would be better to make this class abstract and replace the "ShipType" enumeration with
// explicit "ship classes" which extends this one? E g "ShipSubmarine" and "ShipDestroyer"?

public class Ship {

  private final int lineStart;
  private final int lineEnd;

  private final int columnStart;
  private final int columnEnd;

  Ship(ShipType shipType, int lineStart, int columnStart, int orientation) {

    this.lineStart = lineStart;
    this.columnStart = columnStart;

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