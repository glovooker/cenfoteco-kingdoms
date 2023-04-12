package BL;


import Memento.CareTaker;
import Memento.Originator;
import Model.GameState;

import Observer.Interface.Observer;

public class MementoController implements Observer {

    private GameState gameState = GameState.getStateInstance();

    private Originator originator;

    private CareTaker careTaker;



    public MementoController() {
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
