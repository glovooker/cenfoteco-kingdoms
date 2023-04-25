package BL.memento;

import BL.memento.auxiliar.Snapshot;
import Model.GameState;

public class Memento {
    private Snapshot snapshot;

    public Memento(GameState state){
        this.snapshot = new Snapshot();
        this.snapshot.newPhotoGameState(state);
    }

    public GameState getGameState(){
        return this.snapshot.getGameState();
    }

}
