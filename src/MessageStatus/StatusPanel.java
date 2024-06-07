// package MessageStatus;

// import Functions.Coordinate;
// import Functions.Location;
// import Game.GameState;
// import java.util.List;

// import javax.swing.text.Position;

// import java.awt.*;
// import java.util.ArrayList;
// ;
// //GameStatus.java is the interface to show messages to players
// public class StatusPanel extends Location {

//     private List<Message> messages;
//     private GameState currentState;
//     private int destroyedSections;
//     private int segments;
//     private Location drawPosition;
//     private Location gridPosition;
//     protected Location location;
//     public Coordinate coordinate;
//     private Font font = new Font("Arial", Font.BOLD, 12);
   
//     private boolean isSideways;
//     private String topString;
//     private String bottomString;
//     private String placingShipLine1 = "Place your ships";
//     private String placingShipLine2 = "Tap Space to rotate your ships";

//     public StatusPanel(Location location, int width, int height) {
//         super(location, width, height);
//         if (location == null) {
//             throw new IllegalArgumentException("Location cannot be null");
//         }
//         this.location = location;

//         coordinate = new Coordinate(width, height, x, y);
//         // reset();
//     }
//     public StatusPanel(int x, int y) {
//         super(x, y);
//         initMessages();
//         currentState = GameState.PLACING_SHIPS;
//     }
//     // public StatusPanel(Location location, int width, int i) {
//     //     //TODO Auto-generated constructor stub
//     // }
//     public void initMessages() {
//         messages = (new ArrayList<>());
//         messages.add(new Message("Place your ships", "Click on the grid to place your ships", GameState.PLACING_SHIPS));
//         messages.add(new Message("Your turn", "Click on the grid to fire", GameState.PLAYING));
//         messages.add(new Message("You win!", "You sunk all the enemy ships", GameState.GAME_WIN));
//         messages.add(new Message("You lose", "All your ships have been sunk", GameState.GAME_LOSS));
//     }


//     public String getMessage() {
//         if( currentState == GameState.PLACING_SHIPS) {
//             return messages.get(0).getMessage();
//         } else if(currentState == GameState.PLAYING) {
//             return messages.get(1).getMessage();
//         } else if(currentState == GameState.GAME_WIN) {
//             return messages.get(2).getMessage();
//         } else if(currentState == GameState.GAME_LOSS) {
//             return messages.get(3).getMessage();
//         }
//         return "";
//     }
//     public void destroySection() {
//         destroyedSections++;
//     }

//     public boolean isDestroyed() {
//         return destroyedSections >= segments;
//     }

//     public void setDrawPosition(Location gridPosition, Location drawPosition) {
//         this.drawPosition = drawPosition;
//         this.gridPosition = gridPosition;
//     }

//     public boolean isSideways() {
//         return isSideways;
//     }

//     public int getSegments() {
//         return segments;
//     }
//     public void reset() {
//         topString = placingShipLine1;
//         bottomString = placingShipLine2;
//     }
//     public List<Location> getOccupiedCoordinates() {
//         List<Location> result = new ArrayList<>();
//         if(isSideways) {
//             for(int x = 0; x < segments; x++) {
//                 result.add(new Location(gridPosition.x+x, gridPosition.y));
//             }
//         } else {
//             for(int y = 0; y < segments; y++) {
//                 result.add(new Location(gridPosition.x, gridPosition.y+y));
//             }
//         }
//         return result;
//     }
//     public void paint(Graphics g) {
//         if(location == null) {
//             throw new NullPointerException("Location is null");
//         }
//         g.setColor(Color.LIGHT_GRAY);
//         g.fillRect(location.x, location.y, coordinate.getXCoor(), coordinate.getYCoor());
//         g.setColor(Color.BLACK);
//         g.setFont(font);
//         int strWidth = g.getFontMetrics().stringWidth(topString);
//         g.drawString(topString, location.x+coordinate.getXCoor()/2-strWidth/2, location.y+20);
//         strWidth = g.getFontMetrics().stringWidth(bottomString);
//         g.drawString(bottomString, location.x+coordinate.getXCoor()/2-strWidth/2, location.y+40);
//     }
//     public void setBottomString(String string) {
//         this.bottomString = string;
//     }
//     public void setTopString(String string) {
//         this.topString = string;
    
