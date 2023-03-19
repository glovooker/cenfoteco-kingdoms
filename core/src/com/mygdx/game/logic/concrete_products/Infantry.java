package com.mygdx.game.logic.concrete_products;

import com.mygdx.game.logic.products.Army;

public class Infantry extends Army {

        public Infantry(String id) {
            setCharacterType("infantry");
            setId(id);
            setMaxMove(calculateMovement(1, 6));
            setLife(5);
            setAttack(3);
            setDefense(5);
        }
}
