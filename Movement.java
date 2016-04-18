package TicTacToeConsole;
/*** If the player has completed a row, collum or diagonal, declare end of the game and winner ***/

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Movement {

    //Board Size
    private Draw dr = new Draw();
    private int bdSize = dr.getBdSize(), oCount = 0, xCount = 0;

    public int getxCount() {
        return xCount;
    }

    public int getoCount() {
        return oCount;
    }

    private char[][] board = dr.getBoard();

    private boolean slotAvaliable(int i, int j) {
        //Checks if slot is available, if it's not, it returns false
        return board[i][j] == ' ';
    }

    boolean NewMove(char player, int i, int j) {
        //Adds the new mov. to the board the increases the counter(X || O)
        if (!slotAvaliable(i, j)) return false;
        switch (player) {
            case 'X':
                xCount++;
                break;
            case 'O':
                oCount++;
                break;
        }
        board[i][j] = player;
        dr.setBoard(board);
        dr.output();
        return true;
    }

    void getMoves(char[]pl, char mode) throws IOException {
        //Receives the players input and adds to the board...
        try (Scanner in = new Scanner(System.in)) {
            int pln = 1, x, y; //Player number
            Random rand = new Random();
            do {
                pln = pln == 1 ? 0 : 1; //swap the player
                System.out.println("Player " + pl[pln] + " please enter coordinates...(x y)");
                if(mode == 'D'){
                    //Random Movements...
                    System.out.println("Demonstration Mode...");
                    x = rand.nextInt(3);
                    y = rand.nextInt(3);
                }
                else{
                    x = in.nextInt();
                    y = in.nextInt();
                }
                if (x >= bdSize || y >= bdSize) {
                    System.out.println("Wrong value!! Please use the format: x y and only values 0-2");
                    throw new IOException("Wrong coordinates!");
                    //return;
                }
                if (!NewMove(pl[pln], x, y)) System.out.println("Slot not available!!");
                if(xCount + oCount > 7) {//Check if the game is tied
                    System.out.println("No Winners this Time!!");
                    return;
                }
            } while (!CheckVictory(pl[pln]));
            System.out.println("Player " + pl[pln] + " WINS!!" + " \nTHE END");
        }
    }

    boolean CheckVictory(char pl) {
        // if a player completed a row, column or diagonal, declare end game and the winner
        if (xCount < bdSize && oCount < bdSize) return false;
        int i, col, row , digF = 0, digB = 0;

        for (i = 0; i < bdSize; i++) {
            // Check for slots taken by the player
            row = 0;
            col = 0;
            for (int j = 0; j < bdSize && row < bdSize; j++) {
                if (board[i][j] == pl)
                    row++;
                if (board[j][i] == pl)
                    col++;
                if (row == bdSize || col == bdSize) return true;
            }
        }
        i = 0;
        row = 0;
        col = 2;
        do {
            if (board[i][i] == pl)
                digF++;
            if (board[row++][col--] == pl)
                digB++;
            i++;
            if (digF == bdSize || digB == bdSize) return true;
        } while (i < bdSize);
        return false;
    }
}