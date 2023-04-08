package BL.bridge_dice_buttons.botones;

import BL.bridge_dice_buttons.abstracta.AbstractaBoton;

public class BtnInvocacion extends AbstractaBoton {

    public BtnInvocacion(String tipoBtn) {
        this.tipoBtn = tipoBtn;
    }

    @Override
    public String onPressed(boolean status) {
        if(status)
        {
            return this.tipoBtn.toLowerCase();
        } else {
            return null;
        }
    }
}
