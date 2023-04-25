package BL.decorator.concrete_decorator;

import BL.characters_abstract_fabric.abstract_product.Army;
import BL.decorator.decorator.DecoratedArmy;

public class DecoratedArtillery extends DecoratedArmy {

    private Boolean healerActivated;
    private Boolean addAttackActivated;
    private Boolean addDefenseActivated;
    private Boolean lowerDefenseActivated;

    public DecoratedArtillery(Army army) {
        super(army);
        healerActivated = false;
        addAttackActivated = false;
        addDefenseActivated = false;
        lowerDefenseActivated = false;
    }

    public void addSpecialAttack(int specialAttack) {
        switch (specialAttack) {
            case 1:
                healerActivated = true;
                this.getArmy().setSpecialAttack("Healer");
                break;
            case 2:
                addAttackActivated = true;
                this.getArmy().setSpecialAttack("Add attack");
                break;
            case 3:
                addDefenseActivated = true;
                this.getArmy().setSpecialAttack("Add defense");
                break;
            case 4:
                lowerDefenseActivated = true;
                this.getArmy().setSpecialAttack("Lower defense");
                break;
            default:
                throw new IllegalArgumentException("Invalid special attack number for Artillery: " + specialAttack);
        }
    }

    // Healer
    public void heal(Army ally) {
        int newLife = ally.getLife() + 1;
        ally.setLife(newLife);
        System.out.println("Artillery heals an ally, restoring 1 life point");
    }

    // Add 3 points to attack
    public void addAttackPoints(Army ally) {
        int newAttack = ally.getAttack() + 3;
        ally.setAttack(newAttack);
        System.out.println("Artillery adds 3 attack points to an ally");
    }

    // Add 3 points to defense
    public void addDefensePoints(Army ally) {
        int newDefense = ally.getDefense() + 3;
        ally.setDefense(newDefense);
        System.out.println("Artillery adds 3 defense points to an ally");
    }

    // Decrease defense
    public void decreaseDefense(Army enemy) {
        double defenseReductionPercentage = (enemy.getCharacterClass().equals("Infantry")) ? 0.1 : 0.05;
        int defenseReduction = (int) Math.round(enemy.getDefense() * defenseReductionPercentage);
        int newDefense = enemy.getDefense() - defenseReduction;
        enemy.setDefense(newDefense);
        System.out.println("Artillery decreases enemy defense by " + (defenseReductionPercentage * 100) + "% for two turns");
    }

    @Override
    public void attack(Army armyToAttack) {

    }
}
