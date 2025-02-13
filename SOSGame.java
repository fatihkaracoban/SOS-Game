import java.util.Scanner;

public class SOSGame {
    private static final int BOARD_SIZE = 3;
    private static final char EMPTY_CELL = '-';
    private static final char PLAYER_1_SYMBOL = 'S';
    private static final char PLAYER_2_SYMBOL = 'O';

    private char[][] board;

    public SOSGame() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
    }

    private void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isGameOver() {
        return isBoardFull();
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col] == EMPTY_CELL;
    }

    private void makeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    private boolean isWinningMove(int row, int col, char symbol) {
        // yatay dikey çapraz oyunu kazanan var mı kontrol 
        int count = 0;

        // YATAY
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[row][i] == symbol) {
                count++;
            } else {
                count = 0;
            }
            if (count == 3) return true;
        }

        // DİKEY
        count = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][col] == symbol) {
                count++;
            } else {
                count = 0;
            }
            if (count == 3) return true;
        }

        // ÇAPRAZ 
        if (row == col || row + col == BOARD_SIZE - 1) {
            
            count = 0;
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (board[i][i] == symbol) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == 3) return true;
            }

            count = 0;
            for (int i = 0; i < BOARD_SIZE; i++) {
                if (board[i][BOARD_SIZE - 1 - i] == symbol) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == 3) return true;
            }
        }

        return false;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean player1Turn = true;

        while (!isGameOver()) {
            System.out.println("Current board:");
            printBoard();

            char currentPlayerSymbol = player1Turn ? PLAYER_1_SYMBOL : PLAYER_2_SYMBOL;
            System.out.println("Player " + (player1Turn ? "1" : "2") + ", enter your move (row column):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (isValidMove(row, col)) {
                makeMove(row, col, currentPlayerSymbol);
                if (isWinningMove(row, col, currentPlayerSymbol)) {
                    System.out.println("Player " + (player1Turn ? "1" : "2") + " wins!");
                    printBoard();
                    return;
                }
                player1Turn = !player1Turn; // Switch turn
            } else {
                System.out.println("Invalid move! Please try again.");
            }
        }

        System.out.println("It's a draw!");
        printBoard();
    }

    public static void main(String[] args) {
        SOSGame game = new SOSGame();
        game.play();
    }
}