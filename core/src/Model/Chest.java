package Model;

import BL.bridge_dice_buttons.dadoMovimiento.DadoMovimiento;
import BL.characters_abstract_fabric.abstract_product.Army;

import java.util.ArrayList;

public class Chest implements Cloneable {

    private ArrayList<DadoMovimiento> movementsInChest = new ArrayList<>();

    private int attacksInChest = 0;

    private int specialAttackInChest = 0;
    private int gunner = 0;

    private int infantry = 0;

    private int tank = 0;

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

    public ArrayList<DadoMovimiento> getMovementsInChest() {
        return movementsInChest;
    }

    public void setMovementsInChest(DadoMovimiento movementsInChest) {
        this.movementsInChest.add(movementsInChest);
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

    public int getArmyInChest() {
        return gunner + infantry + tank;
    }

    @Override
    public String toString() {
        return "Chest{" +
                "movementsInChest=" + movementsInChest.size() +
                ", attacksInChest=" + attacksInChest +
                ", specialAttackInChest=" + specialAttackInChest +
                ", gunner=" + gunner +
                ", infantry=" + infantry +
                ", tank=" + tank +
                ", armyInChest=" + this.getArmyInChest() +
                '}';
    }

    @Override
    protected Chest clone() {
        try {
            Chest clone = (Chest)super.clone();

            if(movementsInChest != null) {
                clone.movementsInChest = new ArrayList<>();

                for (DadoMovimiento dadoMovimiento : movementsInChest) {
                    clone.movementsInChest.add(dadoMovimiento.clone());
                }
            }

            return clone;
        }
        catch(CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
