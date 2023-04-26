package BL.decorator;

import BL.characters_abstract_fabric.abstract_product.Army;
import BL.decorator.concrete_decorator.DecoratedArtillery;
import BL.decorator.concrete_decorator.DecoratedInfantry;
import BL.decorator.concrete_decorator.DecoratedTank;

import java.util.Random;

public class GestorDecorador {

    private static final Random random = new Random();

    public GestorDecorador() { }

    public Army addRandomSpecialAttack(Army army) {
        int specialAttack = random.nextInt(4) + 1;

        switch (army.getCharacterType()) {
            case "infanteria":
                return applyInfantrySpecialAttack(army, specialAttack);
            case "artilleria":
                return applyArtillerySpecialAttack(army, specialAttack);
            case "tanque":
                return applyTankSpecialAttack(army, specialAttack);
            default:
                throw new IllegalArgumentException("Invalid character class: " + army.getCharacterClass());
        }
    }

    private static Army applyInfantrySpecialAttack(Army infantry, int specialAttack) {
        DecoratedInfantry decoratedInfantry = new DecoratedInfantry(infantry);
        decoratedInfantry.addSpecialAttack(specialAttack);
        return decoratedInfantry.getArmy();
    }

    private static Army applyArtillerySpecialAttack(Army artillery, int specialAttack) {
        DecoratedArtillery decoratedArtillery = new DecoratedArtillery(artillery);
        decoratedArtillery.addSpecialAttack(specialAttack);
        return decoratedArtillery.getArmy();
    }

    private static Army applyTankSpecialAttack(Army tank, int specialAttack) {
        DecoratedTank decoratedTank = new DecoratedTank(tank);
        decoratedTank.addSpecialAttack(specialAttack);
        return decoratedTank.getArmy();
    }

}


