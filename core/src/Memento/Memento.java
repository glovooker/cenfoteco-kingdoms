package Memento;

import Model.Chest;
import Model.GameState;
import Model.Player;

public class Memento {
    private Snapshot snapshot;


    public Memento(){
        this.snapshot = new Snapshot();
    }


    public GameState getGameState(){
        return this.snapshot.getGameState();
    }

}
