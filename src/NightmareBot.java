import java.util.*;
import java.lang.reflect.AccessFlag;

import javax.swing.text.Position;

public class NightmareBot extends EasyBot {

    private List<Location> shipsHit;

    private final boolean randomAi = false;
    private boolean preferMovesFormingLine;
    private boolean maximizedAdjancentHits;
    
    
    public NightmareBot(SelectionGrid playerGrid, boolean preferMovesFormingLine, boolean maximizedAdjancentHits) {
        super(playerGrid);
        this.preferMovesFormingLine = preferMovesFormingLine;
        this.maximizedAdjancentHits = maximizedAdjancentHits;
        Collection.shuffle(validMoves);
    }

    @Override
    public void reset() {
        super.reset();
        shipsHit.clear();
        Collections.shuffle(validMoves);
    }

    @Override
    public Location selectMove() {
        if (randomAi) System.out.println("\n BEGIN TURN!");
        Location selectMove;

        if (shipsHit.size() >0){
            selectMove = getSmarterMove();
        } else {
            selectMove = getSmartMove();
            }
        } else{     
            if (maximizedAdjancentHits){
                selectMove = findEmptyPosition();
            }
        }
    }

}
