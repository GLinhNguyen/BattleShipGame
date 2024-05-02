
import java.util.Scanner;

public class Player {
    private Scanner scanner;

    public Player() {
        this.scanner = new Scanner(System.in);
    }

    public int[] selectCoordinates() {
        System.out.println("Enter coordinates to attack (format: x y):");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        return new int[]{x, y};
    }

    public boolean isValidCoordinate(int[] coordinates) {
        // Assuming the board size is 10x10
        return coordinates[0] >= 0 && coordinates[0] < 10 && coordinates[1] >= 0 && coordinates[1] < 10;
    }

    public void placeShip() {
        System.out.println("Enter coordinates to place ship (format: x y):");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        if (isValidCoordinate(new int[]{x, y})) {
            // Call the method in the game logic to place the ship
            // game.placeShip(x, y);
        } else {
            System.out.println("Invalid coordinates. Please try again.");
            placeShip();
        }
    }
}

