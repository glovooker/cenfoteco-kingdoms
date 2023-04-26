package BL;

import BL.bridge_dice_buttons.dadoMovimiento.DadoMovimiento;
import BL.decorator.GestorDecorador;
import BL.memento.GestorMemento;
import Model.*;
import BL.observer.concret.TimerSec;
import BL.bridge_dice_buttons.GestorBridge;
import BL.characters_abstract_fabric.GestorFabricaAbstracta;
import BL.characters_abstract_fabric.abstract_product.Army;import java.util.ArrayList;
import java.util.Random;

public class GameController {
    private final Player player1;
    private final Player player2;
    private Player playerInTurn;

    private TimerSec timer;

    private static GestorBridge gestorBridge;
    private static GestorFabricaAbstracta gestorFabricaAbstracta;
    private static GestorDecorador gestorDecorador;
    private static GameController gameController;
    private final GestorMemento mementoController;

    private GameState gameState;


    private GameController() {
        this.player1 = new Player(1);
        this.player2 = new Player(2);
        this.player1.setCastle(new Castle());
        this.player2.setCastle(new Castle());

        gestorBridge = new GestorBridge();
        this.mementoController = new GestorMemento();
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

    public void initialize() {
        this.initializePlayerOrder();
        initializeGameState();
        initializeGameTimer();
    }

    public void loadExistingGame() {
        this.gameState = this.mementoController.getGameStateSaved().clone();
        initializeGameTimer();
    }

    public void exitGame() {
        this.timer.cleanObservers();
        this.timer.stop();
        this.gameState = new GameState();
    }

    private void initializeGameTimer() {
        this.timer = new TimerSec(this.getGameState());
        this.timer.addObservers(mementoController);
        this.timer.start();
    }

    public void setTime(){
        this.timer.setTime(0);
    }

    public Player getPlayer1() {
        return this.gameState.getPlayer1();
    }
    public Player getPlayer2() {
        return this.gameState.getPlayer2();
    }

    public TimerSec getTimer() {
        return this.timer;
    }

    private void initializePlayerOrder(){
        Random r = new Random();
        int luckyNumber = /*1;//*/r.nextInt() * 2 + 1;
        if(luckyNumber == this.player1.getLuckyNumber()){
            this.player1.setTurn(true);
            this.playerInTurn = player1;
        }else {
            this.player2.setTurn(true);
            this.playerInTurn = player2;
        }
    }

    public GestorMemento getMementoController(){
        return this.mementoController;
    }

    private void initializeGameState(){
        this.gameState = new GameState();
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

            if(infanteriaInvocada != null) {
                getPlayerInTurn().getChest().setInfantry(gestorBridge.evaluarCofreInfanteria(gameState.getPlayerInTurn().getChest().getInfantry()));

                return gestorDecorador.addRandomSpecialAttack(infanteriaInvocada);
            }
            else {
                System.out.println("Infantry couldn't be invoked");
            }
        }

        return null;
    }

    public Army invocarArtilleria() {

        if (!(gestorBridge.invocarArtilleria(gameState.getPlayerInTurn().getChest().getGunner()) == null)) {
            Army artilleriaInvocada = gestorFabricaAbstracta.createArmy(gestorBridge.invocarArtilleria(gameState.getPlayerInTurn().getChest().getGunner()), getPlayerInTurn());

            if (artilleriaInvocada != null) {
                getPlayerInTurn().getChest().setGunner(gestorBridge.evaluarCofreArtilleria(gameState.getPlayerInTurn().getChest().getGunner()));
                return gestorDecorador.addRandomSpecialAttack(artilleriaInvocada);
            } else {
                System.out.println("Infantry couldn't be invoked");
            }
        }
        return null;
    }

