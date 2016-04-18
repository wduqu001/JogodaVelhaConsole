package TicTacToeConsole;

public class Draw {
/*Class responsible for drawing the tic tac toe board*/
    private char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}};
    private int bdSize = 3;

    public int getBdSize() {
        return bdSize;
    }

    public void setBdSize(int bdSize) {
        this.bdSize = bdSize;
    }

    public char[][] getBoard() {
        return board;
    }

     void setBoard(char[][] board) {
        this.board = board;
    }

     void output() {
        for (char[] i : board) {
            System.out.println("\n-------------");
            for (char c : i) {
                System.out.print("| " + c + " ");
            }
            System.out.printf("|");
        }
        System.out.println("\n-------------");
    }
}
