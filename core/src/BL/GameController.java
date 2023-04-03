package BL;

import Model.Castle;
import Model.Player;
import Model.TimerSec;

import java.util.Timer;

public class GameController {

   private MementoController mementoController;

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

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public TimerSec getTimer() {
        return this.timer;
    }


    public void choosingStartPlayer(){
        int luckyNumber = (int) (Math.random() * 2 + 1);
        if(luckyNumber == this.player1.getLuckyNumber()){
            this.player1.setTurn(true);
            this.player1.getCastle().setId(this.player1.getLuckyNumber());
            this.playerInTurn = player1;
        }else {
            this.player2.setTurn(true);
            this.playerInTurn = player2;
            this.player2.getCastle().setId(this.player2.getLuckyNumber());
        }

        System.out.println("Actual player " + playerInTurn.getName());
        this.mementoController = new MementoController(this.player1, this.player2, this.playerInTurn);
        this.timer = new TimerSec(this.playerInTurn);
        this.timer.addObservers(this.mementoController);
        Timer test = new Timer();
        test.scheduleAtFixedRate(this.timer, 1000, 1000);
        System.out.println(player1.toString());
        System.out.println(player2.toString());

    }



    public Player getPlayerInTurn(){
        return this.playerInTurn;
    }

    public void setPlayerInTurn(Player player){
        this.playerInTurn = player;
    }

}