//     }
//     public void showGameOver(boolean b) {
//         if (b) {
//             topString = "Game Over"; // Set the top string
//             bottomString = "You win!"; // Set the bottom string
//         } else {
//             topString = "Game Over"; // Set the top string
//             bottomString = "You lose!"; // Set the bottom string
//         }
// }
// }
package MessageStatus;

import Functions.Coordinate;
import Functions.Location;
import Game.GameState;
import java.util.List;
import java.util.ArrayList;
import javax.swing.text.Position;
import java.awt.*;

public class StatusPanel extends Location {

    private List<Message> messages;
    private GameState currentState;
    private int destroyedSections;
    private int segments;
    private Location drawPosition;
    private Location gridPosition;
    protected Location location;
    public Coordinate coordinate;
    private Font font = new Font("Arial", Font.BOLD, 12);

    private boolean isSideways;
    protected String topString;
    protected String bottomString;
    protected final String placingShipLine1 = "Place your ships";
    protected final String placingShipLine2 = "Tap Space to rotate your ships";

    public StatusPanel(Location location, int width, int height) {
        super(location, width, height);
        this.location = location != null ? location : new Location(0, 0); // Ensure location is not null
        coordinate = new Coordinate(width, height, x, y);
        initMessages();
        topString = placingShipLine1; // Set the top string
        bottomString = placingShipLine2; // Set the bottom string
        currentState = GameState.PLACING_SHIPS;
    }

    public void initMessages() {
        messages = new ArrayList<>();
        messages.add(new Message("Place your ships", "Click on the grid to place your ships", GameState.PLACING_SHIPS));
        messages.add(new Message("Your turn", "Click on the grid to fire", GameState.PLAYING));
        messages.add(new Message("You win!", "You sunk all the enemy ships", GameState.GAME_WIN));
        messages.add(new Message("You lose", "All your ships have been sunk", GameState.GAME_LOSS));
    }

    public String getMessage() {
        if (currentState == GameState.PLACING_SHIPS) {
            return messages.get(0).getMessage();
        } else if (currentState == GameState.PLAYING) {
            return messages.get(1).getMessage();
        } else if (currentState == GameState.GAME_WIN) {
            return messages.get(2).getMessage();
        } else if (currentState == GameState.GAME_LOSS) {
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

    public void reset() {
        topString = placingShipLine1;
        bottomString = placingShipLine2;
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
            // Handle gracefully if location is null
            throw new NullPointerException("Location is null");
        }
        
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(location.x, location.y, coordinate.getXCoor(), coordinate.getYCoor());
        g.setColor(Color.BLACK);
        g.setFont(font);
        if (topString == null || bottomString == null) {
            // Handle gracefully if topString or bottomString is null
            throw new NullPointerException("Top or bottom string cannot be null");
        }
        int strWidth = g.getFontMetrics().stringWidth(topString);
       
        if (strWidth < 0) {
            // Handle gracefully if string width is negative
           throw new NullPointerException("String width cannot be negative");
        }
        g.drawString(topString, location.x + coordinate.getXCoor() / 2 - strWidth / 2, location.y + 20);
        strWidth = g.getFontMetrics().stringWidth(bottomString);
        g.drawString(bottomString, location.x + coordinate.getXCoor() / 2 - strWidth / 2, location.y + 40);
       
    }

    public void setBottomString(String string) {
        this.bottomString = string;
    }

    public void setTopString(String string) {
        this.topString = string;
    }

    public void showGameOver(boolean b) {
        if (b) {
            topString = "Game Over"; // Set the top string
            bottomString = "You win!"; // Set the bottom string
        } else {
            topString = "Game Over"; // Set the top string
            bottomString = "You lose!"; // Set the bottom string
        }
    }
}
