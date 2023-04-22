package Model;

public class GameState {
    private Player playerInTurn;

    private int time;

    private Player player1;

    private Player player2;

    private static GameState stateInstance;

    private Board board;


    public static GameState getStateInstance(){
        if(stateInstance == null){
            stateInstance = new GameState();
        }

        return stateInstance;
    }

    private GameState() {
        this.board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setPlayer(Player player) {
        this.playerInTurn = player;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
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

    private boolean isPrefaReady = false;

    public void changePlayerInTurn() {
        this.setPrefaReady(false);

        Player playerInTurn = this.getPlayerInTurn().equals(this.getPlayer1())
                ? this.getPlayer2()
                : this.getPlayer1();

        this.setPlayer(playerInTurn);
    }


    public boolean isPrefaReady() {
        return isPrefaReady;
    }

    public void setPrefaReady(boolean prefaReady) {
        isPrefaReady = prefaReady;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "playerInTurn=" + playerInTurn.toString() +
                ", time=" + time +
                ", player1=" + player1.toString() +
                ", player2=" + player2.toString() +
                '}';
    }
}
