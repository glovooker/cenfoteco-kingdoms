package BL.bridge_dice_buttons.estadosInvocacion;

import BL.bridge_dice_buttons.abstracta.IEstados;

public class InvocacionArtilleriaEstado implements IEstados {

    public boolean status;

    @Override
    public boolean getEstado() {
        return this.status;
    }

    @Override
    public int validar(int dadosArtilleria) {
        if(dadosArtilleria>= 3)
        {
            this.status = true;
            dadosArtilleria-= 3;
        } else {
            this.status = false;
        }
        return dadosArtilleria;
    }
}
