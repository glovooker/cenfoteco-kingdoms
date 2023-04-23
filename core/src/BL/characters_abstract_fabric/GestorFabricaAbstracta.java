package BL.characters_abstract_fabric;

import BL.characters_abstract_fabric.abstract_product.Army;
import BL.characters_abstract_fabric.concrete_fabric.Fabric_Artilleria;
import BL.characters_abstract_fabric.concrete_fabric.Fabric_Infanteria;
import BL.characters_abstract_fabric.concrete_fabric.Fabric_Tanque;
import Model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GestorFabricaAbstracta {
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

    public Army createArmy(String characterType, Player owner) {
        Army army = null;
        String ownerName = owner.getName();
        int playerLuckyNumber = owner.getLuckyNumber();
        if (playerLuckyNumber == 1) {
            do{
                if (characterType.equals("infanteria")) {
                    army = fabricInfanteria.createArmy(ownerName);
                } else if (characterType.equals("tanque")) {
                    army = fabricTanque.createArmy(ownerName);
                } else if (characterType.equals("artilleria")) {
                    army = fabricArtilleria.createArmy(ownerName);
                }
            } while (armyPlayerList.contains(army) && playerMax.get(characterType) > 0);
            if (playerMax.get(characterType) > 0 && army != null) {
                playerMax.put(characterType, playerMax.get(characterType) - 1);
                armyPlayerList.add(army);
                return army;
            }
        } else {
            do{
                if (characterType.equals("infanteria")) {
                    army = fabricInfanteria.createArmy(ownerName);
                } else if (characterType.equals("tanque")) {
                    army = fabricTanque.createArmy(ownerName);
                } else if (characterType.equals("artilleria")) {
                    army = fabricArtilleria.createArmy(ownerName);
                }
            } while (armyEnemyList.contains(army) && enemyMax.get(characterType) > 0);
            if (enemyMax.get(characterType) > 0 && army != null) {
                enemyMax.put(characterType, enemyMax.get(characterType) - 1);
                armyEnemyList.add(army);
                return army;
            }
        }
        return null;
    }

    public ArrayList<Army> getArmyPlayerList(Player owner){
        if (owner.getName().equals("player1")) {
            return armyPlayerList;
        } else if (owner.getName().equals("player2")) {
            return armyEnemyList;
        }
        return null;
    }
}
