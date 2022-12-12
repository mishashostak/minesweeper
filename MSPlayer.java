import java.util.Scanner;

/**
 * This class defines the driver of the game Minesweeper
 * 
 * @author Misha Shostak
 * 
 * @version November 29, 2022
 */
public class MSPlayer {
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("\nPlease enter three positive integers \n(the # of rows | the # of columns | the # of mines):");
            int rows = sc.nextInt();
            int columns = sc.nextInt();
            int mines = sc.nextInt();
            System.out.println("\nPLEASE NOTE:\nmines are represented as \"$\"");
            System.out.println("(1, 1) is the top-left-most corner\n" +
                "(length, height) is the bottom-right-most corner" + 
            "\n");
            // Starts the game 
            MinesweeperGame game = new MinesweeperGame(rows, columns, mines);

            while (true) {
                System.out.println("\nPlease select a column (x) and row (y) to examine:");
                // User selects square to interact with 
                int inputColumn = sc.nextInt() - 1;
                int inputRow = sc.nextInt() - 1;

                // y is local variable of the boolean-styled integer returned by playerInteract(int, int) 
                int y = game.playerInteract(inputRow, inputColumn); 

                // Response System: Win, Hit or Miss 
                if (y == 2) { 
                    System.out.println("\nYou have hit a mine!\n");
                    game.getBoard();
                    // Exits after the game is complete 
                    System.exit(0);
                }
                if (y == 1) {
                    System.out.println("\nYou won! Congratulations\n");
                    game.getBoard();
                    // Exits after the game is complete 
                    System.exit(0);
                }
                // Continues if the game has not been completed 
                if (y == 0) {
                    game.getVisibleBoard();
                }
            }
        }
    }
}