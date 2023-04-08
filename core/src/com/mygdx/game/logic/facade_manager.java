package com.mygdx.game.logic;

import com.mygdx.game.logic.bridge_dice_buttons.GestorBridge;
import com.mygdx.game.logic.characters_fabric_method.GestorMetodoFabrica;
import com.mygdx.game.logic.characters_fabric_method.products.Army;

import java.util.ArrayList;

public class facade_manager {
    private static GestorBridge gestorBridge;
    private static GestorMetodoFabrica gestorMetodoFabrica;

    public facade_manager() {
        gestorBridge = new GestorBridge();
        gestorMetodoFabrica = new GestorMetodoFabrica();

        gestorBridge.iniciarBotones();
    }

    public String mostrarMenu() {
        return ("Bienvenido, seleccione una opcion: \n" +
                "1. Lanzar dados \n" +
                "2. Invocar infanteria \n" +
                "3. Invocar tanque \n" +
                "4. Invocar artilleria \n" +
                "5. Ver ejercito \n" +
                "6. Atacar \n" +
                "7. Ataque especial \n" +
                "8. Mover \n" +
                "9. Salir \n");
    }

    public String ejectuar_funcion(int pIdx) {
        switch (pIdx) {
            case 1: return gestorBridge.lanzarDado();
            case 2: return gestorMetodoFabrica.createArmy(gestorBridge.invocarInfanteria(), "player");
            case 3: return gestorMetodoFabrica.createArmy(gestorBridge.invocarTanque(), "player");
            case 4: return gestorMetodoFabrica.createArmy(gestorBridge.invocarArtilleria(), "player");
            case 5:
                ArrayList<Army> ejercito = gestorMetodoFabrica.getArmyPlayerList();
                String mensaje = "";
                for (Army army : ejercito) {
                    mensaje += army.toString() + "\n";
                }
                return mensaje;
            case 6: return "";
            case 7: return "";
            case 8: return "";
            case 9: return "Adios";
            default: return "Opcion invalida";
        }
    }
}