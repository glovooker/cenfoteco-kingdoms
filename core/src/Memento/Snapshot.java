package Memento;

import Model.GameState;
import Model.Player;

public class Snapshot {
    private GameState state;

    public void newPhotoGameState(GameState gameState){
        this.state = gameState;
    }

    public GameState getGameState(){
        return this.state;
    }

}
