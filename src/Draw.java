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

    public void setBdSize(int newSize) {
        this.bdSize = newSize;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] newBoard) {
        this.board = newBoard;
    }

    //TODO: check if the function bellow is being used
    public void output() {
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
