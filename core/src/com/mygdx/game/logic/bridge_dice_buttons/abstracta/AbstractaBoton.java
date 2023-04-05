package com.mygdx.game.logic.bridge_dice_buttons.abstracta;

public abstract class AbstractaBoton {

    public String tipoBtn;

    public AbstractaBoton(){};

    public abstract void onPressed(boolean status);

    private IEstados globalValidacion;

    public AbstractaBoton(IEstados pIEstInv){
        setGlobalValidacion(pIEstInv);
    }

    public IEstados getGlobalValidacion(){
        return globalValidacion;
    }

    public void setGlobalValidacion(IEstados globalValidacion) {
        this.globalValidacion = globalValidacion;
    }

}
