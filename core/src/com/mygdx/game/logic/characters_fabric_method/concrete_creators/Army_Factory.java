package com.mygdx.game.logic.characters_fabric_method.concrete_creators;

import com.mygdx.game.logic.characters_fabric_method.concrete_products.Artillery;
import com.mygdx.game.logic.characters_fabric_method.concrete_products.Infantry;
import com.mygdx.game.logic.characters_fabric_method.concrete_products.Tank;
import com.mygdx.game.logic.characters_fabric_method.creators.Abstract_Fabric_Method;
import com.mygdx.game.logic.characters_fabric_method.products.Army;

import java.util.Arrays;
import java.util.List;

public class Army_Factory implements Abstract_Fabric_Method {

    @Override
    public Army createArmy(String characterType, String owner) {
        List<String> options;
        if (characterType.equals("infanteria")) {
            options = Arrays.asList("knight", "orcus", "shaman", "squire");
        } else if (characterType.equals("tanque")) {
            options = Arrays.asList("dragon", "golem", "guardian", "kamikaze");
        } else if (characterType.equals("artilleria")) {
            options = Arrays.asList("archer", "daemon", "witch", "wizard");
        } else {
            return null;
        }

        String name = options.get(getRandomNumberInRange(options.size()) - 1);

        if (characterType.equals("infanteria")) {
            return new Infantry(owner, name);
        } else if (characterType.equals("tanque")) {
            return new Tank(owner, name);
        } else if (characterType.equals("artilleria")) {
            return new Artillery(owner, name);
        }
        return null;
    }

    public int getRandomNumberInRange(int max) { return (int) ((Math.random() * ((max - 1) + 1)) + 1); }
}
