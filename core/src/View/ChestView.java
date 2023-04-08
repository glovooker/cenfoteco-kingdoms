package View;

import BL.GameController;
import BL.MementoController;
import Model.Chest;
import View.Components.ButtonComponent;
import View.Components.HudChest;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

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

    private Chest chest;
    private final GameController gameController = GameController.getInstance();

    public ChestView(Stage stage){
        this.stage = stage;
        chest = gameController.getPlayerInTurn().getChest();
        hud  = new HudChest(this.stage, chest.getMovementsInChest(), chest.getGunner(), chest.getInfantry(), chest.getTank(), chest.getSpecialAttackInChest(), chest.getAttacksInChest());
        labelInfantry = hud.getLabelInfantry();
        labelAttack = hud.getLabelAttack();
        labelGunner = hud.getLabelGunner();
        labelMovements = hud.getLabelMovements();
        labelTank = hud.getLabelTank();
        labelSpecialAttack = hud.getLabelSpecialAttack();
        defineButtons();
    }

    public void defineButtons(){
        buttonAddArmy = new ButtonComponent(this.stage, "chestClosed.png", 80, 100, 430,50, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                addArmy(6);
                System.out.println("pp");
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        buttonGetInfantry = new ButtonComponent(this.stage, "escudero.png", 130, 130, 570, 120, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                getInfantry();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        buttonGetGunner = new ButtonComponent(this.stage, "arquero.png", 120, 120, 680, 120, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                getGunner();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        buttonGetTank = new ButtonComponent(this.stage, "golem.png", 120, 120, 780, 120, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                getTank();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        buttonGetAttack = new ButtonComponent(this.stage, "attack.png", 80, 80, 50, 150, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                getAttack();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        buttonGetSpecialAttack = new ButtonComponent(this.stage, "specialAttack.png", 80, 80, 150, 150, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                getSpecialAttack();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        buttonGetMovement = new ButtonComponent(this.stage, "movements.png", 80, 80, 250, 150, new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                getMovement();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    private void addArmy(int num){//recibir un objeto tipo armada
        int infantries = hud.getInitInfantry();
        int gunners = hud.getInitGunner();
        int tanks = hud.getInitTank();
        int army = hud.getInitArmy();
        int movements = hud.getInitMovements();
        int attacks = hud.getInitAttacks();
        int specialAttacks = hud.getInitSpecialAttack();

        if(num == 1 && army < chest.ARMY_AMOUNT_MAX){
            addingInfantries(infantries);
        }else{
            if(num == 2 && army < chest.ARMY_AMOUNT_MAX){
                addingGunner(gunners);
            } else if (num == 3 && army < chest.ARMY_AMOUNT_MAX) {
                addingTank(tanks);
            } else if (num == 4 && movements < chest.MAX_MOVEMENTS) {
                addingMovements(movements);
            } else if (num == 5 && attacks < chest.MAX_ATTACK) {
                addingAttacks(attacks);
            } else if (num == 6 && specialAttacks < chest.MAX_SPECIAL_ATTACK) {
                addingSpecialAttacks();
            }else{
                System.out.println("No se pueden añadir mas elementos");
            }
        }
    }

    private void addingSpecialAttacks(){
        chest.setSpecialAttackInChest(chest.getSpecialAttackInChest() + 1);
        System.out.println("se añadio un ataque especial");
        labelSpecialAttack.setText(chest.getSpecialAttackInChest());
        //se inserta el ataque especial en el array
    }

    private void addingAttacks(int attacks){
        hud.setInitAttacks(attacks + 1);
        System.out.println("se añadio un ataque");
        labelAttack.setText(hud.getInitAttacks());
        //se inserta el tanque en el array
    }

    private void addingTank(int tanks){
        hud.setInitTank(tanks + 1);
        hud.setInitArmy(hud.getInitGunner() + hud.getInitInfantry() + hud.getInitTank());
        System.out.println("se añadio un tanque");
        labelTank.setText(hud.getInitTank());
        //se inserta el tanque en el array
    }

    private void addingGunner(int gunners){
        hud.setInitInfantry(gunners + 1);
        hud.setInitArmy(hud.getInitGunner() + hud.getInitInfantry() + hud.getInitTank());
        System.out.println("se añadio un artillero");
        labelGunner.setText(hud.getInitGunner());
        //se inserta el artillero en el array
    }

    private void addingInfantries(int infantriesInChest){
        hud.setInitInfantry(infantriesInChest + 1);
        hud.setInitArmy(hud.getInitGunner() + hud.getInitInfantry() + hud.getInitTank());
        System.out.println("se añadio un infantero");
        labelInfantry.setText(hud.getInitInfantry());
        //se inserta el infantero en el array
    }

    private void addingMovements(int movementsInChest){
        hud.setInitMovements(movementsInChest + 1);
        System.out.println("se añadio un movimiento");
        labelMovements.setText(hud.getInitMovements());
        //se inserta el movimiento en el array
    }

    private void getInfantry(){//retornar un objeto de tipo infanteria
        System.out.println("getting infantry");
        chest.setInfantry(chest.getInfantry() - 1);
        labelInfantry.setText(chest.getInfantry());
    }

    private void getGunner(){//retornar un objeto de tipo arquero
        System.out.println("getting gunner");
        chest.setGunner(chest.getGunner() - 1);
        labelGunner.setText(chest.getGunner());
    }

    private void getTank(){//retornar un objeto de tipo tanque
        System.out.println("getting tank");
        chest.setTank(chest.getTank() - 1);
        labelTank.setText(chest.getTank());
    }

    private void getAttack(){//retornar un objeto de tipo ataque y ataca
        System.out.println("getting attack");
        chest.setAttacksInChest(chest.getAttacksInChest() - 1);
        labelAttack.setText(chest.getAttacksInChest());
    }

    private void getSpecialAttack(){//retornar un objeto de tipo ataque expecial y ataca
        System.out.println("getting  special attack");
        chest.setSpecialAttackInChest(chest.getSpecialAttackInChest() - 1);
        labelSpecialAttack.setText(chest.getSpecialAttackInChest());
    }

    private void getMovement(){//retornar un objeto de tipo ataque expecial y ataca
        System.out.println("getting movements");
        chest.setMovementsInChest(chest.getMovementsInChest() - 1);
        labelMovements.setText(chest.getMovementsInChest());
    }
}
