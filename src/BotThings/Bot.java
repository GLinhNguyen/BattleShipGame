package BotThings;

import Functions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot {

    protected SelectionGrid playerGrid;
    protected List<Location> possibleMoves;
    protected Bot opponent; // Add this line

    public Bot(SelectionGrid playerGrid) {
        this.playerGrid = playerGrid;
        createValidMoveList();
    }

    public void setOpponent(Bot opponent) { // Add this method
        this.opponent = opponent;
    }

    public void takeTurn() { // Add this method
        Location move = selectMove();
        opponent.applyMove(move);
    }

    public void applyMove(Location move) { // Add this method
        // Implement the logic to apply a move to this bot's grid
    }

    public Location selectMove() {
        return Location.ZERO;
    }

    public void reset() {
        createValidMoveList();
    }
    public void placeShips() {
        Random rand = new Random();
        int numberOfShips = 5; // Change this to the number of ships you want to place
        int[] shipSizes = {2, 3, 3, 4, 5}; // Change this to the sizes of the ships you want to place

        for (int i = 0; i < numberOfShips; i++) {
            int segments = shipSizes[i];
            boolean placed = false;

            while (!placed) {
                int gridX = rand.nextInt(SelectionGrid.gridWidth);
                int gridY = rand.nextInt(SelectionGrid.gridHeight);
                boolean sideways = rand.nextBoolean();

                if (playerGrid.canPlaceShipAt(gridX, gridY, segments, sideways)) {
                    playerGrid.placeShip(gridX, gridY, segments, sideways);
                    placed = true;
                }
            }
        }
    }

    private void createValidMoveList() {
        possibleMoves = new ArrayList<>();
        for(int x = 0; x < SelectionGrid.gridWidth; x++) {
            for(int y = 0; y < SelectionGrid.gridHeight; y++) {
                possibleMoves.add(new Location(x,y));
            }
        }
    }
}