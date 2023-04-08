package BL.characters_abstract_fabric;

import BL.characters_abstract_fabric.abstract_product.Army;
import BL.characters_abstract_fabric.concrete_fabric.Fabric_Artilleria;
import BL.characters_abstract_fabric.concrete_fabric.Fabric_Infanteria;
import BL.characters_abstract_fabric.concrete_fabric.Fabric_Tanque;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GestorFabricaAbstracta {
    private static GestorFabricaAbstracta characterManager;
    private final ArrayList<Army> armyPlayerList = new ArrayList<Army>();
    private final ArrayList<Army> armyEnemyList = new ArrayList<Army>();
    private Map<String, Integer> enemyMax = new HashMap<String, Integer>();
    private Map<String, Integer> playerMax = new HashMap<String, Integer>();
    private static Fabric_Artilleria fabricArtilleria = new Fabric_Artilleria();
    private static Fabric_Tanque fabricTanque = new Fabric_Tanque();
    private static Fabric_Infanteria fabricInfanteria = new Fabric_Infanteria();


    public GestorFabricaAbstracta() {
        enemyMax.put("infanteria", 4);
        enemyMax.put("tanque", 4);
        enemyMax.put("artilleria", 4);
        playerMax.put("infanteria", 4);
        playerMax.put("tanque", 4);
        playerMax.put("artilleria", 4);
    }

    public String createArmy(String characterType, String owner) {
        Army army = null;
        if (owner.equals("player")) {
            do{
                if (characterType.equals("infanteria")) {
                    army = fabricInfanteria.createArmy(owner);
                } else if (characterType.equals("tanque")) {
                    army = fabricTanque.createArmy(owner);
                } else if (characterType.equals("artilleria")) {
                    army = fabricArtilleria.createArmy(owner);
                }
            } while (armyPlayerList.contains(army) && playerMax.get(characterType) > 0);
            if (playerMax.get(characterType) > 0 && army != null) {
                playerMax.put(characterType, playerMax.get(characterType) - 1);
                armyPlayerList.add(army);
                return "Personaje " + characterType + " creado correctamente";
            }
        } else {
            do{
                if (characterType.equals("infanteria")) {
                    army = fabricInfanteria.createArmy(owner);
                } else if (characterType.equals("tanque")) {
                    army = fabricTanque.createArmy(owner);
                } else if (characterType.equals("artilleria")) {
                    army = fabricArtilleria.createArmy(owner);
                }
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
