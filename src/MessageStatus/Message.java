package MessageStatus;
import Boards.Location;

public class Message extends StatusPanel {
    private String topString;
    private String bottomString;
    public GameState state;


    public Message(String topLine, String bottomLine) {
    super (new Location(0, 0), 0, 0);
    this.topString = topLine;
    this.bottomString = bottomLine;
    announcement = topString + "\n" + bottomString;
    }
    
    public Message(String topLine, String bottomLine, GameState state) {
    super (new Location(0, 0), 0, 0);
        this.topString = topLine;
        this.bottomString = bottomLine;
        this.state = state;
        announcement = topString + "\n" + bottomString;
    }

    public String getMessage() {
        return announcement;
    }

    public void setTopString(String string) {
        this.topString = string;
    }
    public void setBottomString(String string) {
        this.bottomString = string;
    }






    // getters and setters omitted for brevity
}