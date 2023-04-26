package Model;

import BL.characters_abstract_fabric.abstract_product.Army;
import BL.observer.interfaces_observer.Observer;

import java.util.List;

public class GameState implements Cloneable {
    private Player playerInTurn;

    private int time;

    private Player player1;

    private Player player2;

    private Board board;

    private Army selectedArmy;
    private Army selectedEnemyArmy;
    private Castle selectedCastle;



    public GameState() {
        this.board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setPlayer(Player player) {
        this.playerInTurn = player;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public int getTime() {
        return time;
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

    private boolean isPrefaReady = false;

    public void changePlayerInTurn() {
        this.setPrefaReady(false);
        this.playerInTurn.setMovementDice(null);
        this.playerInTurn.setUsingSpecialAttack(false);
        this.playerInTurn.setUsingSpecialAttack(true);

        Player playerInTurn = this.getPlayerInTurn().equals(this.getPlayer1())
                ? this.getPlayer2()
                : this.getPlayer1();

        this.setPlayer(playerInTurn);
        this.playerInTurn.setRollDice(true);
        this.selectedArmy = null;
        updateArmyList();

    }


    public boolean isPrefaReady() {
        return isPrefaReady;
    }

    public void setPrefaReady(boolean prefaReady) {
        isPrefaReady = prefaReady;
    }

    public Army getSelectedArmy() {
        return selectedArmy;
    }

    public void setSelectedArmy(Army selectedArmy) {
        this.selectedArmy = selectedArmy;
    }
    public Army getSelectedEnemyArmy() {
        return selectedEnemyArmy;
    }

    public void setSelectedEnemyArmy(Army selectedEnemyArmy) {
        this.selectedEnemyArmy = selectedEnemyArmy;
    }
    public Castle getSelectedCastle() {
        return selectedCastle;
    }
    public void setSelectedCastle(Castle selectedCastle) {
        this.selectedCastle = selectedCastle;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "playerInTurn=" + playerInTurn.toString() +
                ", time=" + time +
                ", player1=" + player1.toString() +
                ", player2=" + player2.toString() +
                '}';
    }



    private void updateArmyList() {
        List<Army> armyInBoard = this.getBoard().getArmyList();
        for(int i = 0; i < armyInBoard.size(); i++){
            Army army = armyInBoard.get(i);
            if(army.getMovement() != army.getMovementAdded()){
                army.setMovements(army.getMovementAdded());
            }
            army.setAdditionalDefense(0);
            army.setAdditionalAttack(0);
        }
    }

    @Override
    public GameState clone() {
        try {
            GameState clone = (GameState) super.clone();
            clone.playerInTurn = this.playerInTurn.clone();
            clone.player1 = this.player1.clone();
            clone.player2 = this.player2.clone();
            clone.board = this.board.clone();
            clone.selectedArmy = null;

            return clone;
        }
        catch(CloneNotSupportedException ex) {
            return this;
        }
    }
}
