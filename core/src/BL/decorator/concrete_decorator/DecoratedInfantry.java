package BL.decorator.concrete_decorator;

import BL.characters_abstract_fabric.abstract_product.Army;
import BL.decorator.decorator.DecoratedArmy;
import Model.Coordinate;

public class DecoratedInfantry extends DecoratedArmy {

    private int originalAttack;
    private int originalDefense;
    private Boolean rangedAttackActivated;
    private Boolean doubleAttackActivated;
    private Boolean doubleDefenseActivated;
    private Boolean lowerDefenseActivated;

    public DecoratedInfantry(Army newArmy) {
        super(newArmy);
        originalAttack = newArmy.getAttack();
        originalDefense = newArmy.getDefense();
    }

    public void addSpecialAttack(int specialAttack) {
        switch (specialAttack) {
            case 1:
                rangedAttackActivated = true;
                this.getArmy().setSpecialAttack("Ranged attack");
                break;
            case 2:
                doubleAttackActivated = true;
                this.getArmy().setSpecialAttack("Double attack");
                break;
            case 3:
                doubleDefenseActivated = true;
                this.getArmy().setSpecialAttack("Double defense");
                break;
            case 4:
                lowerDefenseActivated = true;
                this.getArmy().setSpecialAttack("Lower defense");
                break;
            default:
                throw new IllegalArgumentException("Invalid special attack number for Infantry: " + specialAttack);
        }
    }

    public void rangedAttack(Coordinate currentArmyPosition, Coordinate targetArmyPosition) {
        int attackerColumn = currentArmyPosition.getY();
        int attackerRow = currentArmyPosition.getX();
        int targetColumn = targetArmyPosition.getY();
        int targetRow= targetArmyPosition.getX();

        if ((targetRow == attackerRow && (targetColumn == attackerColumn + 2 || targetColumn == attackerColumn - 2))
                || ((targetRow == attackerRow + 2 || targetRow == attackerRow - 2) && targetColumn == attackerColumn)
                || ((targetRow == attackerRow + 2 || targetRow == attackerRow - 2) && (targetColumn == attackerColumn + 2 || targetColumn == attackerColumn - 2))) {

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

