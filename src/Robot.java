/*
* Written by Umut Yildirim
*/
public class Robot {
    private int row = 0;  // Start position at top-left corner
    private int col = 0;

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean move(String command, Board board) {
        int newRow = row;
        int newCol = col;

        // Move the robot based on the command
        switch (command) {
            case "Move Up":
                newRow = row - 1;
                break;
            case "Move Down":
                newRow = row + 1;
                break;
            case "Move Left":
                newCol = col - 1;
                break;
            case "Move Right":
                newCol = col + 1;
                break;
            default:
                return false;
        }

        // Check for obstacles or bounds
        if (board.isObstacle(newRow, newCol)) {
            return false;  // Crash if there's an obstacle or out of bounds
        }

        // Update the robot's position
        row = newRow;
        col = newCol;
        return true;
    }
}
