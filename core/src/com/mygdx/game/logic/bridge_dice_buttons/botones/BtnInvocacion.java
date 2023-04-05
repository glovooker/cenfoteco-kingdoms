package com.mygdx.game.logic.bridge_dice_buttons.botones;

import com.mygdx.game.logic.bridge_dice_buttons.abstracta.AbstractaBoton;

public class BtnInvocacion extends AbstractaBoton {

    public BtnInvocacion(String tipoBtn) {
        this.tipoBtn = tipoBtn;
    }

    @Override
    public void onPressed(boolean status) {
        if(status)
        {
            System.out.println("Invocando...");
        } else {
            System.out.println("No");
        }
    }
}
