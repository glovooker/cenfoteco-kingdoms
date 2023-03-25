package com.mygdx.game.logic.concrete_creators;
import com.mygdx.game.logic.concrete_products.*;
import com.mygdx.game.logic.creators.Abstract_Fabric_Method;
import com.mygdx.game.logic.products.Army;

import java.util.Arrays;
import java.util.List;

public class Army_Factory implements Abstract_Fabric_Method {

    @Override
    public Army createArmy(String characterType, String owner) {
        List<String> options;
        switch (characterType) {
            case "infantry":
                options = Arrays.asList("knight", "orcus", "shaman", "squire");
                break;
            case "tank":
                options = Arrays.asList("dragon", "golem", "guardian", "kamikaze");
                break;
            case "artillery":
                options = Arrays.asList("archer", "daemon", "witch", "wizard");
                break;
            default:
                return null;
        }
        String name = options.get(getRandomNumberInRange(options.size()) - 1);
        switch (characterType) {
            case "infantry": return new Infantry(owner, name);
            case "tank": return new Tank(owner, name);
            case "artillery": return new Artillery(owner, name);
            default: return null;
        }
    }

    public int getRandomNumberInRange(int max) { return (int) ((Math.random() * ((max - 1) + 1)) + 1); }
}
