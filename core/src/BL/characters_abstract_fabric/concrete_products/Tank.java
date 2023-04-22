package BL.characters_abstract_fabric.concrete_products;

import BL.characters_abstract_fabric.abstract_product.Army;

public class Tank extends Army {

        public Tank(String owner, String characterClass) {
            setCharacterType("tanque");
            setId(owner + '-' + characterClass);
            setMaxMove(calculateMovement(1, 2));
            setLife(2);
            setAttack(10);
            setDefense(10);
            setOwner(owner);
            setCharacterClass(characterClass);
        }

    @Override
    public void attack(Army armyToAttack) {
        if(armyToAttack.getDefense() >= 3){
            armyToAttack.setDefense(armyToAttack.getDefense() - 3);
        }else{
            armyToAttack.setLife(armyToAttack.getLife() - 3);
        }
    }
}
