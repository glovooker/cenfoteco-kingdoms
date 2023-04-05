package com.mygdx.game.logic.characters_fabric_method.concrete_products;

import com.mygdx.game.logic.characters_fabric_method.products.Army;

public class Artillery extends Army {

    public Artillery(String owner, String characterClass) {
        setCharacterType("artillery");
        setId(owner + '-' + characterClass);
        setMaxMove(calculateMovement(1, 4));
        setLife(4);
        setAttack(6);
        setDefense(10);
        setOwner(owner);
        setCharacterClass(characterClass);
    }
}
