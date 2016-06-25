/***
 * If the player has completed a row, collum or diagonal, declare end of the game and winner
 ***/
//TODO: improve readability using better names for fields and methods
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Movement {

    //Board Size
    private final Draw dr = new Draw();
    private final int bdSize = dr.getBdSize();
    private int oCount, xCount;
    //TODO: change this to board[][]
    private char[][] board = dr.getBoard();

    public int getxCount() {
        return xCount;
    }

    public int getoCount() {
        return oCount;
    }

    private boolean slotAvailable(int i, int j) {
        //Checks if slot is available, if it's not, it returns false
        return board[i][j] == ' ';
    }

    private boolean newMove(char player, int i, int j) {
        //Adds the new mov. to the board the increases the counter(X || O)
        boolean ret = false;
        if (slotAvailable(i, j)) {
            switch (player) {
                case 'X':
                    xCount++;
                    break;
                case 'O':
                    oCount++;
                    break;
                default: //do nothing
                    break;
            }
            board[i][j] = player;
            dr.setBoard(board);
            dr.output();
            ret = true;
        }
        return ret;
    }

    public void getMoves(char[] pl, char mode) throws IOException {
        //Receives the players input and adds to the board...
        try (Scanner in = new Scanner(System.in)) {
            //TODO: explain the values of the field declared in the code and see if Its necessary to define pln = 1
            int pln = 1, x, y; //Player number
            Random rand = new Random();
            do {
                //TODO: See if can make the line bellow shorter
                pln = (pln == 1 ? 0 : 1); //swap the player
                System.out.println("Player " + pl[pln] + " please enter coordinates...(x y)");
                if (mode == 'D') {
                    //Random Movements...
                    System.out.println("Demonstration Mode...");
                    x = rand.nextInt(3);
                    y = rand.nextInt(3);
                } else {
                    x = in.nextInt();
                    y = in.nextInt();
                }
                if (x >= bdSize || y >= bdSize) {
                    System.out.println("Wrong value!! Please use the format: x y and only values 0-2");
                    throw new IOException("Wrong coordinates!");
                    //return;
                }
                if (!newMove(pl[pln], x, y)) System.out.println("Slot not available!!");
                if (xCount + oCount > 7) {//Check if the game is tied
                    System.out.println("No Winners this Time!!");
                    return;
                }
            } while (!checkVictory(pl[pln]));
            System.out.println("Player " + pl[pln] + " WINS!!" + " \nTHE END");
        }
    }

    private boolean checkVictory(final char pl) {
        // if a player completed a row, column or diagonal, declare end game and the winner
        if (xCount < bdSize && oCount < bdSize) return false;
        int i, col, row, digF = 0, digB = 0;
        //TODO: improve this method making it simpler and faster, try changing the way it conts the diagonals
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