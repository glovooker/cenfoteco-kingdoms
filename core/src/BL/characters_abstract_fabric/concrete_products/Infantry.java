package BL.characters_abstract_fabric.concrete_products;

import BL.characters_abstract_fabric.abstract_product.Army;

public class Infantry extends Army {

        public Infantry(String owner, String characterClass) {
            setCharacterType("infanteria");
            setId(owner + '-' + characterClass);
            setMaxMove(calculateMovement(1, 6));
            setLife(5);
            setAttack(3);
            setDefense(5);
            setOwner(owner);
            setCharacterClass(characterClass);
        }
}
