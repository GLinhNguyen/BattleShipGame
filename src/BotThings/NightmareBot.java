package BotThings;

import Functions.*;

import java.util.*;
import java.lang.reflect.AccessFlag;

import javax.swing.text.Position;

import Boards.Location;
import Boards.Marker;
import Boards.SelectionGrid;

public class NightmareBot extends Bot {

    private List<Location> shipHits;
    
    private final boolean randomAi = true;
    private final boolean preferMovesFormingLine;
    private boolean maximizedAdjancentHits;
    
    public NightmareBot(SelectionGrid playerGrid, boolean preferMovesFormingLine, boolean maximizedAdjancentHits) {
        super(playerGrid);
        this.preferMovesFormingLine = preferMovesFormingLine;
        this.maximizedAdjancentHits = maximizedAdjancentHits;
        this.shipHits = new ArrayList<>();
        Collections.shuffle(possibleMoves);
    }

    @Override
    public void reset() {
        super.reset();
        shipHits.clear();
        Collections.shuffle(possibleMoves);
    }

    @Override
    public Location selectMove() {
        if (randomAi) System.out.println("\n BEGIN TURN!");
        Location selectMove;

        if (!shipHits.isEmpty()) {
            // If there are ships that have been hit, try to find the next hit.
            if (preferMovesFormingLine) {
                selectMove = getSmarterMove();
            } else {
                selectMove = getSmartMove();
            }
        } else {
            if (maximizedAdjancentHits) {
                // Spam random moves to areas that not attack yet.
                selectMove = findEmptyPosition();
            } else {
                // Random move
                selectMove = possibleMoves.get(0);
            }
        }
        updateShipHits(selectMove);
        possibleMoves.remove(selectMove);
        if (randomAi) {
            System.out.println("Selected move: " + selectMove);
            System.out.println("END TURN!\n");
        }
        return selectMove;
    }

    /**
     * Gets a list of moves adjacent to shipHits and chooses one at random.
     *
     * @return A random move that has a good chance of hitting a ship again.
     */
    public Location getSmartMove() {
       List<Location> possibleMoves = getAdjacentSmartMoves();
       Collections.shuffle(possibleMoves);
         return possibleMoves.getFirst();
    }

    private Location getSmarterMove() {
        List<Location> possibleMoves = getAdjacentSmartMoves();
        for (Location move : possibleMoves){
            if (atLeastTwoHitsInDirection(move, Location.LEFT,0)) {
                return move;
            }
            if (atLeastTwoHitsInDirection(move, Location.RIGHT,0)) {
                return move;
            }
            if (atLeastTwoHitsInDirection(move, Location.UP,0)) {
                return move;
            }
            if (atLeastTwoHitsInDirection(move, Location.DOWN,0)) {
                return move;
            }
        }
        Collections.shuffle(possibleMoves);
        return possibleMoves.getFirst();
    }

    public Location findEmptyPosition(){
        Location location = possibleMoves.getFirst();
        int highestNotAttacked = -1;

        for (int i=0; i < possibleMoves.size(); i++){
            int testCount = getAdjacentEmptyCount(possibleMoves.get(i));
            if (testCount == 4){
                return possibleMoves.get(i);
            } else if (testCount > highestNotAttacked){
                highestNotAttacked = testCount;
                location = possibleMoves.get(i);
            }
        }
        return location;
    }

    public List<Location> getAdjacentSmartMoves(){
        List<Location> result = new ArrayList<>();
        for (Location shipHitPos : shipHits) {
            List<Location> adjacentLocations = getAdjacentCell(shipHitPos);
            for (Location adjacentLocation : adjacentLocations) {
                if (!result.contains(adjacentLocation) && possibleMoves.contains(adjacentLocation)) {
                    result.add(adjacentLocation);
                }
            }
        }
    if(randomAi){
        printLocationList("Ship Hits: ", shipHits);
        printLocationList("Adjacent Smart Moves: ", result);
    }
    return result;
    }


    /**
     * Debug method to print a list of Positions.
     *
     * @param message Debug message to show before the data.
     * @param locationList A list of elements to show in the form [,,,]
     */
    public void printLocationList(String message, List<Location> locationList){
        String result = "[";
        for (int i=0; i<locationList.size(); i++){
            result += locationList.get(i).toString();
            if (i != locationList.size() - 1){
                result += ", ";
            }
        }
        result += "]";
        System.out.println(message+ " " + result);
    }

