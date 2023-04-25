package BL;

import BL.bridge_dice_buttons.dadoMovimiento.DadoMovimiento;
import BL.decorator.GestorDecorador;
import BL.memento.GestorMemento;
import Model.Castle;
import Model.GameState;
import Model.Player;
import BL.observer.concret.TimerSec;
import BL.bridge_dice_buttons.GestorBridge;
import BL.characters_abstract_fabric.GestorFabricaAbstracta;
import BL.characters_abstract_fabric.abstract_product.Army;import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameController {

    private Player player1;
    private Player player2;
    private Player playerInTurn;

    private TimerSec timer;

    private static GestorBridge gestorBridge;
    private static GestorFabricaAbstracta gestorFabricaAbstracta;
    private static GestorDecorador gestorDecorador;
    private static GameController gameController;

    private GestorMemento mementoController;

    private GameState gameState = GameState.getStateInstance();


    private GameController() {
        this.player1 = new Player(1);
        this.player2 = new Player(2);
        gestorBridge = new GestorBridge();
        gestorFabricaAbstracta = new GestorFabricaAbstracta();
        gestorBridge.iniciarBotones();
        gestorDecorador = new GestorDecorador();
    }

    public static GameController getInstance(){
        if (gameController == null){
            gameController = new GameController();
        }

        return gameController;
    }

    public void setTime(){
        this.timer.setTime(0);
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
        this.mementoController = new GestorMemento();
        this.timer = new TimerSec();
        this.timer.addObservers(mementoController);
        this.timer.start();
    }

    public GestorMemento getMementoController(){
        return this.mementoController;
    }

    private void initializeGameState(){
        this.gameState.setPlayer1(this.player1);
        this.gameState.setPlayer2(this.player2);
        this.gameState.setPlayer(this.playerInTurn);
    }

    public GameState getGameState(){
        return this.gameState;
    }

    public Player getPlayerInTurn(){
        return this.gameState.getPlayerInTurn();
    }

    public ArrayList<Integer> lanzarDados(){
        return gestorBridge.lanzarDado();
    }


    public Army invocarInfanteria(){
        String armyName = gestorBridge.invocarInfanteria(gameState.getPlayerInTurn().getChest().getInfantry());

        if(armyName != null) {
            Army infanteriaInvocada = gestorFabricaAbstracta.createArmy(armyName, getPlayerInTurn());
            getPlayerInTurn().getChest().setInfantry(gestorBridge.evaluarCofreInfanteria(gameState.getPlayerInTurn().getChest().getInfantry()));

            return gestorDecorador.addRandomSpecialAttack(infanteriaInvocada);
        }
        return null;
    }
    public Army invocarArtilleria(){

        if(!(gestorBridge.invocarArtilleria(gameState.getPlayerInTurn().getChest().getGunner()) == null)){
            Army artilleriaInvocada = gestorFabricaAbstracta.createArmy(gestorBridge.invocarArtilleria(gameState.getPlayerInTurn().getChest().getGunner()), getPlayerInTurn());
            getPlayerInTurn().getChest().setGunner(gestorBridge.evaluarCofreArtilleria(gameState.getPlayerInTurn().getChest().getGunner()));

            return gestorDecorador.addRandomSpecialAttack(artilleriaInvocada);
        }
        return null;
    }
    public Army invocarTanque(){
        if(!(gestorBridge.invocarTanque(gameState.getPlayerInTurn().getChest().getTank()) == null)){
            Army tanqueInvocado = gestorFabricaAbstracta.createArmy(gestorBridge.invocarTanque(gameState.getPlayerInTurn().getChest().getTank()), getPlayerInTurn());
            getPlayerInTurn().getChest().setTank(gestorBridge.evaluarCofreTanque(gameState.getPlayerInTurn().getChest().getTank()));

            return gestorDecorador.addRandomSpecialAttack(tanqueInvocado);
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

    public DadoMovimiento move(){
        ArrayList<DadoMovimiento> movementsInChest = getPlayerInTurn().getChest().getMovementsInChest();
        DadoMovimiento movement = gestorBridge.mover(movementsInChest);

        if(movement != null) {
            int evaluationResult = gestorBridge.evaluateMovesInChest(movementsInChest.size());

            if(movementsInChest.size() != evaluationResult) {
                movementsInChest.remove(0);
            }

            return movement;
        }

        return null;
    }
    public ArrayList<Integer> almacenarDados(){
        return gestorBridge.almacenarCofre();
    }

    public void atacar(Army armyAttacks, Army armyToAttack){
        armyAttacks.attack(armyToAttack);
    }

    public void atacarCastillo(Castle castle, Army army){
        army.attackCastle(castle);
    }
}
