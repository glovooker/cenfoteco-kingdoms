package BL;

import BL.memento.GestorMemento;
import Model.GameState;
import Model.Player;
import BL.observer.concret.TimerSec;

import java.util.Random;

public class GameController {

    private Player player1;

    private Player player2;

    private  Player playerInTurn;

    private TimerSec timer;

    private static GameController gameController;


    private GameController() {
        this.player1 = new Player(1);
        this.player2 = new Player(2);
    }

    public static GameController getInstance(){
        if (gameController == null){
            gameController = new GameController();
        }

        return gameController;
    }

    public Player getPlayer1() {
        return player1;
    }


    public Player getPlayer2() {
        return player2;
    }

    public TimerSec getTimer() {
        return this.timer;
    }


    public void choosingStartPlayer(){
        Random r = new Random();
        int luckyNumber =  1;
        if(luckyNumber == this.player1.getLuckyNumber()){
            this.player1.setTurn(true);
            this.playerInTurn = player1;
        }else {
            this.player2.setTurn(true);
            this.playerInTurn = player2;
        }

        GameState state = GameState.getStateInstance();
        state.setPlayer1(this.player1);
        state.setPlayer2(this.player2);
        state.setPlayer(this.playerInTurn);

        GestorMemento mementoController = new GestorMemento(this.player1, this.player2, this.playerInTurn);
        this.timer = new TimerSec();
        this.timer.addObservers(mementoController);
        this.timer.start();
    }

    public Player getPlayerInTurn(){
        return this.playerInTurn;
    }

}
