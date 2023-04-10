package Memento;
import Model.GameState;


public class Originator {
    private Snapshot state;

    public Originator(){
        this.state = new Snapshot();
    }


    public void newGameState(GameState state){
        this.state.newPhotoGameState(state);
    }

    public void restoreMemento(Memento memento){
        this.state.newPhotoGameState(memento.getGameState());
    }

    public Memento createMemento(){
        return new Memento();
    }
}
