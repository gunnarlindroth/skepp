package skepp;

final class Coordinate {

    private final int row;
    private final int column;

    Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
        
    }

    int getRow() {
        return row;
    }

    int getColumn() {
        return column;
    }

    public String toString() {
        return "[" + row + ", " + column + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + column;
        result = prime * result + row;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Coordinate)) {
            return false;
        }
        Coordinate other = (Coordinate) obj;
        if (column != other.column) {
            return false;
        }
        if (row != other.row) {
            return false;
        }
        return true;
    }
}
