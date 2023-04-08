package BL;

import BL.bridge_dice_buttons.GestorBridge;
import BL.characters_abstract_fabric.GestorFabricaAbstracta;
import BL.characters_abstract_fabric.abstract_product.Army;

import java.util.ArrayList;

public class facade_manager {
    private static GestorBridge gestorBridge;
    private static GestorFabricaAbstracta gestorFabricaAbstracta;

    public facade_manager() {
        gestorBridge = new GestorBridge();
        gestorFabricaAbstracta = new GestorFabricaAbstracta();
        gestorBridge.iniciarBotones();
    }

    public String lanzarDados(){
        return gestorBridge.lanzarDado();
    }

    public String invocarInfanteria(){
        if(gestorBridge.invocarInfanteria() == null){
            return "No se puede invocar infanteria";
        } else {
            return gestorFabricaAbstracta.createArmy(gestorBridge.invocarInfanteria(), "player");
        }
    }
    public String invocarArtilleria(){
        if(gestorBridge.invocarArtilleria() == null){
            return "No se puede invocar artilleria";
        } else {
            return gestorFabricaAbstracta.createArmy(gestorBridge.invocarArtilleria(), "player");
        }
    }
    public String invocarTanque(){
        if(gestorBridge.invocarTanque() == null){
            return "No se puede invocar tanque";
        } else {
            return gestorFabricaAbstracta.createArmy(gestorBridge.invocarTanque(), "player");
        }
    }

    public String obtenerArmada(){
        ArrayList<Army> ejercito = gestorFabricaAbstracta.getArmyPlayerList();
        String mensaje = "";
        for (Army army : ejercito) {
            mensaje += army.toString() + "\n";
        }
        return mensaje;
    }

    public ArrayList<Integer> almacenarDados(){
        return gestorBridge.almacenarCofre();
    }

}