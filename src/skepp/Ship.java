package skepp;

import javax.swing.SwingUtilities;

// Maybe it would be better to make this class abstract and replace the "ShipType" enumeration with
// explicit "ship classes" which extends this one? E g "ShipSubmarine" and "ShipDestroyer"?
public class Ship {

    private final ShipType shipType;

    private final int lineStart;
    private final int lineEnd;

    private final int columnStart;
    private final int columnEnd;

    Ship(ShipType shipType, int lineStart, int columnStart, boolean horizontal) {
        this.shipType = shipType;
        this.lineStart = lineStart;
        this.columnStart = columnStart;

        if (horizontal) {
            this.lineEnd = lineStart;
            this.columnEnd = columnStart + ShipType.getShipLength(shipType) - 1;
        } else { 
            this.lineEnd = lineStart + ShipType.getShipLength(shipType) - 1;
            this.columnEnd = columnStart;
        }
    }

    Ship(ShipType shipType, Coordinate coordinate, boolean horizontal ) {
        this(shipType, coordinate.getRow(), coordinate.getColumn(), horizontal);
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
}
