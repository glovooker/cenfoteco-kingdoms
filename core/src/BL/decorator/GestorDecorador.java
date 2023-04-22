package BL.decorator;

import BL.characters_abstract_fabric.abstract_product.Army;
import BL.decorator.concrete_decorator.DecoratedArtillery;
import BL.decorator.concrete_decorator.DecoratedInfantry;
import BL.decorator.concrete_decorator.DecoratedTank;

import java.util.Random;

public class GestorDecorador {

    private static final Random random = new Random();

    public static Army addRandomSpecialAttack(Army army) {
        int specialAttack = random.nextInt(4) + 1; // Randomly choose a special attack between 1 and 4

        Army decoratedArmy = null;

        switch (army.getCharacterType()) {
            case "infanteria":
                decoratedArmy = applyInfantrySpecialAttack(army, specialAttack);
                break;
            case "artilleria":
                decoratedArmy = applyArtillerySpecialAttack(army, specialAttack);
                break;
            case "tanque":
                decoratedArmy = applyTankSpecialAttack(army, specialAttack);
                break;
            default:
                throw new IllegalArgumentException("Invalid character class: " + army.getCharacterClass());
        }

        return decoratedArmy;
    }

    private static Army applyInfantrySpecialAttack(Army infantry, int specialAttack) {
        DecoratedInfantry decoratedInfantry = new DecoratedInfantry(infantry);
        decoratedInfantry.setSpecialAttack(specialAttack);
        return decoratedInfantry;
    }

    private static Army applyArtillerySpecialAttack(Army artillery, int specialAttack) {
        DecoratedArtillery decoratedArtillery = new DecoratedArtillery(artillery);
        decoratedArtillery.setSpecialAttack(specialAttack);
        return decoratedArtillery;
    }

    private static Army applyTankSpecialAttack(Army tank, int specialAttack) {
        DecoratedTank decoratedTank = new DecoratedTank(tank);
        decoratedTank.setSpecialAttack(specialAttack);
        return decoratedTank;
    }

}


