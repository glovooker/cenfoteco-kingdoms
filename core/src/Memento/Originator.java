package Memento;

import Model.Chest;
import Model.Player;

public class Originator {
    private Snapshot state;

    public Originator(){
        this.state = new Snapshot();
    }

    public void newStatePlayer1(Player player1){
        this.state.newPhotoPlayer1(player1);
    }

    public void newStatePlayer2(Player player2){
        this.state.newPhotoPlayer2(player2);
    }


    public Player getStatePlayer1(){
       return this.state.getStatePlayer1();

    }

    public Player getStatePlayer2(){
        return this.state.getStatePlayer2();
    }


    public void restoreMemento(Memento memento){
        this.state.newPhotoPlayer1(memento.getPlayer1Memento());
        this.state.newPhotoPlayer2(memento.getPlayer2Memento());
    }

    public Memento createMemento(){
        return new Memento(this.state.getStatePlayer1(), this.getStatePlayer2());
    }
}
