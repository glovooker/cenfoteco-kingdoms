package BL.decorator.decorator;

import BL.characters_abstract_fabric.abstract_product.Army;

public abstract class DecoratedArmy extends Army {

    protected Army dArmy;

    public DecoratedArmy(Army newArmy) {
        this.dArmy = newArmy;
    }

    public Army getArmy() {
        return dArmy;
    }

}

