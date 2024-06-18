package Boards;

import java.awt.*;

import Functions.Ship;
public class Marker extends Coordinate {
    // color ship located
    private final Color HIT_COLOUR = new Color(242, 163, 15);
    // color when there are no ship
    private final Color MISS_COLOUR = new Color(97, 75, 242);
    private final int PADDING = 3;
    private boolean showDestroyedMarker; 
    // true maker will paint
    private boolean showMarker;
    private final Color DESTROYED_COLOR = new Color(255, 0, 0);
    // have ship-> hit_color
    // No ship -> missing_color
    private Ship shipAtMarker;
    public Marker(int x, int y, int width, int height) {
        super(x, y, width, height);
        reset();
    }
    //  Resets to no referenced ship, and with the marker not visible.
    public void reset() {
        shipAtMarker = null;
        showMarker = false;
    }

    public void resetForUndo() {
        showMarker = false;
        showDestroyedMarker = false;
    }

    public void resetHitMarker() {
        if (showMarker && !isShip()) {
            showMarker = false;
        }
    }

    public void resetDestroyedMarker() {
        if (showMarker && isShip() && shipAtMarker.isDestroyed()) {
            showMarker = false;
        }
    }

    // If not previously marked it will tell the associated ship that
    //another section has been destroyed. Then mark the marker to make
    //it visible for drawing.
    public void mark() {
        if(!showMarker && isShip()) {
            shipAtMarker.destroySection();
        }
        showMarker = true;
    }

    public void unmark() {
        showMarker = false;
    }
    // get if maker interacted with , return true if maker is visualble
    public boolean isMarked() {
        return showMarker;
    }
    //  Reference to the ship at this location
    public void setAsShip(Ship ship) {
        this.shipAtMarker = ship;
    }
    //Gets if this marker has an associated Functions.Ship.Return true if a ship has been set
    public boolean isShip() {
        return shipAtMarker != null;
    }
    //Gets the associated ship if there is one, otherwise it will be null. Reuturn
    public Ship getAssociatedShip() {
        return shipAtMarker;
    }
    public void paint(Graphics g) {
        if (!showMarker) return;
        if (shipAtMarker != null && shipAtMarker.isDestroyed()) {
            g.setColor(DESTROYED_COLOR);
        } else if (showMarker && !isShip()) {
            g.setColor(MISS_COLOUR);
        } else if (showMarker && isShip()) {
            g.setColor(HIT_COLOUR);
        } else {
            return;
        }
        g.fillRect(location.x + PADDING + 1, location.y + PADDING + 1, width - PADDING * 2, height - PADDING * 2);
    }

    }




