/**
 * This class defines the logic of the game Minesweeper
 * (Using 2-D Arrays)
 * 
 * @author Misha Shostak
 * 
 * @version November 29, 2022
 */
public class MinesweeperGame {
    /* Instance variables to reset game characteristics when new Object is made */
    private int rows;
    private int columns;
    private int mines;
    private int board[][];
    private int visibleBoard[][];

    /**
     * Parameterized constructor to initialize all instance variables
     * with given integer values
     * 
     * @param inputRows The initial number of rows
     * @param inputColumns The initial number of columns
     * @param inputMines The initial number of amount of mines
     */
    public MinesweeperGame(int inputRows, int inputColumns, int inputMines) {
        rows = inputRows;
        columns = inputColumns;
        mines = inputMines;
        visibleBoard = new int[rows][columns];
        board = this.createBoard();
        board = populateBoard(board);
        visibleBoard();
    }

    /**
     * This method determines the result of an interaction with the board 
     * and changes all components appropriately
     * 
     * @param inputRow The "y" coordinate of the selected square
     * @param inputColumn The "x" coordinate of the selected square
     * 
     * @return int - The boolean-based integer value of the game status
     */
    public int playerInteract(int inputRow, int inputColumn) {
        if (board[inputRow][inputColumn] == 9) {
            return 2;
        }
        else {
            visibleBoard[inputRow][inputColumn] = board[inputRow][inputColumn];
        }
        /* Checks for a win, if they haven't won yet, then they need to keep playing */
        for (int i = 0; i< rows; i++) { 
            for (int j = 0; j < columns; j++) {
                if (board[i][j] != 9) {
                    if (visibleBoard[i][j] == board[i][j]) {
                        visibleBoard[i][j] = visibleBoard[i][j];
                    }
                    else {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }

    /**
     * Accessor to call the printBoard(int[][]) method with
     * the visibleBoard instance variable (i.e. what the player can see)
     */
    public void getVisibleBoard() {
        printBoard(visibleBoard);
    }

    /**
     * Accessor to call the printBoard(int[][]) method with
     * the board instance variable (master board with all answers)
     */
    public void getBoard() {
        printBoard(board);
    }

    /**
     * Helper Method to instantiate the Minesweeper Board
     * 
     * @return int[][] - The initial board made of rows and columns values
     */
    private int[][] createBoard(){
        int temp[][] = new int[rows][columns];
        return temp;
    }

    /**
     * Helper Method to randomly place mines on the Minesweeper Board
     * 
     * @param inputBoard[][] The initial board made of rows and columns values
     * 
     * @return int[][] - The initial board that will be used to play
     */
    private int[][] populateBoard(int inputBoard[][]) {
        while (mines != 0) {
            int a = (int)(Math.random()*rows);
            int b = (int)(Math.random()*columns);
            if (inputBoard[a][b] == 0) {
                inputBoard[a][b] = 9;
                mines -= 1;
            }
        }

        /* This section assigns each space of number based on the surrounding mines. */
        for(int i = 0; i < rows; i++) { 
            for (int j = 0; j < columns; j++) {
                if (inputBoard[i][j] != 9) {
                    int tempMines = 0;
                    if ((j - 1) != -1) {
                        if (inputBoard[i][j - 1] == 9) {
                            tempMines += 1;
                        }
                    }
                    if ((i - 1) != -1) {
                        if (inputBoard[i - 1][j] == 9) {
                            tempMines += 1;
                        }
                    }
                    if ((i - 1) != -1 && (j - 1) != -1) {
                        if (inputBoard[i - 1][j - 1] == 9) {
                            tempMines += 1;
                        }
                    }
                    if ((i - 1) != -1 && (j + 1) != columns) {
                        if (inputBoard[i - 1][j + 1] == 9) {
                            tempMines += 1;
                        }
                    }
                    if ((i + 1) != rows) {
                        if (inputBoard[i + 1][j] == 9) {
                            tempMines += 1;
                        }
                    }
                    if ((j + 1) != columns) {
                        if (inputBoard[i][j + 1] == 9) {
                            tempMines += 1;
                        }
                    }
                    if ((i + 1) != rows && (j + 1) != columns) {
                        if (inputBoard[i + 1][j + 1] == 9) {
                            tempMines += 1;
                        }
                    }
                    if ((i + 1) != rows && (j - 1) != -1) {
                        if (inputBoard[i + 1][j - 1] == 9) {
                            tempMines += 1;
                        }
                    }
                    inputBoard[i][j] = tempMines;
                }
            }
        }
        return (inputBoard);
    }

    /**
     * Converts the 2-D Array into a string and 
     * prints it in a neat matrix
     * 
     * @param inputBoard[][] The board that is to be printed
     */
    private void printBoard(int inputBoard[][]) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.printf("|%2d",inputBoard[i][j]);
            }
            System.out.printf("%s%n","|");
        }
    }

    /**
     * Generates a blank "visible board"
     */
    private void visibleBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                visibleBoard[i][j] = -1;
            }
        }
        printBoard(visibleBoard);
    }
}