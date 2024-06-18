package MessageStatus;

import Boards.Coordinate;
import Boards.Location;
import java.util.ArrayList;
import java.awt.*;
import java.util.List;

public class StatusPanel extends Coordinate {

    private List<Message> messages;
    private GameState currentState;
    private int destroyedSections;
    private int segments;
    private Location drawPosition;
    private Location gridPosition;
    private Font font = new Font("Arial", Font.BOLD, 12);

    private boolean isSideways;
    private String topString;
    private String bottomString;

    public StatusPanel(Location location, int width, int height) {
        super(location, width, height);
        this.location = location;
        this.messages = new ArrayList<>();
        currentState = GameState.PLACING_SHIPS;
        reset();
        initMessages(); // Initialize messages after reset to avoid recursion
    }

    private void initMessages() {
        messages.add(new Message("Place your ships", "Click W to rotate your ships", GameState.PLACING_SHIPS));
        messages.add(new Message("Your turn", "Click on the grid to fire", GameState.FIRING));
        messages.add(new Message("You win!", "You sunk all the enemy ships", GameState.GAME_WIN));
        messages.add(new Message("You lose", "All your ships have been sunk", GameState.GAME_LOSS));
    }

    public void reset() {
        topString = "Place your ships";
        bottomString = "Click Q to rotate your ships";
    }


    public String getMessage() {
        if (currentState == GameState.PLACING_SHIPS) {
            return messages.get(0).getTopString();
        } else if (currentState == GameState.FIRING) {
            return messages.get(1).getTopString();
        } else if (currentState == GameState.GAME_WIN) {
            return messages.get(2).getTopString();
        } else if (currentState == GameState.GAME_LOSS) {
            return messages.get(3).getTopString();
        }
        return "";
    }

    public void destroySection() {
        destroyedSections++;
    }

    public boolean isDestroyed() {
        return destroyedSections >= segments;
    }

    public void setDrawPosition(Location gridPosition, Location drawPosition) {
        this.drawPosition = drawPosition;
        this.gridPosition = gridPosition;
    }

    public boolean isSideways() {
        return isSideways;
    }

    public int getSegments() {
        return segments;
    }

    public List<Location> getOccupiedCoordinates() {
        List<Location> result = new ArrayList<>();
        if (isSideways) {
            for (int x = 0; x < segments; x++) {
                result.add(new Location(gridPosition.x + x, gridPosition.y));
            }
        } else {
            for (int y = 0; y < segments; y++) {
                result.add(new Location(gridPosition.x, gridPosition.y + y));
            }
        }
        return result;
    }

    public void paint(Graphics g) {
        if (location == null) {
            throw new NullPointerException("Location is null");
        }

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(location.x, location.y, width, height);
        g.setColor(Color.BLACK);
        g.setFont(font);

        if (topString == null || bottomString == null) {
            throw new NullPointerException("String cannot be null");
        }

        int strWidth = g.getFontMetrics().stringWidth(topString);
        g.drawString(topString, location.x + width / 2 - strWidth / 2, location.y + 20);
        strWidth = g.getFontMetrics().stringWidth(bottomString);
        g.drawString(bottomString, location.x + width / 2 - strWidth / 2, location.y + 40);
    }

    public void setBottomString(String string) {
        this.bottomString = string;
    }

    public void setTopString(String string) {
        this.topString = string;
    }

    public void showGameOver(boolean isWin) {
        if (isWin) {
            topString = "Game Over";
            bottomString = "You win!";
        } else {
            topString = "Game Over";
            bottomString = "You lose!";
        }
    }
}
