package BL.characters_abstract_fabric.concrete_products;

import BL.characters_abstract_fabric.abstract_product.Army;
import Model.Player;

public class Artillery extends Army {

    public Artillery(Player owner, String characterClass) {
        setCharacterType("artilleria");
        setId(owner.getName() + '-' + characterClass);
        setMovements(calculateMovement(1, 4));
        setMovementAdded(this.getMovement());
        setLife(4);
        setLifeAdded(this.getLife());
        setAttack(6);
        setDefense(10);
        setInitialDefense(10);
        setOwner(owner);
        setCharacterClass(characterClass);
    }

}
