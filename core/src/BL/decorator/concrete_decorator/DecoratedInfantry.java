package BL.decorator.concrete_decorator;

import BL.characters_abstract_fabric.abstract_product.Army;
import BL.decorator.decorator.DecoratedArmy;

public class DecoratedInfantry extends DecoratedArmy {
    private int originalAttack;
    private int originalDefense;
    private Boolean rangedAttackActivated;
    private Boolean doubleAttackActivated;
    private Boolean doubleDefenseActivated;
    private Boolean lowerDefenseActivated;

    public DecoratedInfantry(Army newArmy) {
        super(newArmy);
    }

    public void setSpecialAttack(int specialAttack) {
        switch (specialAttack) {
            case 1:
                rangedAttackActivated = true;
                break;
            case 2:
                doubleAttackActivated = true;
                break;
            case 3:
                doubleDefenseActivated = true;
                break;
            case 4:
                lowerDefenseActivated = true;
                break;
            default:
                throw new IllegalArgumentException("Invalid special attack number for Infantry: " + specialAttack);
        }
    }

//    public void rangedAttack(List<Army> armiesOnBoard, Coordinate attackerCoord, Coordinate targetCoord) {
//        int attackerRow = attackerCoord.getY();
//        int attackerColumn = attackerCoord.getX();
//        int targetRow = targetCoord.getY();
//        int targetColumn = targetCoord.getX();
//
//        int distance = Math.abs(attackerRow - targetRow) + Math.abs(attackerColumn - targetColumn);
//
//        if (distance <= 2) {
//            Army targetArmy = findArmyAtCoordinate(armiesOnBoard, targetCoord);
//            if (targetArmy != null) {
//                System.out.println("Infantry performs a ranged attack, hitting two squares away");
//                int newLife = targetArmy.getLife() - dArmy.getAttack();
//                targetArmy.setLife(newLife);
//            } else {
//                System.out.println("Target cell is empty");
//            }
//        } else {
//            System.out.println("Target is out of range");
//        }
//    }
//
//    private Army findArmyAtCoordinate(List<Army> armies, Coordinate targetCoord) {
//        for (Army army : armies) {
//            if (army.getCoordinate().equals(targetCoord)) {
//                return army;
//            }
//        }
//        return null;
//    }

    public void doubleAttackPower() {
        System.out.println("Infantry performs an attack with double power");
        dArmy.setAttack(originalAttack * 2);
    }

    public void doubleDefensePower() {
        System.out.println("Infantry doubles its defense power for one turn");
        dArmy.setDefense(originalDefense * 2);
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
}

