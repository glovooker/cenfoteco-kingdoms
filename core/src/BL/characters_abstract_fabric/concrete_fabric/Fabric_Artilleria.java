package BL.characters_abstract_fabric.concrete_fabric;

import BL.characters_abstract_fabric.abstract_fabric.Abstract_Fabric_Army;
import BL.characters_abstract_fabric.abstract_product.Army;
import BL.characters_abstract_fabric.concrete_products.Artillery;
import Model.Player;

import java.util.Arrays;
import java.util.List;

public class Fabric_Artilleria implements Abstract_Fabric_Army {
    @Override
    public Army createArmy(Player owner) {
        List<String> units = Arrays.asList("archero", "daemon", "bruja");//, "mago");
        String name = units.get(getRandomNumberInRange(units.size()) - 1);

        return new Artillery(owner, name);
    }
    public int getRandomNumberInRange(int max) { return (int) ((Math.random() * ((max - 1) + 1)) + 1); }
}
