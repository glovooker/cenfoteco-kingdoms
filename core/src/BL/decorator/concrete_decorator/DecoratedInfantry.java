package BL.decorator.concrete_decorator;

import BL.characters_abstract_fabric.abstract_product.Army;
import BL.decorator.decorator.DecoratedArmy;
import Model.Coordinate;
import Model.Player;

public class DecoratedInfantry extends DecoratedArmy {

    private int originalAttack;
    private int originalDefense;

    private Boolean rangedAttackActivated;

    private Boolean healer;

    private Boolean addThreePointsToAttack;

    private Boolean addThreePointsToDefense;

    private Boolean lowDefense;

    public DecoratedInfantry(Army newArmy) {
        super(newArmy);
        originalAttack = newArmy.getAttack();
        originalDefense = newArmy.getDefense();
    }

    public void addSpecialAttack(int specialAttack) {
        switch (specialAttack) {
            case 1:
                healer = true;
                this.getArmy().setSpecialAttack("Healer");
                break;
            case 2:
                addThreePointsToDefense = true;
                this.getArmy().setSpecialAttack("Add three points to defense");
                break;
            case 3:
                addThreePointsToAttack = true;
                this.getArmy().setSpecialAttack("Add three points to attack");
                break;
            case 4:
                lowDefense = true;
                this.getArmy().setSpecialAttack("Low defense");
                break;

            default:
                throw new IllegalArgumentException("Invalid special attack number for Infantry: " + specialAttack);
        }
    }


    public void doubleAttackPower() {
        System.out.println("Infantry performs an attack with double power");
        this.setAttack(originalAttack * 2);
    }

    public void doubleDefensePower() {
        System.out.println("Infantry doubles its defense power for one turn");
        this.setDefense(originalDefense * 2);
    }

    public void lowerEnemyDefense(Army enemy) {
        System.out.println("Lowering the enemy's defense");
        double newDefense;
        if (enemy.getCharacterClass().equals("Infantry")) {
            newDefense = enemy.getDefense() * 0.9;
        } else if (enemy.getCharacterClass().equals("Artillery")) {
            newDefense = enemy.getDefense() * 0.95;
        } else {
            return;
        }
        enemy.setDefense((int) newDefense);
        // Implement any additional logic, like resetting defense after two turns
    }

    public void reset() {
        dArmy.setAttack(originalAttack);
        dArmy.setDefense(originalDefense);
    }

    @Override
    public void attack(Army armyToAttack) {

    }
}

