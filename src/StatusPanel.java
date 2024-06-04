import Functions.Location;
import Game.GameState;

import java.util.List;
import java.util.ArrayList;

//GameStatus.java is the interface to show messages to players
public class StatusPanel extends Location {

    private List<Message> messages;
    private GameState currentState;
    private int destroyedSections;
    private int segments;
    private Location drawPosition;
    private Location gridPosition;
    private boolean isSideways;

    public StatusPanel(int x, int y) {
        super(x, y);
        initMessages();
        currentState = GameState.PLACING_SHIPS;
    }
    public void initMessages() {
        messages = (new ArrayList<>());
        messages.add(new Message("Place your ships", "Click on the grid to place your ships", GameState.PLACING_SHIPS));
        messages.add(new Message("Your turn", "Click on the grid to fire", GameState.PLAYING));
        messages.add(new Message("You win!", "You sunk all the enemy ships", GameState.GAME_WIN));
        messages.add(new Message("You lose", "All your ships have been sunk", GameState.GAME_LOSS));
    }


    public String getMessage() {
        if( currentState == GameState.PLACING_SHIPS) {
            return messages.get(0).getMessage();
        } else if(currentState == GameState.PLAYING) {
            return messages.get(1).getMessage();
        } else if(currentState == GameState.GAME_WIN) {
            return messages.get(2).getMessage();
        } else if(currentState == GameState.GAME_LOSS) {
            return messages.get(3).getMessage();
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
        if(isSideways) {
            for(int x = 0; x < segments; x++) {
                result.add(new Location(gridPosition.x+x, gridPosition.y));
            }
        } else {
            for(int y = 0; y < segments; y++) {
                result.add(new Location(gridPosition.x, gridPosition.y+y));
            }
        }
        return result;
    }
}
