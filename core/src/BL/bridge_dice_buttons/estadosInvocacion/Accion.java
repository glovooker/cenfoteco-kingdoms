package BL.bridge_dice_buttons.estadosInvocacion;

import BL.bridge_dice_buttons.abstracta.IEstados;

public class Accion implements IEstados {
    public boolean status;

    @Override
    public boolean getEstado() {
        return this.status;
    }

    @Override
    public int validar(int dadosAccion) {
        if(dadosAccion >= 1)
        {
            this.status = true;
            dadosAccion-= 1;
        } else {
            this.status = false;
        }
        return dadosAccion;
    }
}
