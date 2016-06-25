/***
 * Tic Tac Toe game running on console
 * Developed by Willian D. Almeida
 * Version: 1.0.1 24/06/2016
 ***/

import java.io.IOException;
import java.util.Scanner;

public class TicTacToeConsole {

    public static void main(String[] args) throws IOException {
        // Input string
        String inp;

        Movement mv = new Movement();
        Scanner in = new Scanner(System.in);

        System.out.println("Select mode: 1-Player vs Player or 2-Demostration  mode");
        //TODO: fix the switch for PxP and Demo-Mode
        //mode(R - Real, D - Demonstration
        char mode = 'R';
        switch (in.nextInt()) {
            case 1:
                System.out.println("X' or 'O' ?\n");
                inp = in.next();
                char[] pl;
                if (null != inp)
                    switch (inp.toUpperCase()) {
                        case "X":
                            pl = new char[]{'X', 'O'};
                            mv.getMoves(pl, mode);
                            break;
                        case "O":
                            pl = new char[]{'O', 'X'};
                            mv.getMoves(pl, mode);
                            break;
                        default:
                            System.out.println("Wrong Input!! Please choose either X or O\n");
                            break;
                    }
                break;
            default:
                //D - "demonstration mode"
                mode = 'D';
                pl = new char[]{'X', 'O'};
                mv.getMoves(pl, mode);
                break;
        }
    }
}
