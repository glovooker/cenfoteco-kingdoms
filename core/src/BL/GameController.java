package BL;

import BL.memento.GestorMemento;
import Model.GameState;
import Model.Player;
import BL.observer.concret.TimerSec;
import BL.bridge_dice_buttons.GestorBridge;
import BL.characters_abstract_fabric.GestorFabricaAbstracta;
import BL.characters_abstract_fabric.abstract_product.Army;
import java.util.ArrayList;
import java.util.Random;

public class GameController {

    private Player player1;
    private Player player2;
    private  Player playerInTurn;

    private TimerSec timer;

    private static GestorBridge gestorBridge;
    private static GestorFabricaAbstracta gestorFabricaAbstracta;
    private static GameController gameController;

    private GameController() {
        this.player1 = new Player(1);
        this.player2 = new Player(2);
        gestorBridge = new GestorBridge();
        gestorFabricaAbstracta = new GestorFabricaAbstracta();
        gestorBridge.iniciarBotones();
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

    public ArrayList<Integer> lanzarDados(){
        return gestorBridge.lanzarDado();
    }

    public String invocarInfanteria(){
        if(gestorBridge.invocarInfanteria() == null){
            return "No se puede invocar infanteria";
        } else {
            return gestorFabricaAbstracta.createArmy(gestorBridge.invocarInfanteria(), "player");
        }
    }
    public String invocarArtilleria(){
        if(gestorBridge.invocarArtilleria() == null){
            return "No se puede invocar artilleria";
        } else {
            return gestorFabricaAbstracta.createArmy(gestorBridge.invocarArtilleria(), "player");
        }
    }
    public String invocarTanque(){
        if(gestorBridge.invocarTanque() == null){
            return "No se puede invocar tanque";
        } else {
            return gestorFabricaAbstracta.createArmy(gestorBridge.invocarTanque(), "player");
        }
    }

    public String obtenerArmada(){
        ArrayList<Army> ejercito = gestorFabricaAbstracta.getArmyPlayerList();
        String mensaje = "";
        for (Army army : ejercito) {
            mensaje += army.toString() + "\n";
        }
        return mensaje;
    }

    public ArrayList<Integer> almacenarDados(){
        return gestorBridge.almacenarCofre();
    }

}
