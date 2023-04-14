package BL.memento;

import BL.memento.auxiliar.Snapshot;
import Model.Player;

public class Memento {
    private Snapshot snapshot;

    private Snapshot getSnapshot(){
        return this.snapshot;
    }


    public Memento(Player player1, Player player2){
        this.snapshot = new Snapshot();
        this.getSnapshot().newPhotoPlayer1(player1);
        this.getSnapshot().newPhotoPlayer2(player2);
    }

    public Player getPlayer1Memento(){
        return this.snapshot.getStatePlayer1();
    }

    public Player getPlayer2Memento(){
        return this.snapshot.getStatePlayer2();
    }
}
