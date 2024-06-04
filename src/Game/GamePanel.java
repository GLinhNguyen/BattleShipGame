package Game;

import Functions.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener{

    // Initialize the game state, which will be used to determine what the player is currently doing

    // Show a panel to let user know what state they are in


    public StatusPanel statusMessage;

    // Computer's grid 
    private SelectionGrid computer;

    // Functions.Player's grid
    private Player player;
    private GameState currentState;


    public GamePanel() {
        addMouseListener(this);
        addMouseMotionListener(this);
        currentState = GameState.PLACING_SHIPS;
    }

    public void updateGameState(GameState newState) {
        switch (currentState) {
            case PLAYING:
                if(newState == GameState.GAME_WIN) {
                    statusMessage.getMessage();
                } else if(newState == GameState.GAME_LOSS) {
                    statusMessage.getMessage();
                }
                break;
            case PLACING_SHIPS:
                if(newState == GameState.PLAYING) {
                    statusMessage.getMessage();
                }
                break;
            case GAME_WIN:
                //play again
                break;

            case GAME_LOSS:
                //restart the game
                break;


        }
        currentState = newState;
    }
    public static boolean debugModeActive() {
        return true;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }


    public void startGame() {
    // Initialize game components
    statusMessage = new StatusPanel(0, 0);
    player = new Player();
    computer = new SelectionGrid();

    // Start the game loop
    while (true) {
        // Update the game state
        updateGameState(currentState);

        // Redraw the game panel
        repaint();

        // Pause for a short period before the next iteration
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
}
