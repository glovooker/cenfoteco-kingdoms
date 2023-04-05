package com.mygdx.game.logic.characters_fabric_method.concrete_products;

import com.mygdx.game.logic.characters_fabric_method.products.Army;

public class Infantry extends Army {

        public Infantry(String owner, String characterClass) {
            setCharacterType("infantry");
            setId(owner + '-' + characterClass);
            setMaxMove(calculateMovement(1, 6));
            setLife(5);
            setAttack(3);
            setDefense(5);
            setOwner(owner);
            setCharacterClass(characterClass);
        }
}
