package Model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Chest chest;
    private Castle castle;
    private boolean turn;
    private final int luckyNumber;
    private String name;

    private boolean rollDice = true;

    private List<Coordinate> coordinateList;

    public Player(int luckyNumber) {
        this.chest = new Chest();
        this.turn = false;
        this.luckyNumber = luckyNumber;
        this.name = "Player " + luckyNumber;
    }

    public Chest getChest() {
        return chest;
    }

    public List<Coordinate> getCoordinatesList() {
        return coordinateList;
    }

    public void initializeCoordinatesList() {
        this.coordinateList = new ArrayList<>();
    }

    public void setChest(Chest chest) {
        this.chest = chest;
    }

    public Castle getCastle() {
        return castle;
    }

    public void setCastle(Castle castle) {
        this.castle = castle;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public int getLuckyNumber(){
        return this.luckyNumber;
    }

    public String getName() {
        return name;
    }

    public boolean isRollDice() {
        return rollDice;
    }

    public void setRollDice(boolean rollDice) {
        this.rollDice = rollDice;
    }

    @Override
    public String toString() {
        return "Player{" +
                "chest=" + chest.toString() +
                "Player=" + this.name +
                "number=" + this.luckyNumber +
                "life = " + this.castle.getLife() +
                '}';
    }
}
