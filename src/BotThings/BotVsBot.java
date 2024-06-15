package BotThings;
import Functions.*;

public class BotVsBot {
    private SelectionGrid grid1;
    private SelectionGrid grid2;
    private EasyBot player1;
    private NightmareBot player2;

    public BotVsBot(EasyBot player1, NightmareBot player2) {
        this.grid1 = new SelectionGrid(0,0);
        this.grid2 = new SelectionGrid(0, grid1.getHeight()+50);
        this.player1 = player1;
        this.player2 = player2;
        player1.setOpponent(player2);
        player2.setOpponent(player1);
    }
    public void playGame(){
        player1.placeShips();
        player2.placeShips();
        boolean bot1Turn = true;

        while(!grid1.areAllShipsDestroyed() && !grid2.areAllShipsDestroyed()){
            if(bot1Turn){
                player1.takeTurn();
            } else {
                player2.takeTurn();
            }
            bot1Turn = !bot1Turn;
        }
        if(grid1.areAllShipsDestroyed()){
            System.out.println("Player 2 wins!");
        } else {
            System.out.println("Player 1 wins!");
        }
    }

}
