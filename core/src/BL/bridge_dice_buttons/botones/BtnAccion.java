package BL.bridge_dice_buttons.botones;

import BL.bridge_dice_buttons.abstracta.AbstractaBoton;

public class BtnAccion extends AbstractaBoton {
    public BtnAccion(String tipoBtn) {
        this.tipoBtn = tipoBtn;
    }

    @Override
    public String onPressed(boolean status) {
        if(status)
        {
            return this.tipoBtn;
        }

        return null;
    }
}
