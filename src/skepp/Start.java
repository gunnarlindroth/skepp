<<<<<<< Upstream, based on branch 'master' of https://github.com/gunnarlindroth/skepp.git
package skepp;

public final class Start {

    public static void main(String[] args) {
        System.out.println("Nu kör vi!");
        System.out.println("Jag skjuter på D3");
        System.out.println("Mitt motdrag blir F9");
        System.out.println("Miss!");
        System.out.println("Miss igen!");
        System.out.println("Träff!");
                
        System.out.println("\nNu måste vi göra ett bräde! Så här kanske?\n"); //Ändrade designen lite, kanske är bättre?
        
        Board board = new Board();
        
        System.out.println(board);
    }
}
=======
package skepp;

import javax.swing.SwingUtilities;

public final class Start {

    public static void main(String[] args) {
        System.out.println("Nu kör vi!");
        System.out.println("Jag skjuter på D3");
        System.out.println("Mitt motdrag blir F9");
        System.out.println("Miss!");
        System.out.println("Miss igen!");
        System.out.println("Träff!");
                
        System.out.println("\nNu måste vi göra ett bräde! Så här kanske?\n");
        
        Board board = new Board();
        
        board.addShip(new Ship(ShipType.CRUISER, 2, 3, SwingUtilities.HORIZONTAL));
        
        board.addShip(new Ship(ShipType.DESTROYER, 5, 8, SwingUtilities.VERTICAL));
        
        System.out.println(board);
    }
}
>>>>>>> 8fcbb46 Add an attempt to add ships to board
