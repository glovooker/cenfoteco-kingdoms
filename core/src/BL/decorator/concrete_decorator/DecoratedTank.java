package BL.decorator.concrete_decorator;

import BL.characters_abstract_fabric.abstract_product.Army;
import BL.decorator.decorator.DecoratedArmy;
import Model.Coordinate;

import java.util.List;

public class DecoratedTank extends DecoratedArmy {

    private Boolean lifeForDoubleMovementActivated;
    private Boolean bombAttackActivated;
    private Boolean protectAllyActivated;

    public DecoratedTank(Army newArmy) {
        super(newArmy);
    }

    public void setSpecialAttack(int specialAttack) {
        switch (specialAttack) {
            case 1:
                lifeForDoubleMovementActivated = true;
                break;
            case 2:
                bombAttackActivated = true;
                break;
            case 3:
                protectAllyActivated = true;
                break;
            default:
                throw new IllegalArgumentException("Invalid special attack number for Tank: " + specialAttack);
        }
    }


    // Sacrifice 1 life for double movement for two turns
    public void sacrificeLifeForDoubleMovement() {
        if (dArmy.getLife() == dArmy.getLife()) {
            int newLife = dArmy.getLife() - 1;
            dArmy.setLife(newLife);
            // Double the movement for the next two turns
            // This should be managed in the game logic, not here
            System.out.println("Tank sacrifices 1 life for double movement for two turns");
        } else {
            System.out.println("Tank cannot sacrifice life as it's not at full life");
        }
    }

    // Kamikaze attack, destroying everything within 3 squares
    public void kamikazeAttack(List<Army> armiesOnBoard, Coordinate tankCoord) {
        System.out.println("Tank performs a kamikaze attack, destroying everything within 3 squares");
        // This method should remove the tank and any affected armies from the board
        // You'll need to adapt this to your game logic
    }

    // Protect an ally, sharing 50% of defense for one turn
    public void protectAlly(Army ally) {
        int sharedDefense = (int) Math.round(dArmy.getDefense() * 0.5);
        int newDefense = ally.getDefense() + sharedDefense;
        ally.setDefense(newDefense);
        System.out.println("Tank shares 50% of its defense with an ally for one turn");
        // The defense bonus should be removed after one turn
        // This should be managed in the game logic, not here
    }

}

