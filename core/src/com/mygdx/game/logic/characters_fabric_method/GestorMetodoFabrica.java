package com.mygdx.game.logic.characters_fabric_method;

import com.mygdx.game.logic.characters_fabric_method.concrete_creators.Army_Factory;
import com.mygdx.game.logic.characters_fabric_method.products.Army;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GestorMetodoFabrica {
    private static GestorMetodoFabrica characterManager;
    private final ArrayList<Army> armyPlayerList = new ArrayList<Army>();
    private final ArrayList<Army> armyEnemyList = new ArrayList<Army>();
    private Map<String, Integer> enemyMax = new HashMap<String, Integer>();
    private Map<String, Integer> playerMax = new HashMap<String, Integer>();
    private static Army_Factory armyFactory = new Army_Factory();

    public GestorMetodoFabrica() {
        enemyMax.put("infantry", 4);
        enemyMax.put("tank", 4);
        enemyMax.put("artillery", 4);
        playerMax.put("infantry", 4);
        playerMax.put("tank", 4);
        playerMax.put("artillery", 4);
    }

    public String createArmy(String characterType, String owner) {
        Army army = null;
        if (owner.equals("player")) {
            do{
                army = armyFactory.createArmy(characterType, owner);
            } while (armyPlayerList.contains(army) && playerMax.get(characterType) > 0);
            if (playerMax.get(characterType) > 0 && army != null) {
                playerMax.put(characterType, playerMax.get(characterType) - 1);
                armyPlayerList.add(army);
                return "Personaje " + characterType + " creado correctamente";
            }
        } else {
            do{
                army = armyFactory.createArmy(characterType, owner);
            } while (armyEnemyList.contains(army) && enemyMax.get(characterType) > 0);
            if (enemyMax.get(characterType) > 0 && army != null) {
                enemyMax.put(characterType, enemyMax.get(characterType) - 1);
                armyEnemyList.add(army);
                return "Personaje " + characterType + " creado correctamente";
            }
        }
        return "No hay espacio para crear más personajes " + characterType + " para el jugador " + owner;
    }

    public ArrayList<Army> getArmyPlayerList() {
        return armyPlayerList;
    }

    public ArrayList<Army> getArmyEnemyList() {
        return armyEnemyList;
    }
}
