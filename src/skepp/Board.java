package skepp;

public final class Board {

  private static final int SIZE = 10;
  private final int[][] matrix;

  Board() {
    matrix = new int[SIZE][SIZE];
  }

  public String toString() {
    StringBuilder sb = new StringBuilder(200);

    int lineIndex = 0;

    sb.append("  0123456789\n");

    for (int[] line : matrix) {

      sb.append(lineIndex);
      sb.append(" ");

      for (int column : line) {
        sb.append('x');
      }

      sb.append("\n");

      lineIndex++;
    }

    return sb.toString();
  }
}