package BL.memento;


import Model.GameState;
import Model.Player;
import BL.observer.interfaces_observer.Observer;

public class GestorMemento implements Observer {

    private Player player1;
    private Player player2;

    private Player playerInTurn;

    private Originator originator;

    private CareTaker careTaker;


    public GestorMemento(Player player1, Player player2, Player playerInTurn) {
        this.originator = new Originator();
        this.careTaker = new CareTaker();
        this.player1 = player1;
        this.player2 = player2;
        this.playerInTurn = playerInTurn;
    }

    private void updatePlayer1(Player player1){
        this.originator.newStatePlayer1(player1);
        this.player1 = player1;
        this.careTaker.setMemento(this.careTaker.getMemento());
    }

    private void updatePlayer2(Player player2){
        this.originator.newStatePlayer2(player2);
        this.player2 = player2;
        this.careTaker.setMemento(this.careTaker.getMemento());
    }

    public void saveStatePlayer1(Player playerInTurn){
        updatePlayer1(playerInTurn);
    }

    public void saveStatePlayer2(Player playerInTurn){
        updatePlayer2(playerInTurn);
    }

    private void updateMemento(){
        this.careTaker.setMemento(this.originator.createMemento());
    }

    public void restoreMemento(){
        this.originator.restoreMemento(this.careTaker.getMemento());
    }

    @Override
    public void update(GameState state) {
        if(this.playerInTurn.equals(this.player1)){
            saveStatePlayer1(this.playerInTurn);
        }else{
            saveStatePlayer2(this.playerInTurn);
        }

        this.playerInTurn = state.getPlayer();
    }
}
