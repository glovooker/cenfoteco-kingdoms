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
    private Player playerInTurn;

    private TimerSec timer;

    private static GestorBridge gestorBridge;
    private static GestorFabricaAbstracta gestorFabricaAbstracta;
    private static GameController gameController;

    private GameState gameState = GameState.getStateInstance();


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
        int luckyNumber = /*1;//*/r.nextInt() * 2 + 1;
        if(luckyNumber == this.player1.getLuckyNumber()){
            this.player1.setTurn(true);
            this.playerInTurn = player1;
        }else {
            this.player2.setTurn(true);
            this.playerInTurn = player2;
        }

        initializeGameState();

        GestorMemento mementoController = new GestorMemento();
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

    public ArrayList<Integer> lanzarDados(){
        return gestorBridge.lanzarDado();
    }

    public Army invocarInfanteria(){
        if(!(gestorBridge.invocarInfanteria(getPlayerInTurn().getChest().getInfantry()) == null)) {
            Army infanteriaInvocada = gestorFabricaAbstracta.createArmy(gestorBridge.invocarInfanteria(getPlayerInTurn().getChest().getInfantry()), "player");
            getPlayerInTurn().getChest().setInfantry(gestorBridge.evaluarCofreInfanteria(getPlayerInTurn().getChest().getInfantry()));
            return infanteriaInvocada;
        }
        return null;
    }
    public Army invocarArtilleria(){
        if(!(gestorBridge.invocarArtilleria(getPlayerInTurn().getChest().getGunner()) == null)){
            Army artilleriaInvocada = gestorFabricaAbstracta.createArmy(gestorBridge.invocarArtilleria(getPlayerInTurn().getChest().getGunner()), "player");
            getPlayerInTurn().getChest().setGunner(gestorBridge.evaluarCofreArtilleria(getPlayerInTurn().getChest().getGunner()));
            return artilleriaInvocada;
        }
        return null;
    }
    public Army invocarTanque(){
        if(!(gestorBridge.invocarTanque(getPlayerInTurn().getChest().getTank()) == null)){
            Army tanqueInvocado = gestorFabricaAbstracta.createArmy(gestorBridge.invocarTanque(getPlayerInTurn().getChest().getTank()), "player");
            getPlayerInTurn().getChest().setTank(gestorBridge.evaluarCofreTanque(getPlayerInTurn().getChest().getTank()));
            return tanqueInvocado;
        }
        return null;
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
