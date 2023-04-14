package Model;

public class Army {
    private int movements = 0;
    private int hitPoints = 0;
    private int attacks = 0;
    private int defense = 0;
    private String category = "";
    private String specialAttack = "";


    public Army() {

    }

    public int getMovements() {
        return movements;
    }

    public void setMovements(int movements) {
        this.movements = movements;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getAttacks() {
        return attacks;
    }

    public void setAttacks(int attacks) {
        this.attacks = attacks;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(String specialAttack) {
        this.specialAttack = specialAttack;
    }

    @Override
    public String toString() {
        return "Army{" +
                "movements=" + movements +
                ", hitPoints=" + hitPoints +
                ", attacks=" + attacks +
                ", defense=" + defense +
                ", category='" + category + '\'' +
                ", specialAttack='" + specialAttack + '\'' +
                '}';
    }
}
