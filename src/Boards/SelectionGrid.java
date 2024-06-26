package Boards;
/* Functions.SelectionGrid class:
define the grid for storing ships
define the grid of markers to indicate hit/ miss detection  */

import Game.GamePanel;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Functions.Ship;

public class SelectionGrid extends Coordinate implements LayoutManager {

    // size of each grid cell in pixel
    public static final int cellSize = 35;

    // number of grid cell in x-axis (width) and y-axis (height)
    public static final int gridXNum = 10;
    public static final int gridYNum = 10;

    // Definitions of the number of Ships, and the number of segments that make up
    // each of those ships.

    public static final int[] boatSize = { 5, 4, 3, 3, 2 };

    // A grid of markers to show the hit/miss on attacks on grid.

    private Marker[][] markers = new Marker[gridXNum][gridYNum];

    // list of ship
    private List<Ship> ships;

    // place ship randomly on grid
    private Random rand;

    // Ships are drawn when true.
    private boolean showShips;

    // return true once all the elements in ships have been destroyed.
    private boolean allShipsDestroyed;

    /**
     * Configures the grid to create the default configuration of markers.
     * x coordinate to offset the grid by in pixels.
     * y coordinate to offset the grid by in pixels.
     */
    public SelectionGrid(int x, int y) {
        super(x, y, cellSize * gridXNum, cellSize * gridYNum);
        createMarkerGrid();
        ships = new ArrayList<>();
        rand = new Random();
        showShips = false;
    }

//    public SelectionGrid() {
//        this(0, 0);
//    }


    /**
     * Draws the ships if all ships on this grid are to be shown, or if debug mode
     * is active,
     * or if each individual ship is flagged as having been destroyed.
     * Then draws all markers that should be shown for attacks made so far,
     * and a grid of lines to show where the grid is overlaid.
     *
     * @param g Reference to the Graphics object for rendering.
     */
    public void paint(Graphics g) {
        for (Ship ship : ships) {
            if (showShips || GamePanel.debugModeActive() || ship.isDestroyed()) {
                ship.paint(g);
            }
        }
      
        
        drawGrid(g);
        drawMarkers(g);
     
      
    }
    //Method to test ship
    public void paintShips(Graphics g) {
        for (Ship ship : ships) { // Assume ships is a List of Ship objects
            for (Location location : ship.getOccupiedCoordinates()) {
                g.setColor(Color.YELLOW);
                g.fillRect(location.x * cellSize, location.y * cellSize, cellSize, cellSize);
            }
        }
    }

    // show all the ships if set is true. showShips is true will make all the ships
    // on this grid be visible.
    public void setShowShips(boolean showShips) {
        this.showShips = showShips;
    }

    /**
     * Resets the Functions.SelectionGrid by telling all the markers to reset,
     * removing all ships from the grid, and defaulting back to not show any ships,
     * and a state where no ships have been destroyed.
     */
    public void reset() {
        for (int x = 0; x < gridXNum; x++) {
            for (int y = 0; y < gridYNum; y++) {
                markers[x][y].reset();
            }
        }
        ships.clear();
        showShips = false;
        allShipsDestroyed = false;
    }

  

    // Marks the specified location and then checks if all ships have been destroyed
    public boolean markLocation(Location locToMark) {
        markers[locToMark.x][locToMark.y].mark();

        allShipsDestroyed = true;
        for (Ship ship : ships) {
            if (!ship.isDestroyed()) {
                allShipsDestroyed = false;
                break;
            }
        }
        return markers[locToMark.x][locToMark.y].isShip();
    }


    /**
     * Checks if all ships have been destroyed or not.
     * Return true if all the ships have been destroyed.
     */
    public boolean areAllShipsDestroyed() {
        return allShipsDestroyed;
    }

    // Checks if the specified location is already marked.

    public boolean isLocationMarked(Location locToTest) {
        return markers[locToTest.x][locToTest.y].isMarked();
    }

    /**
     * Gets the marker at the specified location.
     * 
     * @param locToSelect location on the grid to select the marker.
     * @return Returns a reference to the marker at the specified position.
     */
    public Marker getMarkerAtLocation(Location locToSelect) {
        return markers[locToSelect.x][locToSelect.y];
    }

