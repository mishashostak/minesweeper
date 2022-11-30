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
    private String board[][];
    private String visBoard[][];

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
        board = this.createBoardSpace();
        board = populateBoard(board);
        visBoard = this.createBoardDash();
        printBoard(visBoard);
    }

    /**
     * This method determines the result of an interaction with the board 
     * and changes all components accordingly
     * 
     * @param inputRow The "y" coordinate of the selected square
     * @param inputColumn The "x" coordinate of the selected square
     * 
     * @return int - The boolean-styled integer value of the game status
     */
    public int playerInteract(int inputRow, int inputColumn) {
        /* Check for no "$" (indicates a bomb) */
        if (board[inputRow][inputColumn] == "$") {
            return 2;
        }
        /* Check: "-" indicates it is blank */
        else if (board[inputRow][inputColumn] == " ") {
            blankAdjacents(inputRow, inputColumn);
        }
        /* Check: " " indicates it has already been revealed and isn't numbered */
        else if (board[inputRow][inputColumn] == " ") {
            blankAdjacents(inputRow, inputColumn);
        }
        /* Return value of the numbered cell */
        else {
            visBoard[inputRow][inputColumn] = board[inputRow][inputColumn];
        }
        /* Checks for a win, if they haven't won yet, then they need to keep playing */
        for (int i = 0; i< rows; i++) { 
            for (int j = 0; j < columns; j++) {
                /* Looks through each coordinate that isn't a bomb */
                if (board[i][j] != "$") {
                    /* Checks for a win, if they haven't won yet, then they need to keep playing */
                    if (visBoard[i][j] == board[i][j]) {
                        visBoard[i][j] = board[i][j];
                    }
                    /* No win will sustain gameplay, naturally */
                    else {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }

    /**
     * Helper method to reveal blank cells and all cells adjacent
     * 
     * @param y The row coordinate 
     * @param x The column coordinate 
     */
    private void blankAdjacents(int y, int x){
        /* Check for bounds */
        if (x < 0 | x > columns-1 | y < 0 | y > rows-1) {
            return;
        }
        /* Check if cell has already been processed to avoid throwing StackOverflowError */
        else if(visBoard[y][x] == " ") return;

        /* Stops if the cell is the bomb */
        else if(board[y][x] == "$") return;

        /* Check: "-" indicates it is blank */
        else if(board[y][x] == " ") {
            visBoard[y][x] = board[y][x];
            /* Recursion */
            blankAdjacents(y, x+1); /*up*/
            blankAdjacents(y, x-1);  /*down*/
            blankAdjacents(y+1, x);  /*left*/
            blankAdjacents(y-1, x);  /*right*/
            blankAdjacents(y-1, x+1);  /*up-left*/
            blankAdjacents(y+1, x+1);  /*up-right*/
            blankAdjacents(y-1, x-1);  /*down-left*/
            blankAdjacents(y+1, x-1);  /*down-right*/
            return;
        }
        /* Reveals cell if the cell is numbered */
        else {
            visBoard[y][x] = board[y][x];
            return;
        }
    }

    /**
     * Accessor to call the printBoard(String[][]) method with
     * the visBoard instance variable (i.e. what the player can see)
     */
    public void getVisibleBoard() {
        printBoard(visBoard);
    }

    /**
     * Accessor to call the printBoard(String[][]) method with
     * the board instance variable (master board with all answers)
     */
    public void getBoard() {
        printBoard(board);
    }

    /**
     * Helper method to instantiate the Minesweeper Board
     * 
     * @return String[][] - The initial board made of rows and columns values
     */
    private String[][] createBoardDash(){
        String[][] temp = new String[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                temp[i][j] = "-";
            }
        }
        return temp;
    }

    /**
     * Helper Method to instantiate the Minesweeper Board
     * 
     * @return String[][] - The initial board made of rows and columns values
     */
    private String[][] createBoardSpace(){
        String[][] temp = new String[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                temp[i][j] = " ";
            }
        }
        return temp;
    }

    /**
     * Helper Method to randomly place mines on the Minesweeper Board
     * 
     * @param inputBoard[][] The initial board made of rows and columns values
     * 
     * @return String[][] - The initial board that will be used to play
     */
    private String[][] populateBoard(String inputBoard[][]) {
        /* This section assigns each space of number based on the surrounding mines. */
        while (mines != 0) {
            int a = (int)(Math.random()*rows);
            int b = (int)(Math.random()*columns);
            if (inputBoard[a][b] == " ") {
                inputBoard[a][b] = "$";
                mines -= 1;
            }
        }

        /* Assigns each space of number based on the surrounding mines */
        for(int i = 0; i < rows; i++) { 
            for (int j = 0; j < columns; j++) {
                if (inputBoard[i][j] != "$") {
                    int tempMines = 0;
                    if ((j - 1) != -1) {
                        if (inputBoard[i][j - 1] == "$") {
                            tempMines += 1;
                        }
                    }
                    if ((i - 1) != -1) {
                        if (inputBoard[i - 1][j] == "$") {
                            tempMines += 1;
                        }
                    }
                    if ((i - 1) != -1 && (j - 1) != -1) {
                        if (inputBoard[i - 1][j - 1] == "$") {
                            tempMines += 1;
                        }
                    }
                    if ((i - 1) != -1 && (j + 1) != columns) {
                        if (inputBoard[i - 1][j + 1] == "$") {
                            tempMines += 1;
                        }
                    }
                    if ((i + 1) != rows) {
                        if (inputBoard[i + 1][j] == "$") {
                            tempMines += 1;
                        }
                    }
                    if ((j + 1) != columns) {
                        if (inputBoard[i][j + 1] == "$") {
                            tempMines += 1;
                        }
                    }
                    if ((i + 1) != rows && (j + 1) != columns) {
                        if (inputBoard[i + 1][j + 1] == "$") {
                            tempMines += 1;
                        }
                    }
                    if ((i + 1) != rows && (j - 1) != -1) {
                        if (inputBoard[i + 1][j - 1] == "$") {
                            tempMines += 1;
                        }
                    }
                    /* Keep blank cells as "-" */
                    if (tempMines != 0) {
                        inputBoard[i][j] = Integer.toString(tempMines);
                    }
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
    private void printBoard(String inputBoard[][]) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.printf("|%1s",inputBoard[i][j]);
            }
            System.out.printf("%s%n","|");
        }
    }
    
    /**
     * This is a standard toString() method 
     * 
     * @return String - The String representation of the current object
     */
    public String toString() {
        return "rows=" + rows + "\ncols=" + columns + "\nmines=" + mines;
    }
}