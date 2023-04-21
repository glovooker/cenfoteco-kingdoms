package View.Components;

import BL.GameController;
import BL.observer.interfaces_observer.Observer;
import Model.GameState;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class HudChest implements Observer {

    private Label countGunner;

    private Label countInfantry;

    private Label countTank;

    private Label countAttack;

    private Label countSpecialAttack;

    private Label countMovement;

    private int initMovements;

    private int initArmy;
    private int initGunner;
    private int initInfantry;
    private int initTank;

    private int initAttacks;
    private int initSpecialAttack;

    private GameController controller = GameController.getInstance();

    public HudChest(Stage stage, int initMovements, int initGunner, int initInfantry, int initTank, int initSpecialAttack, int initAttacks){
        this.initMovements = initMovements;
        this.initGunner = initGunner;
        this.initInfantry = initInfantry;
        this.initTank = initTank;
        this.initSpecialAttack = initSpecialAttack;
        this.initAttacks = initAttacks;
        controller.getTimer().addObservers(this);


        countGunner = new Label(String.format("%01d", initGunner), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        countInfantry = new Label(String.format("%01d", initInfantry), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        countTank = new Label(String.format("%01d", initTank), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        countAttack = new Label(String.format("%01d", initAttacks), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        countSpecialAttack = new Label(String.format("%01d", initSpecialAttack), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        countMovement =  new Label(String.format("%01d", initMovements), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        countInfantry.setPosition(610, 90);
        countInfantry.setFontScale(2.5f);

        countGunner.setPosition(710, 90 );
        countGunner.setFontScale(2.5f);

        countTank.setPosition(830, 90);
        countTank.setFontScale(2.5f);

        countAttack.setPosition(65, 90);
        countAttack.setFontScale(2.5f);

        countSpecialAttack.setPosition(170, 90);
        countSpecialAttack.setFontScale(2.5f);

        countMovement.setPosition(275, 90);
        countMovement.setFontScale(2.5f);


        stage.addActor(countGunner);
        stage.addActor(countInfantry);
        stage.addActor(countTank);
        stage.addActor(countAttack);
        stage.addActor(countSpecialAttack);
        stage.addActor(countMovement);

    }

    public int getInitArmy(){
        return this.initArmy;
    }

    public void setInitArmy(int amount){
        this.initArmy = amount;
    }

    public Label getLabelGunner(){
        return this.countGunner;
    }

    public int getInitGunner(){
        return this.initGunner;
    }

    public void setInitGunner(int amount){
        this.initGunner = amount;
    }

    public Label getLabelInfantry(){
        return this.countInfantry;
    }

    public int getInitInfantry(){
        return this.initInfantry;
    }

    public void setInitInfantry(int amount){
        this.initInfantry = amount;
    }


    public Label getLabelTank(){
        return this.countTank;
    }

    public int getInitTank(){
        return this.initTank;
    }

    public void setInitTank(int amount){
        this.initTank = amount;
    }

    public Label getLabelMovements(){
        return this.countMovement;
    }

    public int getInitMovements(){
        return this.initMovements;
    }

    public void setInitMovements(int amount){
        this.initMovements = amount;
    }

    public Label getLabelAttack(){
        return this.countAttack;
    }

    public int getInitAttacks(){
        return this.initAttacks;
    }

    public void setInitAttacks(int amount){
        this.initAttacks = amount;
    }

    public Label getLabelSpecialAttack(){
        return this.countSpecialAttack;
    }

    public int getInitSpecialAttack(){
        return this.initSpecialAttack;
    }

    public void setInitSpecialAttack(int amount){
        this.initSpecialAttack = amount;
    }

    @Override
    public void update(GameState state) {

        if(state.getTime() < 0){
            this.countGunner.setText(state.getPlayerInTurn().getChest().getGunner());
            this.countInfantry.setText(state.getPlayerInTurn().getChest().getInfantry());
            this.countTank.setText(state.getPlayerInTurn().getChest().getTank());
            this.countMovement.setText(state.getPlayerInTurn().getChest().getMovementsInChest());
            this.countAttack.setText(state.getPlayerInTurn().getChest().getAttacksInChest());
            this.countSpecialAttack.setText(state.getPlayerInTurn().getChest().getSpecialAttackInChest());
        }
    }
}
