import java.util.Scanner;

/**
 * This class defines the driver of the game Minesweeper
 * 
 * @author Misha Shostak
 * 
 * @version November 29, 2022
 */
public class MinesweeperPlayer {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Please enter three positive constants \n(the # of rows | the # of columns | the # of mines):\n ");
            int rows = sc.nextInt();
            int columns = sc.nextInt();
            int mines = sc.nextInt();
            System.out.println("Please note that mines are represented by the # 9 while -1 refers to 'hidden' spaces\n");

            /* starts the game */
            MinesweeperGame game = new MinesweeperGame(rows, columns, mines);

            int x = 0;
            while (x != -1) {
                /* user selects square to interact with */ 
                System.out.println("\nPlease select a row | column to examine (zero-based indexing please): \n");
                int inputRow = sc.nextInt();
                int inputColumn = sc.nextInt();
                int y = game.playerInteract(inputRow, inputColumn); // User input to select square

                /* Response System: Win, Hit or Miss */
                if (y == 2) { 
                    System.out.println("\nYou have hit a mine!\n");
                    game.getBoard();
                    /* exits after the game is complete */
                    System.exit(0);
                }
                if (y == 1) {
                    System.out.println("\nYou won! Congratulations\n");
                    game.getBoard();
                    /* exits after the game is complete */
                    System.exit(0);
                }
                if (y == 0) {
                    System.out.println("\nHere is the board for reference. Play your next move wisely.\n");
                    /* continues if the game has not been completed */
                    game.getVisibleBoard();
                }
            }
        }
    }
}