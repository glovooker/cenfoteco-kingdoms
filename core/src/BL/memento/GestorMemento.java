package BL.memento;
import BL.memento.CareTaker;
import BL.memento.Originator;
import Model.GameState;

import BL.observer.interfaces_observer.Observer;

public class GestorMemento implements Observer {

    private GameState gameState = GameState.getStateInstance();

    private Originator originator;

    private CareTaker careTaker;


    public GestorMemento() {
        this.originator = new Originator();
        this.careTaker = new CareTaker();
    }

    private void updateGameState(GameState state){
        this.originator.newGameState(state);
        this.gameState = state;
        this.careTaker.setMemento(this.careTaker.getMemento());
    }

    private void saveGameState(GameState state){
        updateGameState(state);
    }

    private void updateMemento(){
        this.careTaker.setMemento(this.originator.createMemento());
    }

    public void restoreMemento(){
        this.originator.restoreMemento(this.careTaker.getMemento());
    }

    @Override
    public void update(GameState state) {
        saveGameState(state);
    }
}
