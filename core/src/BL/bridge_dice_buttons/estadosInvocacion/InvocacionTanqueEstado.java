package BL.bridge_dice_buttons.estadosInvocacion;

import BL.bridge_dice_buttons.abstracta.IEstados;

public class InvocacionTanqueEstado  implements IEstados {
    public boolean status;

    @Override
    public boolean getEstado() {
        return this.status;
    }

    @Override
    public int validar(int dadosTanque) {
        if(dadosTanque >= 4)
        {
            this.status = true;
            dadosTanque -= 4;
        } else {
            this.status = false;
        }
        return dadosTanque;
    }
}