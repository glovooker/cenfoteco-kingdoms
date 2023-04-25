package BL.memento;
import Model.GameState;

import BL.memento.auxiliar.Snapshot;

public class Originator {
    private Snapshot snapshot;

    public Originator(){
        this.snapshot = new Snapshot();
    }

    public void newGameState(GameState state){
        this.snapshot.newPhotoGameState(state);
    }

    public void restoreMemento(Memento memento){
        this.snapshot.newPhotoGameState(memento.getGameState());
    }

    public Memento createMemento(){
        return new Memento(this.snapshot.getGameState());
    }
}
