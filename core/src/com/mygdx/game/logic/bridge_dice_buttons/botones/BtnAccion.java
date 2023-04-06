package com.mygdx.game.logic.bridge_dice_buttons.botones;

import com.mygdx.game.logic.bridge_dice_buttons.abstracta.AbstractaBoton;

public class BtnAccion extends AbstractaBoton {

    public BtnAccion(String tipoBtn) {
        this.tipoBtn = tipoBtn;
    }

    @Override
    public String onPressed(boolean status) {
        if(status)
        {
            System.out.println("Accionando...");
        } else {
            System.out.println("No");
        }
        return null;
    }
}
