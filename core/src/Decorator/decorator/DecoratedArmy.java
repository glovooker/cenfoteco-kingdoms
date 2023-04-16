package Decorator.decorator;

import com.mygdx.game.logic.characters_fabric_method.products.Army;

public abstract class DecoratedArmy extends Army {

    protected Army dArmy;

    public Army getArmy() {
        return dArmy;
    }

}
