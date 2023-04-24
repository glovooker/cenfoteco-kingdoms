package BL.characters_abstract_fabric.concrete_products;

import BL.characters_abstract_fabric.abstract_product.Army;
import Model.Player;

public class Infantry extends Army {

        public Infantry(Player owner, String characterClass) {
            setCharacterType("infanteria");
            setId(owner.getName() + '-' + characterClass);
            setMovements(calculateMovement(1, 6));
            setLife(5);
            setAttack(3);
            setDefense(5);
            setOwner(owner);
            setCharacterClass(characterClass);
        }

    @Override
    public void attack(Army armyToAttack) {
        if(armyToAttack.getDefense() >= 1){
            armyToAttack.setDefense(armyToAttack.getDefense() - 1);
        }else{
            armyToAttack.setLife(armyToAttack.getLife() - 1);
        }
    }
}
