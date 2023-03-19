package com.mygdx.game.logic.concrete_products;

import com.mygdx.game.logic.products.Army;

public class Tank extends Army{

        public Tank(String id) {
            setCharacterType("tank");
            setId(id);
            setMaxMove(calculateMovement(1, 2));
            setLife(2);
            setAttack(10);
            setDefense(10);
        }
}