    /**
     * Creates a list of all adjacent cells around the position excluding any that
     * are off the grid.
     *
     * @param location Position to find adjacent cells around.
     * @return A list of all adjacent positions that are inside the grid space.
     */
    private List<Location> getAdjacentCell(Location location){
        List<Location> result = new ArrayList<>();
        if (location.x != 0) {
            Location left = new Location(location);
            left.add(Location.LEFT);
            result.add(left);
        }
        if (location.x != SelectionGrid.gridXNum - 1) {
            Location right = new Location(location);
            right.add(Location.RIGHT);
            result.add(right);
        }
        if (location.y != 0) {
            Location up = new Location(location);
            up.add(Location.UP);
            result.add(up);
        }
        if (location.y != SelectionGrid.gridYNum - 1) {
            Location down = new Location(location);
            down.add(Location.DOWN);
            result.add(down);
        }
        return result;
    }

    /**
     * Tests if the position hits a ship. Then evaluates if the ship that is hit
     * would be destroyed. If it would be destroyed the data is all cleared for that
     * ship because it is no longer necessary to know about destroyed ships.
     *
     * @param testLocation The position that is being evaluated for hitting a ship.
     */
    public void updateShipHits(Location testLocation){
        Marker marker = playerGrid.getMarkerAtLocation(testLocation);
        if (marker.isShip()){
            shipHits.add(testLocation);
           //Check if this is the last place of the ship
            List<Location> allLocationsOfShip = marker.getAssociatedShip().getOccupiedCoordinates();
            if (randomAi) printLocationList("All of ship found at: ", allLocationsOfShip);
            boolean shipDestroyed =containsAllLocations(allLocationsOfShip, shipHits);
            // If the ship is destroyed, remove all hits.
            if(shipDestroyed){
                for (Location shipLocation : allLocationsOfShip){
                   for(int i =0; i<shipHits.size(); i++){
                       if (shipHits.get(i).equals(shipLocation)){
                           shipHits.remove(i);
                           if(randomAi) System.out.println("Ship destroyed" + shipLocation);
                           break;
                       }
                    }
                }
            }
        }
    }


    /**
     * Tests if all the positions in positionsToSearch are in listToSearchIn.
     *
     * @param locationToSearch List of positions to search all of.
     * @param listLocationToSearch List of positions to search inside.
     * @return True if all the positions in positionsToSearch are in listToSearchIn.
     */
    private boolean containsAllLocations(List<Location> locationToSearch, List<Location> listLocationToSearch){
        for (Location location: locationToSearch) {
            boolean found = false;
            for (Location searchLocation : listLocationToSearch) {
                if (searchLocation.equals(location)) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
            return true;
        }
    /**
     * Counts the number of adjacent cells that have not been marked around the specified position.]
     * @param location The position to count adjacent cells.
     * @return The number of adjacent cells that have not been marked around the position.
     */
    private int getAdjacentEmptyCount(Location location){
            List<Location> adjacentCells = getAdjacentCell(location);
            int count = 0;

            for (Location adjacentCell : adjacentCells){
                if (!playerGrid.getMarkerAtLocation(adjacentCell).isMarked()){
                    count++;
                }
            }
            return count;
        }

    /**
     * Tests if there are two adjacent ship hits in a direction from a test start point.
     *
     * @param start Position to start from (but not test).
     * @param direction Direction to move from the start position.
     * @param steps Number of steps to take in the direction.
     * @return True if there are two adjacent ship hits in the specified direction.
     */
    private boolean atLeastTwoHitsInDirection(Location start, Location direction, int steps){
        if(steps == 2) {
            return true;
        }

        Location testLocation = new Location(start);
        testLocation.add(direction);

        if (!shipHits.contains(testLocation)) {
            return false;
        }

        return atLeastTwoHitsInDirection(testLocation, direction, steps + 1); //Recursive call
    }

    @Override
public void applyMove(Location move) {
        Marker marker = playerGrid.getMarkerAtLocation(move);
        if (marker.isShip()) {
            marker.getAssociatedShip().destroySection();
        }
    }
}

