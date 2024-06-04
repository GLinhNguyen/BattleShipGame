package BotThings;
import Functions.*;

import java.util.Collections;

public class EasyBot extends Bot {
    public EasyBot(SelectionGrid playerGrid) {
        super(playerGrid);
        Collections.shuffle(validMoves);
    }

    public void reset() {
        selectionGrid.reset();
        Collections.shuffle(validMoves);
    }

    @Override
    public Location selectMove() {
        Location nextMove = validMoves.get(0);
        validMoves.remove(0);
        return nextMove;
    }
}
