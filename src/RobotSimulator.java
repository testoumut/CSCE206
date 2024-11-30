/*
* 
*/
import java.io.*;
import java.util.Scanner;

public class RobotSimulator {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);  // Add Scanner for user input

        System.out.println("Welcome to the Robot Simulator");

        // Get file names for the board and commands
        System.out.print("Enter file for the Board: ");
        String boardFile = scanner.nextLine();
        System.out.print("Enter file for the Robot Commands: ");
        String commandsFile = scanner.nextLine();

        // Load the board and robot commands
        try {
            Board boardObj = new Board(boardFile);  // Load the board from the file
            GenericQueue<String> commandQueue = loadCommands(commandsFile);  // Load the robot commands

            boolean runSimulation = true;

            // Run the simulation loop
            while (runSimulation) {
                Robot robot = new Robot();  // Create a new robot at the start of each simulation
                System.out.println("\nSimulation begin");

                // Process each command in the queue
                while (!commandQueue.isEmpty()) {
                    String command = commandQueue.dequeue();  // Get the next command
                    System.out.println("Command: " + command);

                    if (robot.move(command, boardObj)) {  // Move the robot and check for crashes
                        boardObj.displayBoard(robot.getRow(), robot.getCol());
                    } else {
                        System.out.println("CRASH");
                        break;  // Stop simulation if robot crashes
                    }
                }

                // Ask user whether to quit or restart
                System.out.print("\nSimulation end\nQuit? Enter \"true\" to quit or hit enter to run another simulation: ");
                String userChoice = scanner.nextLine();
                if (userChoice.equalsIgnoreCase("true")) {
                    runSimulation = false;  // Quit if user enters "true"
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        scanner.close();  // Close the scanner when done
    }

    private static GenericQueue<String> loadCommands(String filename) throws IOException {
        GenericQueue<String> queue = new GenericQueue<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (isValidCommand(line)) {
                    queue.enqueue(line);  // Enqueue valid commands
                }
            }
        }
        return queue;
    }

    private static boolean isValidCommand(String command) {
        return command.equals("Move Up") || command.equals("Move Down") ||
               command.equals("Move Left") || command.equals("Move Right");
    }
}