    /**
     * mouse location on the grid
     * 
     * @param mouseX Mouse X coordinate.
     * @param mouseY Mouse Y coordinate.
     * @return Returns (-1,-1) for an invalid location, or return the corresponding
     *         grid location related to the coordinates.
     */
    public Location getLocationWithinGrid(int mouseX, int mouseY) {
        if (!isLocationWithinCoordinate(new Location(mouseX, mouseY)))
            return new Location(-1, -1);

        return new Location((mouseX - location.x) / cellSize, (mouseY - location.y) / cellSize);
    }

    /**
     * Tests if a ship given the specified properties would be valid for placement.
     * checking if the ship fits within the bounds of the grid,
     * and if all the segments would fall on places where a ship does not already
     * sit.
     * This is handled separately depending on whether it is a horizontal or
     * vertical ship.
     *
     * @param gridX    Grid X coordinate.
     * @param gridY    Grid Y coordinate.
     * @param segments The number of cells that tail the coordinate.
     * @param sideways True when ship is horizontal, false when ship is
     *                 vertical.
     * @return True if the ship can be placed with the specified properties.
     */
    public boolean canPlaceShipAt(int gridX, int gridY, int segments, boolean sideways) {
        if (gridX < 0 || gridY < 0)
            return false;

        if (sideways) {

            // when ship is place in horizontal
            if (gridY > gridYNum || gridX + segments > gridXNum) {
                return false;
            }

            for (int x = 0; x < segments; x++) {
                if (markers[gridX + x][gridY].isShip()) {
                    return false;
                }
            }

        } else {

            // when ship is place in vertical
            if (gridY + segments > gridYNum || gridX > gridXNum) {
                return false;
            }

            for (int y = 0; y < segments; y++) {
                if (markers[gridX][gridY + y].isShip()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Draws a grid made up of single pixel black lines.
     * 
     * @param g Reference to the Graphics object for rendering.
     */
    private void drawGrid(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
       
      
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3)); // Set line thickness to 3

       
        // Draw vertical lines
        int y2 = location.y;
        int y1 = location.y + height;
        for (int x = 0; x <= gridXNum; x++)
            g.drawLine(location.x + x * cellSize, y1, location.x + x * cellSize, y2);

        // Draw horizontal lines
        int x2 = location.x;
        int x1 = location.x + width;
        for (int y = 0; y <= gridYNum; y++)
            g.drawLine(x1, location.y + y * cellSize, x2, location.y + y * cellSize);
        
    }

    /**
     * Draws all the markers.
     * The markers will determine individually if it is necessary to draw.
     *
     * @param g Reference to the Graphics object for rendering.
     */
    private void drawMarkers(Graphics g) {
        
        for (int x = 0; x < gridXNum; x++) {
            for (int y = 0; y < gridYNum; y++) {
                markers[x][y].paint(g);
                
               
            }
        }
    }

    // Creates all the marker objects setting their draw positions on the grid to
    // initialise them.

    private void createMarkerGrid() {
        for (int x = 0; x < gridXNum; x++) {

            for (int y = 0; y < gridYNum; y++) {

                markers[x][y] = new Marker(location.x + x * cellSize, location.y + y * cellSize, cellSize,
                        cellSize);
            }
        }
    }

    /**
     * Clears all current ships, and then randomly places all the ships. The ships
     * will not be placed over the top of other ships. This method assumes there is
     * plenty of space to place all the ships regardless of configuration.
     */
    public void populateShips() {
        ships.clear();
        for (int i = 0; i < boatSize.length; i++) {
            boolean sideways = rand.nextBoolean();
            int gridX, gridY;
            do {
                gridX = rand.nextInt(sideways ? gridXNum - boatSize[i] : gridXNum);
                gridY = rand.nextInt(sideways ? gridYNum : gridYNum - boatSize[i]);
            } while (!canPlaceShipAt(gridX, gridY, boatSize[i], sideways));
            placeShip(gridX, gridY, boatSize[i], sideways);
        }
    }

    /**
     * Places a ship on the grid.
     * Indicates to the marker cells that a ship is on top of them to use for
     * placement of other ships, and hit detection.
     *
     * @param gridX    X coordinate on the grid.
     * @param gridY    Y coordinate on the grid.
     * @param segments Number of cells the ship occupies.
     * @param sideways True indicates horizontal, or false indicates vertical.
     */
    public void placeShip(int gridX, int gridY, int segments, boolean sideways) {
        placeShip(new Ship(new Location(gridX, gridY),
                new Location(location.x + gridX * cellSize, location.y + gridY * cellSize),
                segments, sideways), gridX, gridY);
    }

    /**
     * Places a ship on the grid.
     * Indicates to the marker cells that a ship is Places a ship on the grid with
     * the specified properties. Assumes checks have
     * already been
     * made to verify the ship can be placed there. Indicates to the marker cells
     * that a ship is
     * on top of them to use for placement of other ships, and hit detection.
     * 
     * @param ship  The ship to place on the grid with already configured
     *              properties.
     * @param gridX X coordinate on the grid.
     * @param gridY Y coordinate on the grid.
     */
    public void placeShip(Ship ship, int gridX, int gridY) {
        ships.add(ship);
        if (ship.isSideways()) { // If the ship is horizontal
            for (int x = 0; x < ship.getSegments(); x++) {
                markers[gridX + x][gridY].setAsShip(ships.get(ships.size() - 1));
            }
        } else { // If the ship is vertical
            for (int y = 0; y < ship.getSegments(); y++) {
                markers[gridX][gridY + y].setAsShip(ships.get(ships.size() - 1));
            }
        }
    }
    public void unmarkLocation(Location locToMark) {
        markers[locToMark.x][locToMark.y].unmark();
        // Recheck the destruction status if needed
        allShipsDestroyed = true;
        for (Ship ship : ships) {
            if (ship.isDestroyed()) {
                allShipsDestroyed = false;
            }
           
        }
    }

    public void removeShip(Location location) {
        Marker marker = markers[location.x][location.y];
        if (marker.isMarked()) {
            marker.unmark();
        }//remove marker

        Ship shipToRemove = marker.getAssociatedShip();
        if (shipToRemove != null) {
            resetShipMarkers1(location, shipToRemove);
            ships.remove(shipToRemove);
        }

        // Recheck all ships destruction status
        allShipsDestroyed = ships.isEmpty() || ships.stream().allMatch(Ship::isDestroyed);
    }

    public void removeDestroyedShip(Location location) {
        Marker marker = markers[location.x][location.y];
        
        if (marker.isMarked()) {
            marker.unmark();
        }
        
        Ship shipToRemove = marker.getAssociatedShip();
        if (shipToRemove != null) {
            resetShipMarkers(location,shipToRemove);
            ships.remove(shipToRemove);
        }

        // Recheck all ships destruction status
        allShipsDestroyed = ships.isEmpty() || ships.stream().allMatch(Ship::isDestroyed);
    }

    private void resetShipMarkers(Location location,Ship ship) {
        for (int x = 0; x < gridXNum; x++) {
            for (int y = 0; y < gridYNum; y++) {
                Marker marker = markers[x][y];
                if (marker.getAssociatedShip() == ship) {
                    marker.resetHitMarker();
                    marker.resetDestroyedMarker();
                }
            }
        }
    }



    private void resetShipMarkers1(Location location, Ship ship) {
        int shipSize = ship.getSegments();
        boolean isSideways = ship.isSideways();
        for (int i = 0; i < shipSize; i++) {
            int offsetX = isSideways ? i : 0;
            int offsetY = isSideways ? 0 : i;
            markers[location.x + offsetX][location.y + offsetY].reset();
        }
    }
    
    public void removeShipForRedo(Location location){
        Ship shipToRemove = null;
        for (Ship ship : ships) {
            if (ship.getGridPosition().equals(location)) {
                shipToRemove = ship;
                break;
            }
        }
    
        if (shipToRemove != null) {
            ships.remove(shipToRemove);
            
            int shipSize = shipToRemove.getSegments();
            boolean isSideways = shipToRemove.isSideways();
            
            // Reset markers for the ship's segments at the current location
            for (int i = 0; i < shipSize; i++) {
                int offsetX = isSideways ? i : 0;
                int offsetY = isSideways ? 0 : i;
                markers[location.x + offsetX][location.y + offsetY].reset();
            }
    
            // If the ship is sideways, reset additional markers horizontally
            if (isSideways) {
                for (int x = 0; x < shipSize; x++) {
                    markers[location.x + x][location.y].reset();
                }
            } else { // Otherwise, reset markers vertically
                for (int y = 0; y < shipSize; y++) {
                    markers[location.x][location.y + y].reset();
                }
            }
        }
    }
    


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    

    @Override
    public void addLayoutComponent(String name, Component comp) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addLayoutComponent'");
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeLayoutComponent'");
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'preferredLayoutSize'");
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'minimumLayoutSize'");
    }

    @Override
    public void layoutContainer(Container parent) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'layoutContainer'");
    }

    public void placeShip(Location location, boolean sideways) {

    }


}