package BotThings;

import Functions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Boards.Location;
import Boards.SelectionGrid;

public class Bot {

    protected SelectionGrid playerGrid;
    protected List<Location> possibleMoves;
    protected Bot opponent;

    public Bot(SelectionGrid playerGrid) {
        this.playerGrid = playerGrid;
        createValidMoveList();
    }

    public void setOpponent(Bot opponent) { // Add this method
        this.opponent = opponent;
    }

    public void takeTurn() {
        Location move = selectMove();
        opponent.applyMove(move);
    }

    public void applyMove(Location move) { // Add this method
        // Implement the logic to apply a move to this bot's grid
    }

    public Location selectMove() {
        return Location.ZERO;
    }
    public Location getNextMove() {
        if (possibleMoves.isEmpty()) {
            createValidMoveList(); // Reset the possible moves if empty
        }
        Random rand = new Random();
        int index = rand.nextInt(possibleMoves.size());
        Location selectedMove = possibleMoves.get(index);
        possibleMoves.remove(index);
        return selectedMove;
    }

    public void reset() {
        createValidMoveList();
    }
    public void placeShips() {
        Random rand = new Random();
        int numberOfShips = 5;
        int[] shipSizes = {2, 3, 3, 4, 5};

        for (int i = 0; i < numberOfShips; i++) {
            int segments = shipSizes[i];
            boolean placed = false;

            while (!placed) {
                int gridX = rand.nextInt(SelectionGrid.gridXNum);
                int gridY = rand.nextInt(SelectionGrid.gridYNum);
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
        for(int x = 0; x < SelectionGrid.gridXNum; x++) {
            for(int y = 0; y < SelectionGrid.gridYNum; y++) {
                possibleMoves.add(new Location(x,y));
            }
        }
    }
}