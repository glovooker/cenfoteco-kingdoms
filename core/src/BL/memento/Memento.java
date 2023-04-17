package BL.memento;

import BL.memento.auxiliar.Snapshot;
import Model.GameState;

public class Memento {
    private Snapshot snapshot;

    public Memento(){
        this.snapshot = new Snapshot();
    }


    public GameState getGameState(){
        return this.snapshot.getGameState();
    }

}
