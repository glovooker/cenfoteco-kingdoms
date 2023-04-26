package BL.characters_abstract_fabric.concrete_products;

import BL.characters_abstract_fabric.abstract_product.Army;
import Model.Player;

public class Infantry extends Army {

        public Infantry(Player owner, String characterClass) {
            setCharacterType("infanteria");
            setId(owner.getName() + '-' + characterClass);
            setMovements(calculateMovement(1, 6));
            setMovementAdded(this.getMovement());
            setLife(5);
            setLifeAdded(this.getLife());
            setAttack(3);
            setDefense(5);
            setInitialDefense(5);
            setOwner(owner);
            setCharacterClass(characterClass);
        }

}
