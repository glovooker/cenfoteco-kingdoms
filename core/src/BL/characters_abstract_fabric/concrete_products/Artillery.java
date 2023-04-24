package BL.characters_abstract_fabric.concrete_products;

import BL.characters_abstract_fabric.abstract_product.Army;
import Model.Player;

public class Artillery extends Army {

    public Artillery(Player owner, String characterClass) {
        setCharacterType("artilleria");
        setId(owner.getName() + '-' + characterClass);
        setMovements(calculateMovement(1, 4));
        setLife(4);
        setAttack(6);
        setDefense(10);
        setOwner(owner);
        setCharacterClass(characterClass);
    }

    @Override
    public void attack(Army armyToAttack) {
        if(armyToAttack.getDefense() >= 2){
            armyToAttack.setDefense(armyToAttack.getDefense() - 2);
        }else{
            armyToAttack.setLife(armyToAttack.getLife() - 2);
        }
    }
}
