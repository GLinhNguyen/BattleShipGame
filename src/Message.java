import Game.GameState;

public class Message {
    private String topLine;
    private String bottomLine;
    public GameState state;

    public Message(String topLine, String bottomLine, GameState state) {
        this.topLine = topLine;
        this.bottomLine = bottomLine;
        this.state = state;
    }

    public String getMessage() {
        return topLine + "\n" + bottomLine;
    }






    // getters and setters omitted for brevity
}