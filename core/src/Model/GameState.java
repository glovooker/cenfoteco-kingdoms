package Model;

public class GameState {
    private Player player;

    private int time;

    private Player player1;

    private Player player2;

    private static GameState stateInstance;


    public static GameState getStateInstance(){
        if(stateInstance == null){
            stateInstance = new GameState();
        }

        return stateInstance;
    }

    private GameState() {

    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Player getPlayer() {
        return player;
    }

    public int getTime() {
        return time;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
