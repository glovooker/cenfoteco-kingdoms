package BL;

import Model.Castle;
import Model.GameState;
import Model.Player;
import Model.TimerSec;
import Observer.Interface.Subject;
import java.util.Random;
import java.util.Timer;


public class GameController {

    private Player player1;

    private Player player2;

    private  Player playerInTurn;

    private TimerSec timer;

    private static GameController gameController;

    private GameState gameState = GameState.getStateInstance();


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
        int luckyNumber = /*1;//*/r.nextInt() * 2 + 1;
        if(luckyNumber == this.player1.getLuckyNumber()){
            this.player1.setTurn(true);
            this.playerInTurn = player1;
        }else {
            this.player2.setTurn(true);
            this.playerInTurn = player2;
        }

        initializeGameState();
        MementoController mementoController = new MementoController();
        this.timer = new TimerSec();
        this.timer.addObservers(mementoController);
        this.timer.start();
    }

    private void initializeGameState(){
        this.gameState.setPlayer1(this.player1);
        this.gameState.setPlayer2(this.player2);
        this.gameState.setPlayer(this.playerInTurn);
    }

    public Player getPlayerInTurn(){
        return this.playerInTurn;
    }

}
