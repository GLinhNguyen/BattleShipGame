package BotThings;

import Functions.*;

import java.util.ArrayList;
import java.util.List;

public class Bot {

        protected SelectionGrid playerGrid;
        protected List<Location> possibleMoves;

        /**
         * Creates the basic setup for the AI by setting up references to the player's grid,
         * and creates a list of all valid moves.
         *
         * @param playerGrid A reference to the grid controlled by the player for testing attacks.
         */
        public Bot(SelectionGrid playerGrid) {
            this.playerGrid = playerGrid;
            createValidMoveList();
        }

        public Location selectMove() {
            return Location.ZERO;
        }

        public void reset() {
            createValidMoveList();
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

