package View.Components;

import BL.GameController;
import BL.observer.interfaces_observer.Observer;
import Model.Chest;
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

    private GameController controller = GameController.getInstance();

    public HudChest(Stage stage){
        Chest chest = controller.getGameState().getPlayerInTurn().getChest();
        controller.getTimer().addObservers(this);


        countGunner = new Label(String.format("%01d", chest.getGunner()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        countInfantry = new Label(String.format("%01d", chest.getInfantry()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        countTank = new Label(String.format("%01d", chest.getTank()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        countAttack = new Label(String.format("%01d", chest.getAttacksInChest()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        countSpecialAttack = new Label(String.format("%01d", chest.getSpecialAttackInChest()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        countMovement =  new Label(String.format("%01d", chest.getMovementsInChest()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

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

    public Label getLabelGunner(){
        return this.countGunner;
    }

    public Label getLabelInfantry(){
        return this.countInfantry;
    }

    public Label getLabelTank(){
        return this.countTank;
    }

    public Label getLabelMovements(){
        return this.countMovement;
    }

    public Label getLabelAttack(){
        return this.countAttack;
    }


    public Label getLabelSpecialAttack(){
        return this.countSpecialAttack;
    }

    @Override
    public void update(GameState state) {


        if(state.getTime() < 0){
           updateLabels(state);
        }
    }

    public void updateLabels(GameState state){
        Chest chest = state.getPlayerInTurn().getChest();
        this.countGunner.setText(chest.getGunner());
        this.countInfantry.setText(chest.getInfantry());
        this.countTank.setText(chest.getTank());
        this.countMovement.setText(chest.getMovementsInChest());
        this.countAttack.setText(chest.getAttacksInChest());
        this.countSpecialAttack.setText(chest.getSpecialAttackInChest());
    }
}
