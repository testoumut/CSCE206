/*
* Written by Umut Yildirim
*/
import java.io.*;

public class Board {
    private char[][] board;

    public Board(String filename) throws IOException {
        // Initialize board from file
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) {
                    if (board == null) {
                        board = new char[10][10];  // Adjust size based on the actual board size
                    }
                    board[row][col] = line.charAt(col);
                }
                row++;
            }
        }
    }

    public void displayBoard(int robotRow, int robotCol) {
        // Display the board with the robot's current position
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (row == robotRow && col == robotCol) {
                    System.out.print("O");  // Robot's position
                } else {
                    System.out.print(board[row][col]);
                }
            }
            System.out.println();
        }
    }

    public boolean isObstacle(int row, int col) {
        // Check if the robot is trying to move into an obstacle or out of bounds
        return row < 0 || row >= board.length || col < 0 || col >= board[row].length || board[row][col] == 'X';
    }
}