    public Army invocarTanque() {
        if (!(gestorBridge.invocarTanque(gameState.getPlayerInTurn().getChest().getTank()) == null)) {
            Army tanqueInvocado = gestorFabricaAbstracta.createArmy(gestorBridge.invocarTanque(gameState.getPlayerInTurn().getChest().getTank()), getPlayerInTurn());

            if (tanqueInvocado != null) {
                getPlayerInTurn().getChest().setTank(gestorBridge.evaluarCofreTanque(gameState.getPlayerInTurn().getChest().getTank()));
                return gestorDecorador.addRandomSpecialAttack(tanqueInvocado);
            } else {
                System.out.println("Infantry couldn't be invoked");
            }
        }
        return null;
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

    public void attackEnemyOrCastle(Army enemyArmy, Castle castle) {
        Chest chest = getPlayerInTurn().getChest();

        if((enemyArmy != null || castle != null) && gestorBridge.atacar(getPlayerInTurn().getChest().getAttacksInChest()) != null) {
            Army attackingArmy = this.gameState.getSelectedArmy();

            if(enemyArmy != null) {
                attackEnemy(attackingArmy, enemyArmy);
            }

            if(castle != null) {
                attackCastle(attackingArmy, castle);
            }

            chest.setAttacksInChest(gestorBridge.evaluateAttacksInChest(chest.getAttacksInChest()));
        }
    }

    private void attackEnemy(Army attackingArmy, Army enemyArmy) {
        if(attackingArmy.getPosition().isAdjacentTo(enemyArmy.getPosition())) {
            attackingArmy.attack(enemyArmy);
        }
        else {
            System.out.println("Ta muy largo");
        }
    }

    private void attackCastle(Army attackingArmy, Castle castle) {
        if(attackingArmy.getPosition().isAdjacentTo(castle.getCoordinates())) {
            attackingArmy.attackCastle(castle);
        }
        else {
            System.out.println("Ta muy largo");
        }
    }

    private void healer(Army aly, Player playerInTurn){
        if(aly.getOwner().getName().equalsIgnoreCase(playerInTurn.getName()) && aly.getLife() !=  aly.getLifeAdded() ){
            aly.setLife(aly.getLife() + 1);
        }
    }

    private void addThreePointsToDefense(Army aly){
        if(aly.getOwner().getName().equalsIgnoreCase(playerInTurn.getName())){
            aly.setAdditionalDefense(3);
        }
    }

    private void addThreePointsToAttack(Army aly){
        if(aly.getOwner().getName().equalsIgnoreCase(playerInTurn.getName())){
            aly.setAdditionalAttack(3);
        }
    }

    public void specialAttacks(Army aly){

         Chest chest = gameState.getPlayerInTurn().getChest();
        if(this.gameState.getSelectedArmy() != null && gestorBridge.ejecutarAtaqueEspecial(gameState.getPlayerInTurn().getChest().getSpecialAttackInChest()) != null) {

            switch (this.gameState.getSelectedArmy().getCharacterType()) {
                case "infanteria":
                    specialAttackInfantry(aly);
                    break;
            }

            chest.setSpecialAttackInChest(gestorBridge.evaluateSpecialAttacksInChest(chest.getSpecialAttackInChest()));
        }
    }

    private void specialAttackInfantry(Army aly){
        String specialAttack = this.gameState.getSelectedArmy().getSpecialAttack();

        switch (specialAttack){
            case "Healer":
                healer(aly, this.getPlayerInTurn());
                break;

            case "Add three points to defense" :
                addThreePointsToDefense(aly);
                break;

            case "Add three points to attack":
                addThreePointsToAttack(aly);
                break;
        }
    }

    private void twoTilesAttack(Army enemy){
        if(!(enemy.getOwner().getName().equals(this.gameState.getPlayerInTurn().getName()))){
            int positionXRight = gameState.getSelectedArmy().getPosition().getX() + 2;
            int positionXLeft = gameState.getSelectedArmy().getPosition().getX() - 2;
            int positionYUp = gameState.getSelectedArmy().getPosition().getX() + 2;
            int positionYDown = gameState.getSelectedArmy().getPosition().getX() - 2;
            int positionArmyEnemyX = enemy.getPosition().getX();
            int positionArmyEnemyY = enemy.getPosition().getY();

            if( positionArmyEnemyX == positionXRight || positionArmyEnemyX == positionXLeft || positionArmyEnemyY == positionYUp ||positionArmyEnemyY == positionYDown){
                int attack = gameState.getSelectedArmy().getAttack();
                enemy.setDefense(enemy.getDefense() - attack);
            }
        }
    }

    private void specialAttackGunner(Army enemy){
        String specialAttack = this.gameState.getSelectedArmy().getSpecialAttack();

        switch (specialAttack){
            case "Two tiles attack":
                twoTilesAttack(enemy);
                break;

            case "Double defense power" :

                break;

            case "Double attack power":

                break;
        }
    }


}
