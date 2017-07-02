package skepp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.SwingUtilities;
import javax.swing.plaf.synth.SynthSeparatorUI;

public final class Start {

  public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
    // System.out.println("Nu kör vi!");
    // System.out.println("Jag skjuter på D3");
    // System.out.println("Mitt motdrag blir F9");
    // System.out.println("Miss!");
    // System.out.println("Miss igen!");
    // System.out.println("Träff!");
    // System.out.println("\nNu måste vi göra ett bräde! Så här kanske?\n");
    // //Ändrade designen lite, kanske är bättre?
    //

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
      System.out.println("Äntligen dags för en omgång Sänka Skepp!");
      
      System.out.println("Vad heter du?");
      String player = in.nextLine();

      System.out.println("\nOk " + player + ", nu kör vi!\n");
    

    

    Board board = new Board();

    /*board.addShip(new Ship(ShipType.CRUISER, 2, 3, true));
    board.addShip(new Ship(ShipType.DESTROYER, 5, 8, false));
    board.addShip(new Ship(ShipType.FRIGATE, 7, 1, true));
    board.addShip(new Ship(ShipType.SUBMARINE, 0, 0, false));*/
    
    board.generateShips();

    System.out.println(board);
  }
}