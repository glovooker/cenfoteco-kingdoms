package View;

import BL.GameController;
import BL.bridge_dice_buttons.dadoMovimiento.DadoMovimiento;
import Model.Chest;
import View.Components.ButtonComponent;
import View.Components.HudChest;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.ArrayList;

public class ChestView {

    private ButtonComponent buttonAddArmy;

    private ButtonComponent buttonGetGunner;

    private ButtonComponent buttonGetInfantry;

    private ButtonComponent buttonGetTank;

    private ButtonComponent buttonGetAttack;

    private ButtonComponent buttonGetSpecialAttack;

    private ButtonComponent buttonGetMovement;

    private Label labelInfantry;

    private Label labelGunner;

    private Label labelTank;

    private Label labelMovements;

    private Label labelAttack;

    private Label labelSpecialAttack;

    private HudChest hud;

    private Stage stage;

    private final GameController gameController = GameController.getInstance();

    public ChestView(Stage stage){
        this.stage = stage;
        hud  = new HudChest(this.stage);
        labelInfantry = hud.getLabelInfantry();
        labelAttack = hud.getLabelAttack();
        labelGunner = hud.getLabelGunner();
        labelMovements = hud.getLabelMovements();
        labelTank = hud.getLabelTank();
        labelSpecialAttack = hud.getLabelSpecialAttack();
        defineButtons();
    }

    public HudChest getHudChest(){
        return this.hud;
    }

    public void defineButtons(){
        buttonAddArmy = new ButtonComponent(this.stage, "chestClosed.png", 80, 100, 430,50, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                addArmy(gameController.almacenarDados());
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        buttonGetInfantry = new ButtonComponent(this.stage, "escudero.png", 130, 130, 570, 120, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //getInfantry();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        buttonGetGunner = new ButtonComponent(this.stage, "arquero.png", 120, 120, 680, 120, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
               // getGunner();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        buttonGetTank = new ButtonComponent(this.stage, "golem.png", 120, 120, 780, 120, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
               // getTank();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        buttonGetAttack = new ButtonComponent(this.stage, "attack.png", 80, 80, 50, 150, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
               // getAttack();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        buttonGetSpecialAttack = new ButtonComponent(this.stage, "specialAttack.png", 80, 80, 150, 150, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //getSpecialAttack();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        buttonGetMovement = new ButtonComponent(this.stage, "movements.png", 80, 80, 250, 150, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //getMovement();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
    private void addArmy(ArrayList<Integer> dados){
        Chest chest = this.gameController.getGameState().getPlayerInTurn().getChest();
        int army = chest.getArmyInChest();
        int movements = chest.getMovementsInChest().size();
        int attacks = chest.getAttacksInChest();
        int specialAttacks = chest.getSpecialAttackInChest();

        int incomingInfantries = dados.get(0);
        int incomingGunners = dados.get(1);
        int incomingTanks = dados.get(2);

        if (army + incomingInfantries + incomingGunners + incomingTanks > chest.ARMY_AMOUNT_MAX) {
            if (incomingInfantries == 1 && incomingGunners == 1){
                incomingInfantries = 0;
            } else if (incomingInfantries == 1 && incomingTanks == 1){
                incomingInfantries = 0;
            } else if (incomingGunners == 1 && incomingTanks == 1){
                incomingGunners = 0;
            } else if (incomingInfantries == 2){
                incomingInfantries = 1;
            } else if (incomingGunners == 2){
                incomingGunners = 1;
            } else if (incomingTanks == 2){
                incomingTanks = 1;
            }
        }

        if (army < Chest.ARMY_AMOUNT_MAX) {
            if (incomingInfantries > 0) {
                addingInfantries(incomingInfantries, chest);
            }
            if (incomingGunners > 0) {
                addingGunner(incomingGunners, chest);
            }
            if (incomingTanks > 0) {
                addingTank(incomingTanks, chest);
            }
        } else {
            //TODO retumba todo la maire
            System.out.println("No se pueden añadir mas elementos");
        }

        if (attacks < Chest.MAX_ATTACK) {
            if (dados.get(3) > 0) {
                addingAttacks();
            }
        } else {
            System.out.println("No se pueden añadir mas elementos");
        }

        if (specialAttacks < Chest.MAX_SPECIAL_ATTACK) {
            if (dados.get(4) > 0) {
                addingSpecialAttacks();
            }
        } else {
            System.out.println("No se pueden añadir mas elementos");
        }

        if (movements < Chest.MAX_MOVEMENTS) {
            if (dados.get(5) > 0) {
                DadoMovimiento newDadoMovimiento = new DadoMovimiento();
                newDadoMovimiento.setMovimiento(dados.get(5));
                addingMovements(newDadoMovimiento);
            }
        } else {
            System.out.println("No se pueden añadir mas elementos");
        }
    }

    private void addingSpecialAttacks(){
        Chest chest = gameController.getPlayerInTurn().getChest();

        chest.setSpecialAttackInChest(chest.getSpecialAttackInChest() + 1);
        labelSpecialAttack.setText(chest.getSpecialAttackInChest());
        //se inserta el ataque especial en el array
    }

    private void addingAttacks(){
        Chest chest = gameController.getPlayerInTurn().getChest();

        chest.setAttacksInChest(chest.getAttacksInChest() + 1);
        labelAttack.setText(chest.getAttacksInChest());
        //se inserta el tanque en el array
    }

    private void addingTank(int tanksIncoming, Chest chest){
        chest.setTank(chest.getTank() + tanksIncoming);
        labelTank.setText(chest.getTank());
    }

    private void addingGunner(int gunnersIncoming, Chest chest){
        chest.setGunner(chest.getGunner() + gunnersIncoming);
        labelGunner.setText(chest.getGunner());
    }

    private void addingInfantries(int infantriesIncoming, Chest chest){
        chest.setInfantry(chest.getInfantry() + infantriesIncoming);
        labelInfantry.setText(chest.getInfantry());

    }

    private void addingMovements(DadoMovimiento movementsInChest){
        Chest chest = gameController.getPlayerInTurn().getChest();

        chest.setMovementsInChest(movementsInChest);
        labelMovements.setText(chest.getMovementsInChest().size());

    }

    private void getInfantry(){
        Chest chest = gameController.getPlayerInTurn().getChest();

        chest.setInfantry(chest.getInfantry() - 1);
        labelInfantry.setText(chest.getInfantry());
    }

    private void getGunner(){
        Chest chest = gameController.getPlayerInTurn().getChest();

        chest.setGunner(chest.getGunner() - 1);
        labelGunner.setText(chest.getGunner());
    }

    private void getTank(){
        Chest chest = gameController.getPlayerInTurn().getChest();

        chest.setTank(chest.getTank() - 1);
        labelTank.setText(chest.getTank());
    }

    private void getAttack(){
        Chest chest = gameController.getPlayerInTurn().getChest();

        chest.setAttacksInChest(chest.getAttacksInChest() - 1);
        labelAttack.setText(chest.getAttacksInChest());
    }

    private void getSpecialAttack(){
        Chest chest = gameController.getPlayerInTurn().getChest();

        chest.setSpecialAttackInChest(chest.getSpecialAttackInChest() - 1);
        labelSpecialAttack.setText(chest.getSpecialAttackInChest());
    }

    /*private void getMovement(){//retornar un objeto de tipo ataque expecial y ataca
        System.out.println("getting movements");
        chest.setMovementsInChest(chest.getMovementsInChest() - 1);
        labelMovements.setText(chest.getMovementsInChest());
    }*/
}
