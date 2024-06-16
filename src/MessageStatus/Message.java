package MessageStatus;

import Boards.Location;

public class Message {

    private String topString;
    private String bottomString;
    private GameState state;

    public Message(String topLine, String bottomLine, GameState state) {
        this.topString = topLine;
        this.bottomString = bottomLine;
        this.state = state;
    }

    public String getTopString() {
        return topString;
    }

    public String getBottomString() {
        return bottomString;
    }

    public GameState getState() {
        return state;
    }

    public void setTopString(String string) {
        this.topString = string;
    }

    public void setBottomString(String string) {
        this.bottomString = string;
    }
}
