package BL;


import Memento.CareTaker;
import Memento.Originator;
import Model.Player;
import Observer.Interface.Observer;

public class MementoController implements Observer {
    private Player player1;

    private Player player2;

    private Player playerInTurn;

    private Originator originator;

    private CareTaker careTaker;



    public MementoController(Player player1, Player player2, Player playerInTurn) {
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
    public void update() {
        if(this.playerInTurn.equals(this.player1)){
            saveStatePlayer1(this.playerInTurn);
            this.playerInTurn = player2;
            System.out.println(playerInTurn.getName());
        }else{
            saveStatePlayer2(this.playerInTurn);
            this.playerInTurn = player1;
            System.out.println(playerInTurn.getName());
        }
    }
}
