package Model;

public class Player {
    private Chest chest;
    private Castle castle;
    private boolean turn;
    private final int luckyNumber;

    private String name;

    public Player(int luckyNumber) {
        this.chest = new Chest();
        this.castle = new Castle();
        this.turn = false;
        this.luckyNumber = luckyNumber;
        this.name = "Player " + luckyNumber;
    }

    public Chest getChest() {
        return chest;
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

    @Override
    public String toString() {
        return "Player{" +
                "chest=" + chest.toString() +
                ", castle=" + castle.toString() +
                "Player=" + this.name +
                '}';
    }
}
