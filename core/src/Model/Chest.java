package Model;

import BL.characters_abstract_fabric.abstract_product.Army;

public class Chest {

    private int movementsInChest = 0;

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

    public int getArmyInChest() {
        return gunner + infantry + tank;
    }

    public void cleanChest(Army armyInvoked, GameState gameState){
        Chest chest = gameState.getPlayerInTurn().getChest();

        if(armyInvoked.getCharacterClass().equals("ratallero") || armyInvoked.getCharacterClass().equals("orco") || armyInvoked.getCharacterClass().equals("chaman") || armyInvoked.getCharacterClass().equals("escudero")){
            chest.setInfantry(chest.getInfantry() - 2);
        }

        if(armyInvoked.getCharacterClass().equals("archero") || armyInvoked.getCharacterClass().equals("daemon") || armyInvoked.getCharacterClass().equals("bruja")){
            chest.setGunner(chest.getGunner() - 3);
        }

        if(armyInvoked.getCharacterClass().equals("dragon") || armyInvoked.getCharacterClass().equals("golem") || armyInvoked.getCharacterClass().equals("guardian") || armyInvoked.getCharacterClass().equals("kamikaze")){
            chest.setTank(chest.getTank() - 4);
        }
    }

    @Override
    public String toString() {
        return "Chest{" +
                "movementsInChest=" + movementsInChest +
                ", attacksInChest=" + attacksInChest +
                ", specialAttackInChest=" + specialAttackInChest +
                ", gunner=" + gunner +
                ", infantry=" + infantry +
                ", tank=" + tank +
                ", armyInChest=" + this.getArmyInChest() +
                '}';
    }
}
