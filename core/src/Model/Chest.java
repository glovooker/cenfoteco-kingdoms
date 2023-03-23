package Model;

public class Chest {


    private int movementsInChest = 0;//deberia ser un array de enteros con los movimientos

    private int attacksInChest = 0;//deberia ser un array de objetos tipo attaque

    private int specialAttackInChest = 0;//deberia ser un array de objetos tipo ataque especial

    private int gunner = 0;

    private int infantry = 0;

    private int tank = 0;

    private int armyInChest = gunner + infantry + tank;//deberia ser un array de objetos tipo armada

    public final static int ARMY_AMOUNT_MAX = 6;

    public final static int MAX_MOVEMENTS = 3;

    public final static int MAX_ATTACK = 3;

    public final static int MAX_SPECIAL_ATTACK = 2;

    public Chest() {
    }


    public int getGunner() {
        return gunner;
    }

    public void setGunner(int gunner) {
        this.gunner = gunner;
    }

    public int getInfantry() {
        return infantry;
    }

    public void setInfantry(int infantry) {
        this.infantry = infantry;
    }

    public int getTank() {
        return tank;
    }

    public void setTank(int tank) {
        this.tank = tank;
    }

    public int getMovementsInChest() {
        return movementsInChest;
    }

    public void setMovementsInChest(int movementsInChest) {
        this.movementsInChest = movementsInChest;
    }

    public int getAttacksInChest() {
        return attacksInChest;
    }

    public void setAttacksInChest(int attacksInChest) {
        this.attacksInChest = attacksInChest;
    }

    public int getSpecialAttackInChest() {
        return specialAttackInChest;
    }

    public void setSpecialAttackInChest(int specialAttackInChest) {
        this.specialAttackInChest = specialAttackInChest;
    }


}
