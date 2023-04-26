package BL.characters_abstract_fabric.concrete_products;

import BL.characters_abstract_fabric.abstract_product.Army;
import Model.Player;

public class Tank extends Army {

        public Tank(Player owner, String characterClass) {
            setCharacterType("tanque");
            setId(owner.getName() + '-' + characterClass);
            setMovements(calculateMovement(1, 2));
            setMovementAdded(this.getMovement());
            setLife(2);
            setLifeAdded(this.getLife());
            setAttack(10);
            setDefense(10);
            setInitialDefense(10);
            setOwner(owner);
            setCharacterClass(characterClass);
        }

}
