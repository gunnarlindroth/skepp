package skepp;

public final class Start {

    public static void main(String[] args) {

	System.out.println("Äntligen dags för en omgång Sänka Skepp!");

	// Scanner in = new Scanner(System.in);
	// System.out.println("Vad heter du?");
	// String player = in.nextLine();

	String player = "";

	System.out.println("\nOk " + player + ", nu kör vi!\n");

	Board board = new Board();

	// board.addShip(new Ship(ShipType.CRUISER, 2, 3, true));
	// board.addShip(new Ship(ShipType.DESTROYER, 5, 8, false));
	// board.addShip(new Ship(ShipType.FRIGATE, 7, 1, true));
	// board.addShip(new Ship(ShipType.SUBMARINE, 0, 0, false));

	board.generateShips();

	System.out.println("\n");
	System.out.println(board);
    }
}