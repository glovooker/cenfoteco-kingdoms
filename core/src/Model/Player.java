package Model;

import BL.bridge_dice_buttons.dadoMovimiento.DadoMovimiento;

import java.util.ArrayList;
import java.util.List;

public class Player implements Cloneable {
    private Chest chest;
    private Castle castle;
    private boolean turn;
    private final int luckyNumber;
    private String name;
    private boolean rollDice = true;
    private ArrayList<Coordinate> coordinateList;

    private DadoMovimiento movementDice;

    public Player(int luckyNumber) {
        this.chest = new Chest();
        this.turn = false;
        this.luckyNumber = luckyNumber;
        this.name = "Player " + luckyNumber;
    }

    public DadoMovimiento getMovementDice() {
        return movementDice;
    }

    public void setMovementDice(DadoMovimiento movementDice) {
        this.movementDice = movementDice;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Player)) return false;
        Player player = (Player) obj;
        return this.name.equals(player.getName());
    }



    @Override
    public Player clone() {
        try{
            Player clone = (Player) super.clone();
            clone.castle = this.castle.clone();
            clone.chest = this.chest.clone();
            clone.movementDice = movementDice != null ? movementDice.clone() : null;

            if(coordinateList != null) {
                clone.coordinateList = new ArrayList<>();

                for (Coordinate coordinate: this.coordinateList) {
                    clone.coordinateList.add(coordinate.clone());
                }
            }

            return clone;
        }
        catch (Exception ex) {
            throw new AssertionError();
        }
    }
}
