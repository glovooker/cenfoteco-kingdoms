package BL.bridge_dice_buttons.estadosInvocacion;

import BL.bridge_dice_buttons.abstracta.IEstados;

public class InvocacionInfanteriaEstado implements IEstados {

    public boolean status;

    @Override
    public boolean getEstado() {
        return this.status;
    }

    @Override
    public int validar(int dadosInfanteria) {
        if(dadosInfanteria >= 2)
        {
            this.status = true;
            dadosInfanteria -= 2;
        } else {
            this.status = false;
        }
        return dadosInfanteria;
    }
}
